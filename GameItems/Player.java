package GameItems;


import java.util.*;

/**
 * 
 * @author Naser Ghadi.
 * 
 * This class represents the player and its attributes.
 *
 */

public class Player implements Comparable<Player> {
    private int id;
    /**
     * This attribute represents the men that this player has.
     */
    private ArrayList<Character> places;
    /**
     * This attribute represents the mills that this player has.
     */
    private boolean[] mills = new boolean[16];
    
    private int numberOfMen = 0;

    public Player(){
    	for (int i = 0; i < 16; i++)
    		mills[i] = false;
    }
    
    public String toString() {
    	String placesString = "";
    	String millsString = "";
    	for(char place : places) {
    		placesString += (" " + place);
    	}
    	for(int i = 0; i < 16; i++) {
    		if(mills[i])
    			millsString += mills[i] ? (" " + i) : "";
    	}
    	return ""
    			+ "Player #" + id 
    			+ "\nMen: " + placesString
    			+ "\nMills: " + millsString;
    }
    @Override
    public int compareTo(Player player) {
    	return this.getNumberOfMen() - player.getNumberOfMen();
    }
    public Player clone() {
    	return this;
    }
    public Player(int id){
        this.id = id;
        this.places = new ArrayList<>();
        for (int i = 0; i < 16; i++)
    		mills[i] = false;
    }

    public int getNumberOfMen() {
    	this.numberOfMen = this.places.size();
    	return this.numberOfMen;
    }
    public boolean[] getMills() {
        return mills;
    }

    public void setMills(boolean[] mills) {
        this.mills = mills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Character> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Character> places) {
        this.places = places;
    }

    public void addPlace(char newPlace){
        this.places.add(newPlace);
    }
    public void removePlace(char place){
        this.places.remove(places.indexOf(place));
    }
}
