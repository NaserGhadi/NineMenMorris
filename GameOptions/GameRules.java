package GameOptions;

import java.util.*;

import GameItems.Field;
import GameItems.Player;

/**
 * @author Naser Ghadi.
 * 
 * Is this class we define the rules that this game will follow. 
 */

public class GameRules {
    private char[][] attackRules;
    private char[][] availableMoves;

    /**
     * In this constructor we initialize the Attack Rules and available Moves;
     */
    public GameRules() {
        this.attackRules = new char[16][3];
        StringBuilder rules = new StringBuilder("abcdefghijklmnopqrstuvwxajvdksglpbehqtwimrfnucox");
        int groupsCounter = 0, elementsCounter = 0;
        for (int i = 0; i < rules.length(); i++) {
        	attackRules[groupsCounter][elementsCounter] = rules.charAt(i);
        	elementsCounter++;
        	if (elementsCounter > 2) {
        		elementsCounter = 0;
        		groupsCounter++;
        	}
        }

        this.availableMoves = new char[24][5];
        StringBuilder moves = new StringBuilder("abjzzbacezcbozzdekzzedfbhfenzzglhzzhegizihmzzjakvzkjdsllkgpzmirnznfumoocxnzplqzzqprtzrqmzzsktzztsqwuuntzzvjwzzwvtxzxwozz");
        groupsCounter = 0;
        elementsCounter = 0;
        for (int i = 0; i < moves.length(); i++) {
        	availableMoves[groupsCounter][elementsCounter] = moves.charAt(i);
        	elementsCounter++;
        	if (elementsCounter > 4) {
        		elementsCounter = 0;
        		groupsCounter++;
        	}
        }
        
    }

    public char[][] getAttackRules() {
        return attackRules;
    }

    public void setAttackRules(char[][] attackRules) {
        this.attackRules = attackRules;
    }

    public char[][] getAvailableMoves() {
        return availableMoves;
    }

    public void setAvailableMoves(char[][] availableMoves) {
        this.availableMoves = availableMoves;
    }

    public boolean emptyPlace(Field field, char targetPlace){
        if (targetPlace == 'z')
            return false;
        int players = field.getPlayers().length;
        for (int i = 0; i < players; i++){
            for (char place : field.getPlayers()[i].getPlaces()){
                if (place == targetPlace)
                    return false;
            }
        }
        return true;
    }
    public boolean checkMovePossibility(Field field, char movingMan, char targetPlace){
        if (targetPlace == 'z')
            return false;
        if (!emptyPlace(field, targetPlace))
            return false;

        for (int i = 1; i < 5; i++){
            if (availableMoves[movingMan - 'a'][i] == targetPlace)
                return true;
        }
        return false;
    }
    public boolean canMove(Field field, char movingMan){
        for (int i = 1; i < 5; i++){
            if (this.emptyPlace(field, this.availableMoves[movingMan - 'a'][i]) ){
                return true;
            }
        }
        return false;
    }
    public void resetMills(Field field, int playerId){
        ArrayList<Character> places;
        Player player = field.getPlayers()[playerId];
        places = field.getPlayers()[playerId].getPlaces();

        int count = 0;
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 3; j++) {
                for (char place : places) {
                    if (place == attackRules[i][j]) {
                        count++;
                        break;
                    }
                }
            }
            if (count == 3){
            	player.getMills()[i] = true;
            }
            else
            	player.getMills()[i] = false;
            count = 0;
        }
    }
    public boolean checkForWin(Field field){
        for(int i = 0; i < field.getPlayers().length; i++) {
        	if (field.getPlayers()[i].getNumberOfMen() < 3)
        		return true;
        }
        return false;
    }
    public boolean clickProcessor(Character hit, int playerId, boolean attackClick, Field field, GameView gameView){
    	if (hit.equals('#')|| hit.equals(' '))
    		return false;
                if (attackClick){
                    for (int i = 0; i < 2; i++) {
                        for (char place : field.getPlayers()[i].getPlaces()){
                            if (hit == place){
                                field.getPlayers()[i].removePlace(hit);
                                gameView.playSound("Explosion.wav", false);
                                this.resetMills(field, i);
                                Player debugPlayer = field.getPlayers()[i].clone();
                                System.out.println(debugPlayer.toString());
                                return true;
                            }
                        }
                    }
                }
                else {
                    if (this.emptyPlace(field, hit)){
                        field.getPlayers()[playerId].addPlace(hit);
                        gameView.playSound("click.wav", false);
                        return true;
                    }
                }
        return false;
    }
    public boolean checkAttackPossibility(Field field, int playerId){
        ArrayList<Character> places;
        Player player = field.getPlayers()[playerId];
        places = field.getPlayers()[playerId].getPlaces();

        int count = 0;
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 3; j++) {
                for (char place : places) {
                    if (place == attackRules[i][j]) {
                        count++;
                        break;
                    }
                }
            }
            if (count == 3){
                if (!player.getMills()[i]) {
                    player.getMills()[i] = true;
                    return true;
                }
            }
            else {
                player.getMills()[i] = false;
            }
            count = 0;
        }
        return false;
    }
}
