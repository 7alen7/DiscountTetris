package tetris;

public class Player {
	private String name;
	
	Player(String playerName)
	{
		name = playerName;
	}
	
	public void setPlayerName(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return this.name;
	}
}
