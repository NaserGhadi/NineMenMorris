package Game;

import java.awt.event.MouseEvent;

import GameOptions.GameView;

/**
 * @author Naser Ghadi.
 * 
 * This class is all about process and show user interfaces when the player playing the game.<br>
 * It is responsible for add strings with different colors to the canvas,
 * so the player can interact with the game interfaces.
 */
public class IntroInterfaces {
	

	/**
	 * this method is responsible for add canvas that represents the start menu and print it to the screen.
	 * @param gameView
	 */
	public void startMenu(GameView gameView) {
        gameView.addToCanvasCentered("Welcome to nine men's morris!!!\n\n\n" + "        --> PLAY <--\n\n");
        gameView.printCanvas();
        while (true) {
            MouseEvent[] mouseEvents = gameView.getMouseEvents();
            for (MouseEvent mouseEvent : mouseEvents) {
                if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                    char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                    if (hit == 'P' || hit == 'L' || hit == 'A' || hit == 'Y') {
                        return;
                    }
                }
                }
            try {
                Thread.sleep(2);
            } catch (InterruptedException ignore) {
            }
        }
    }
	/**
	 * This method is responsible for add canvas that represents the Winner menu and print it to the screen.
	 * @param winner
	 * @param gameView
	 */
    public void EndGame(int winner, GameView gameView) {
        gameView.addToCanvasCentered("PLAYER #" + winner + "\nCONGRATS YOU WIN.\n\n\n" + "        --> continue <--\n\n");
        gameView.printCanvas();
        while (true) {
            MouseEvent[] mouseEvents = gameView.getMouseEvents();
            for (MouseEvent mouseEvent : mouseEvents) {
                if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                    char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                    if (hit == 'c' || hit == 'o' || hit == 'n' || hit == 't' || hit == 'i' || hit == 'n' || hit == 'u' || hit == 'e') {
                        return;
                    }
                }
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException ignore) {
            }
        }
    }
    /**
     * This method is responsible for add canvas that represents the Choose Player Status menu
     * that process player situation (Bot or human) and print it to the screen.
     * @param id
     * @param gameView
     * @param playersStatus
     */
    public void playerStatus(int id, GameView gameView, int[] playersStatus) {
        gameView.addToCanvasCentered("Player #" + (id + 1) + " :\n\n\n" + "HUMAN\n\nBOT");
        gameView.printCanvas();
        while (true) {
            MouseEvent[] mouseEvents = gameView.getMouseEvents();
            for (MouseEvent mouseEvent : mouseEvents) {
                if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                    char hit = gameView.getCharacter(mouseEvent.getY(), mouseEvent.getX());
                    if (hit == 'H' || hit == 'U' || hit == 'M' || hit == 'A' || hit == 'N') {
                        playersStatus[id] = 1;
                        return;
                    }
                    if (hit == 'B' || hit == 'O' || hit == 'T') {
                        playersStatus[id] = 2;
                        return;
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
