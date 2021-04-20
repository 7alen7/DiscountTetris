package tetris;
//This class is responsible creating the player and holding the name 
public class Player {
	private String name;
	
	Player(String playerName)//constructor for Player class
	{
		name = playerName;//Sets player name to temporary parameter
	}
	
	public void setPlayerName(String n)//Creates the name for the player 
	{
		name = n;
	}
	
	public String getName()//Returns name to the function call and allows the name to be accessed
	{
		return this.name;
	}
}
