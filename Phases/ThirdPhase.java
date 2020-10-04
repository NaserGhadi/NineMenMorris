package Phases;

import java.awt.Color;

import java.awt.event.MouseEvent;
import java.util.Random;

import GameItems.Field;
import GameOptions.GameView;


/**
 * 
 * @author Naser Ghadi
 * This class represents the second phase of the game where players can flying their men on the field.<br>
 * Note: the methods represent the same functionality as the first phase. 
 */ 
public class ThirdPhase extends Phase {
	
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
	
	public boolean botPlaying (int playerId, Field field, GameView gameView, int[] playersStatus) {
		boolean canMove = false;
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

        for (char i = 'a'; i <= 'x'; i++) {
            for (int j = 1; j < 5; j++) {
                if (gameRules.emptyPlace(field, gameRules.getAvailableMoves()[i - 'a'][j])){
                    canMove = true;
                    break;
                }
            }
        }

        if (canMove){
            flyMan(hit, playerId, field, gameView, playersStatus);
            if (gameRules.checkAttackPossibility(field, playerId)){
                gameView.changeResolution(100, 180);
                attack(playerId, field, gameView, playersStatus);
            }
            return true;
        }
        return false;
	}
	
	public boolean humanPlaying(int playerId, Field field, GameView gameView, int[] playersStatus) {
		boolean haveMan = false;
        boolean canMove = false;
        MouseEvent[] mouseEvents = gameView.getMouseEvents();
        for (MouseEvent mouseEvent : mouseEvents) {
            if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                for (char place : field.getPlayers()[playerId].getPlaces()) {
                    if (hit == place) {
                        haveMan = true;
                        break;
                    }
                }
                for (char i = 'a'; i <= 'x'; i++) {
                    for (int j = 1; j < 5; j++) {
                        if (gameRules.emptyPlace(field, gameRules.getAvailableMoves()[i - 'a'][j])){
                            canMove = true;
                            break;
                        }
                    }
                }

                if (canMove && haveMan && hit != ' ' && hit != '#'){
                    flyMan(hit, playerId, field, gameView, playersStatus);
                    if (gameRules.checkAttackPossibility(field, playerId)){
                        gameView.changeResolution(100, 180);
                        attack(playerId, field, gameView, playersStatus);
                    }
                    return true;
                }
            }
        } 
        return false;
	}
	
	@Override
    public void run(int playerId, Field field, GameView gameView, int[] playersStatus) {
		
        displayCanvas(playerId, field, gameView);
        while (true) {

            if (playersStatus[playerId] == 2) {
            	if (botPlaying(playerId, field, gameView, playersStatus))
            		return;
            }
            else{
            	if (humanPlaying(playerId, field, gameView, playersStatus))
            		return;
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException ignore) { }
        }
	}
	
	/**
	 * This field is responsible for flying specific man for a player. 
	 * @param movingMan
	 * @param playerId
	 * @param field
	 * @param gameView
	 * @param playersStatus
	 */
	public void flyMan(char movingMan, int playerId, Field field, GameView gameView, int[] playersStatus ){
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
                	

                    field.getPlayers()[playerId].addPlace(hit);
                    field.getPlayers()[playerId].removePlace(movingMan);
                    gameView.playSound("click.wav", false);
                    return;

            }

            else {
                MouseEvent[] mouseEvents = gameView.getMouseEvents();
                for (MouseEvent mouseEvent : mouseEvents) {
                    if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                        char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                        if (gameRules.emptyPlace(field, hit) && hit != ' ' && hit != '#'){
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
