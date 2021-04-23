package tetris;

import java.io.IOException;

/**
 * Start Tetris Class
 * 
 * <p> Used to start a game of tetris by creating a new Game object and using its start method.
 * @author CS321 Group 3
 * @version 3rd attempt to get this project to work.
 *
 */ 
public class startTetris {
	public static void main(String[] args) throws IOException
	{
		Game game = new Game(); // create new game
		
		game.start(); // start the game
		game.setVisible(true); // allow you to see the game
	}

}
