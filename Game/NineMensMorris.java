package Game;

import GameItems.Field;
import GameOptions.GameRules;
import GameOptions.GameView;
import Phases.FirstPhase;
import Phases.SecondPhase;
import Phases.ThirdPhase;



/**
 * This is the main class of the game , this is where the game begin.<br>
 * @author Naser Ghadi.
 * 
 */

public class NineMensMorris {

    static final int rows = 90;
    static final int columns = 160;
    private static final boolean MOUSE = true;
    private int players = 2;
    private int[] playersStatus = new int[2];
    private Field field;
    private GameRules gameRules = new GameRules();
    private int roundCounter = 0;
    private GameView gameView;
    private FirstPhase firstPhase = new FirstPhase();
    private SecondPhase secondPhase = new SecondPhase();
    private ThirdPhase thirdPhase = new ThirdPhase();
    private long lastShow;
    private IntroInterfaces introInterfaces = new IntroInterfaces();

    public static void main(String[] args) {
        new NineMensMorris().game();
    }

    /**
     * This is the class constructor Here where we initialize the game view and set some initial values.
     * */
    private NineMensMorris() {
        // GameView Initialization
        gameView = new GameView(rows, columns, GameView.WINDOWSIZE_LARGE);
        gameView.setTitle("Nine Men's Morris");
        gameView.setWindowIcon("icon.png");
        gameView.setStatusText("Java Programmierung SS 2020");
        if (MOUSE) {
            gameView.useMouse(true);
            //gameView.setMouseCursor("Cursor.png", true);
        }
        // New Game Initialization
        initGame();
    }

    /**
     * With this method we reset some basic attributes to its initial values.
     */
    private void initGame() {
        lastShow = 0;
        Field newField = new Field(this.players);
        this.field = newField;
        this.roundCounter = 0;
    }
    /**
     * this method is to display and process the Start Menu and every user interface before start playing. 
     */
    private void intro() {
    	// Start Menu
        gameView.changeResolution(18, 32);
        this.introInterfaces.startMenu(gameView);

        // Check Player's Status Menu
        for (int i = 0; i < players; i++){
            gameView.changeResolution(18, 32);
            this.introInterfaces.playerStatus(i, gameView, playersStatus);
        }

        // Preparing the Game interface
        gameView.changeResolution(rows, columns);
        // background sound
        gameView.playSound("background-music.wav", true);
    }
    
    /**
     * This method is to display and process the user interface when the game is in the first phase.
     */
    private void firstRoundIntro() {
    	 for (int i = 0; i < this.players; i++){
                        gameView.changeResolution(100, 180);
                        this.firstPhase.run(i, field, gameView, playersStatus);
         }
    }
    /**
     * This method is to display and process the user interface when the game is in the second or third phase.
     */
    private void lastRoundsIntro() {
    	for (int i = 0; i < this.players; i++){
            if (this.field.getPlayers()[i].getPlaces().size() > 3){
            	this.secondPhase.run(i, field, gameView, playersStatus);
                if (gameRules.checkForWin(field)){
                    break;
                }
            }
            else{
            	this.thirdPhase.run(i, field, gameView, playersStatus);
                if (gameRules.checkForWin(field)){
                    break;
                }
            }
        }
    }
    
    /**
     * This is the method that contain the ( Game while loop ) and call all methods required to run the game.
     */
    private void game() {
        while (true){
            this.intro();
            while (true) {
                // Sleep so that the CPU is not getting warm
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ignore) {
                }
                // Refresh the field
                roundCounter++;
                if (roundCounter <= 9){
                	firstRoundIntro();
                }
                else {
                	lastRoundsIntro();
                }
                if (gameRules.checkForWin(field) && roundCounter > 9){
                	int winner = -1;
                	if (field.getPlayers()[0].compareTo(field.getPlayers()[1]) > 0) {
                		winner = 1;
                	}
                	else if(field.getPlayers()[0].compareTo(field.getPlayers()[1]) < 0) {
                		winner = 2;
                	}
                	else
                		winner = 0;
                    gameView.changeResolution(18, 32);
                    this.introInterfaces.EndGame(winner, gameView);
                    initGame();
                    break;
                }
                // show every 16 ms (60 FPS)
                long now = System.currentTimeMillis();
                if (now - lastShow >= 15) {
                    gameView.printCanvas();
                    lastShow = now;
                }
            }
        }


    }
  
   
}