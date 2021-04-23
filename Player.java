package tetris;
/**
 * Player Object Class
 * 
 * <p> Defines a getter and setter function for manipulating the current Player's name
 * @author CS321 Group 3
 * @version 3rd attempt to get this project to work.
 *
 */ 
public class Player {
	/**
	 * Variable used to store the current player's name
	 */
	private String name;
	
	/**
	 * Constructor for creating a Player object
	 * @param playerName: String to be set as the player's name
	 */
	Player(String playerName)//constructor for Player class
	{
		name = playerName;//Sets player name to temporary parameter
	}
	
	/**
	 * Method used to change the player's current name
	 * @param n: String to be set as player's name
	 */
	public void setPlayerName(String n)//Creates the name for the player 
	{
		name = n;
	}
	
	/**
	 * Method to get the player's name
	 * @return The player's name
	 */
	public String getName()//Returns name to the function call and allows the name to be accessed
	{
		return this.name;
	}
}
