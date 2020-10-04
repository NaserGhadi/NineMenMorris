package Phases;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Random;

import GameItems.Field;
import GameOptions.GameView;


/**
 * 
 * @author Naser Ghadi
 * This class represents the second phase of the game where players move their men on the field.<br>
 * Note: the methods represent the same functionality as the first phase. 
 */ 
public class SecondPhase extends Phase {
	

	public void displayCanvas(int playerId, Field field, GameView gameView) {
		gameView.changeResolution(100, 180);
		gameView.addToCanvas(field.drawField(playerId), 0, 0, Color.WHITE);
        for (int i = 0; i < 2; i++){
            for (char place : field.getPlayers()[i].getPlaces()) {
                addPlayerCanvas(i, place, field, gameView);
            }
        }
        gameView.printCanvas();
	}
	public void botPlaying(Field field, GameView gameView, int playerId, int[] playersStatus) {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {
        }
        char hit = 'z';
        for (char place : field.getPlayers()[playerId].getPlaces()){
            if (gameRules.canMove(field, place)){
                hit = place;
                break;
            }
        }
            moveMan(hit, playerId, field, gameView, playersStatus);
            if (gameRules.checkAttackPossibility(field, playerId)){
                gameView.changeResolution(100, 180);
                attack(playerId, field, gameView, playersStatus);
            }
	}
	public boolean humanPlaying(Field field, GameView gameView, int playerId, int[] playersStatus) {
		boolean haveMan = false;
                boolean canMove = false;
        		MouseEvent[] mouseEvents = gameView.getMouseEvents();
                for (MouseEvent mouseEvent : mouseEvents) {
                    if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                        Character hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                        for (char place : field.getPlayers()[playerId].getPlaces()) {
                            if (hit == place) {
                                haveMan = true;
                                break;
                            }
                        }
                        if (hit != ' ' && hit != '#') {
                            for (int i = 1; i < 5; i++) {
                                if (gameRules.emptyPlace(field, gameRules.getAvailableMoves()[hit - 'a'][i])) {
                                    canMove = true;
                                    break;
                                }
                            }
                        }
                        
                        if (canMove && haveMan && hit != ' ' && hit != '#') {
                            moveMan(hit, playerId, field, gameView, playersStatus);
                            if (gameRules.checkAttackPossibility(field, playerId)) {
                                gameView.changeResolution(100, 180);
                                attack(playerId, field, gameView, playersStatus);
                            }
                            return true;
                        }
                        haveMan = false;
                        canMove = false;
                        
                    }
                    
                }
                return false;
	}
	
	@Override
    public void run(int playerId, Field field, GameView gameView, int[] playersStatus) {
        displayCanvas( playerId, field, gameView);
        while (true) {
            if (playersStatus[playerId] == 2) {
            	botPlaying(field, gameView, playerId, playersStatus);
            	return;
            }
            else {
            	if (humanPlaying(field, gameView, playerId, playersStatus))
            		return;
            }
            try {
            	Thread.sleep(2);
            } catch (InterruptedException ignore) { }

        }
	}
/**
 * This field is responsible for moving specific man for a player. 
 * @param movingMan
 * @param playerId
 * @param field
 * @param gameView
 * @param playersStatus
 */
public void moveMan(char movingMan, int playerId, Field field, GameView gameView, int[] playersStatus){
    gameView.addToCanvas(field.drawField(playerId), 0, 0, Color.WHITE);
    for (int i = 0; i < 2; i++){
        for (char place : field.getPlayers()[i].getPlaces()) {
            addPlayerCanvas(i, place, field, gameView);
        }
    }
    gameView.printCanvas();
    while (true){

        if (playersStatus[playerId] == 2) {
            Random random = new Random();
            String choices = "abcdefghijklmnopqrstuvwx";
            char hit = choices.charAt(random.nextInt(choices.length()));
            while (!gameRules.emptyPlace(field, hit))
                hit = choices.charAt(random.nextInt(choices.length()));


            if (gameRules.checkMovePossibility(field, movingMan, hit)){
                field.getPlayers()[playerId].removePlace(movingMan);
                field.getPlayers()[playerId].addPlace(hit);
                gameView.playSound("click.wav", false);
                return;
            }
        }

        else {
            MouseEvent[] mouseEvents = gameView.getMouseEvents();
            for (MouseEvent mouseEvent : mouseEvents) {
                if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                    char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                    if (gameRules.checkMovePossibility(field, movingMan, hit) && hit != ' ' && hit != '#'){
                        field.getPlayers()[playerId].removePlace(movingMan);
                        field.getPlayers()[playerId].addPlace(hit);
                        gameView.playSound("click.wav", false);
                        return;
                    }
                }
            }
        }

        try {
            Thread.sleep(2);
        } catch (InterruptedException ignore) {
        }
    }
}
}
