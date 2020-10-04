package Phases;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;

import GameItems.Field;
import GameOptions.GameView;


/**
 * 
 * @author Naser Ghadi
 * This class represents the first phase of the game where players locate their nine men on the field.
 */
public class FirstPhase extends Phase {
	
	/**
	 * This method is responsible for display the canvas that represents the phase field.
	 * @param playerId
	 * @param field
	 * @param gameView
	 */
	@Override
	public void displayCanvas(int playerId, Field field, GameView gameView) {
		gameView.addToCanvas(field.drawField(playerId), 0, 0, Color.WHITE);
        for (int i = 0; i < 2; i++){
            for (char place : field.getPlayers()[i].getPlaces()) {
                addPlayerCanvas(i, place, field, gameView);
            }
        }
        gameView.printCanvas();
	}
	/**
	 * This method is responsible for making the computer plays the phase.
	 * @param playerId
	 * @param field
	 * @param gameView
	 * @param playersStatus
	 */
	public void botPlaying(int playerId, Field field, GameView gameView, int[] playersStatus) {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) { }
        Random random = new Random();
        String choices = "abcdefghijklmnopqrstuvwx";
        char hit = choices.charAt(random.nextInt(choices.length()));
        while (!gameRules.emptyPlace(field, hit))
            hit = choices.charAt(random.nextInt(choices.length()));

        if (this.gameRules.clickProcessor(hit, playerId, false, field, gameView)) {
            if (gameRules.checkAttackPossibility(field, playerId)){
                gameView.changeResolution(100, 180);
                attack(playerId, field, gameView, playersStatus);
            }
        }
	}
	/**
	 * This method is responsible for making the user plays the phase.
	 * @param playerId
	 * @param field
	 * @param gameView
	 * @param playersStatus
	 */
	@Override
	public boolean humanPlaying(int playerId, Field field, GameView gameView, int[] playersStatus) {
		MouseEvent[] mouseEvents = gameView.getMouseEvents();
        for (MouseEvent mouseEvent : mouseEvents) {
            if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                if (this.gameRules.clickProcessor(hit, playerId, false, field, gameView)) {
                    if (gameRules.checkAttackPossibility(field, playerId)) {
                        gameView.changeResolution(100, 180);
                        attack(playerId, field, gameView, playersStatus);
                    }
                    return true;
                }
            }
        }
        return false;
	}
	/**
	 * This override method is responsible for run this phase and processing user events during this phase.
	 * @param playerId
	 * @param field
	 * @param gameView
	 * @param playersStatus
	 */
    @Override
    public void run(int playerId, Field field, GameView gameView, int[] playersStatus) {
    	displayCanvas(playerId, field, gameView);
        while (true) {
            if (playersStatus[playerId] == 2){
                botPlaying(playerId, field, gameView, playersStatus);
                return;
            }
            else {
                if(humanPlaying(playerId, field, gameView, playersStatus)) {
                	return;
                }
            }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ignore) {
                }
        }
    }
}
