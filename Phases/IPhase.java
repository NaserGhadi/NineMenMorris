package Phases;

import GameItems.Field;
import GameOptions.GameView;

/**
 * 
 * @author Naser Ghadi
 * This interface represents the methods that the class must has so it can be a Phase.
 */
public interface IPhase {
    void run(int playerId, Field field, GameView gameView, int[] playersStatus);
    void displayCanvas(int playerId, Field field, GameView gameView);
    boolean humanPlaying(int playerId, Field field, GameView gameView, int[] playersStatus);
}
