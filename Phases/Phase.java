package Phases;

import java.awt.*;
import java.awt.event.MouseEvent;

import GameItems.Field;
import GameOptions.GameRules;
import GameOptions.GameView;

/**
 * 
 * @author Naser Ghadi
 * This Super class represents the Phase generally
 * and has methods represents for processing the changes that happen to the game when playing a certain phase.
 */

public class Phase implements IPhase {
	protected GameRules gameRules = new GameRules();
	
	@Override
	public void run(int playerId, Field field, GameView gameView, int[] playersStatus){}

	@Override
	public void displayCanvas(int playerId, Field field, GameView gameView) {}
	
	
	@Override
	public boolean humanPlaying(int playerId, Field field, GameView gameView, int[] playersStatus) {return true;}
	
	private void firstPlayerCanvas(int playerId, char place,Field field, GameView gameView) {
		if (place == 'a')
            gameView.addToCanvas(field.drawPlaceA(), 0, 0, Color.RED);
        if (place == 'b')
            gameView.addToCanvas(field.drawPlaceB(), 0, 0, Color.RED);
        if (place == 'c')
            gameView.addToCanvas(field.drawPlaceC(), 0, 0, Color.RED);
        if (place == 'd')
            gameView.addToCanvas(field.drawPlaceD(), 0, 0, Color.RED);
        if (place == 'e')
            gameView.addToCanvas(field.drawPlaceE(), 0, 0, Color.RED);
        if (place == 'f')
            gameView.addToCanvas(field.drawPlaceF(), 0, 0, Color.RED);
        if (place == 'g')
            gameView.addToCanvas(field.drawPlaceG(), 0, 0, Color.RED);
        if (place == 'h')
            gameView.addToCanvas(field.drawPlaceH(), 0, 0, Color.RED);
        if (place == 'i')
            gameView.addToCanvas(field.drawPlaceI(), 0, 0, Color.RED);
        if (place == 'j')
            gameView.addToCanvas(field.drawPlaceJ(), 0, 0, Color.RED);
        if (place == 'k')
            gameView.addToCanvas(field.drawPlaceK(), 0, 0, Color.RED);
        if (place == 'l')
            gameView.addToCanvas(field.drawPlaceL(), 0, 0, Color.RED);
        if (place == 'm')
            gameView.addToCanvas(field.drawPlaceM(), 0, 0, Color.RED);
        if (place == 'n')
            gameView.addToCanvas(field.drawPlaceN(), 0, 0, Color.RED);
        if (place == 'o')
            gameView.addToCanvas(field.drawPlaceO(), 0, 0, Color.RED);
        if (place == 'p')
            gameView.addToCanvas(field.drawPlaceP(), 0, 0, Color.RED);
        if (place == 'q')
            gameView.addToCanvas(field.drawPlaceQ(), 0, 0, Color.RED);
        if (place == 'r')
            gameView.addToCanvas(field.drawPlaceR(), 0, 0, Color.RED);
        if (place == 's')
            gameView.addToCanvas(field.drawPlaceS(), 0, 0, Color.RED);
        if (place == 't')
            gameView.addToCanvas(field.drawPlaceT(), 0, 0, Color.RED);
        if (place == 'u')
            gameView.addToCanvas(field.drawPlaceU(), 0, 0, Color.RED);
        if (place == 'v')
            gameView.addToCanvas(field.drawPlaceV(), 0, 0, Color.RED);
        if (place == 'w')
            gameView.addToCanvas(field.drawPlaceW(), 0, 0, Color.RED);
        if (place == 'x')
            gameView.addToCanvas(field.drawPlaceX(), 0, 0, Color.RED);
	}
	private void secondPlayerCanvas(int playerId, char place,Field field, GameView gameView) {
		if (place == 'a')
            gameView.addToCanvas(field.drawPlaceA(), 0, 0, Color.GREEN);
        if (place == 'b')
            gameView.addToCanvas(field.drawPlaceB(), 0, 0, Color.GREEN);
        if (place == 'c')
            gameView.addToCanvas(field.drawPlaceC(), 0, 0, Color.GREEN);
        if (place == 'd')
            gameView.addToCanvas(field.drawPlaceD(), 0, 0, Color.GREEN);
        if (place == 'e')
            gameView.addToCanvas(field.drawPlaceE(), 0, 0, Color.GREEN);
        if (place == 'f')
            gameView.addToCanvas(field.drawPlaceF(), 0, 0, Color.GREEN);
        if (place == 'g')
            gameView.addToCanvas(field.drawPlaceG(), 0, 0, Color.GREEN);
        if (place == 'h')
            gameView.addToCanvas(field.drawPlaceH(), 0, 0, Color.GREEN);
        if (place == 'i')
            gameView.addToCanvas(field.drawPlaceI(), 0, 0, Color.GREEN);
        if (place == 'j')
            gameView.addToCanvas(field.drawPlaceJ(), 0, 0, Color.GREEN);
        if (place == 'k')
            gameView.addToCanvas(field.drawPlaceK(), 0, 0, Color.GREEN);
        if (place == 'l')
            gameView.addToCanvas(field.drawPlaceL(), 0, 0, Color.GREEN);
        if (place == 'm')
            gameView.addToCanvas(field.drawPlaceM(), 0, 0, Color.GREEN);
        if (place == 'n')
            gameView.addToCanvas(field.drawPlaceN(), 0, 0, Color.GREEN);
        if (place == 'o')
            gameView.addToCanvas(field.drawPlaceO(), 0, 0, Color.GREEN);
        if (place == 'p')
            gameView.addToCanvas(field.drawPlaceP(), 0, 0, Color.GREEN);
        if (place == 'q')
            gameView.addToCanvas(field.drawPlaceQ(), 0, 0, Color.GREEN);
        if (place == 'r')
            gameView.addToCanvas(field.drawPlaceR(), 0, 0, Color.GREEN);
        if (place == 's')
            gameView.addToCanvas(field.drawPlaceS(), 0, 0, Color.GREEN);
        if (place == 't')
            gameView.addToCanvas(field.drawPlaceT(), 0, 0, Color.GREEN);
        if (place == 'u')
            gameView.addToCanvas(field.drawPlaceU(), 0, 0, Color.GREEN);
        if (place == 'v')
            gameView.addToCanvas(field.drawPlaceV(), 0, 0, Color.GREEN);
        if (place == 'w')
            gameView.addToCanvas(field.drawPlaceW(), 0, 0, Color.GREEN);
        if (place == 'x')
            gameView.addToCanvas(field.drawPlaceX(), 0, 0, Color.GREEN);
	}
	public void addPlayerCanvas(int playerId, char place,Field field, GameView gameView){
	        if (playerId == 0){
	            firstPlayerCanvas(playerId, place, field, gameView);
	        }
	        if (playerId == 1){
	            secondPlayerCanvas(playerId, place, field, gameView);
	        }
	 }

	public void attack(int playerId, Field field, GameView gameView,int[] playersStatus){
        gameView.addToCanvas(field.drawField(playerId), 0, 0, Color.WHITE);
        for (int i = 0; i < 2; i++){
            for (char place : field.getPlayers()[i].getPlaces()) {
                addPlayerCanvas(i, place, field, gameView);
            }
        }
        gameView.printCanvas();
        while (true){

            if (playersStatus[playerId] == 2){
                char hit = 'z';
                int anotherPlayerId = playerId == 0 ?  1 :  0;
                for (char place : field.getPlayers()[anotherPlayerId].getPlaces()){
                    hit = place;
                    break;
                }
                this.gameRules.clickProcessor(hit, playerId, true, field, gameView);
                return;
            }
            else {
                MouseEvent[] mouseEvents = gameView.getMouseEvents();
                for (MouseEvent mouseEvent : mouseEvents) {
                    if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                        char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                        boolean selfAttack = false;
                        for (char place : field.getPlayers()[playerId].getPlaces()) {
                            if (hit == place)
                                selfAttack = true;
                        }
                        if (!selfAttack && hit != '#' && hit != ' ') {
                            this.gameRules.clickProcessor(hit, playerId, true, field, gameView);
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
