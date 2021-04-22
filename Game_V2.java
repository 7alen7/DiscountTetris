package edu.uah.cs.cs321.tetris2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *  Class Description
 */
public class Game extends JFrame{
	
    // Declare class variables
    public Board board;
	
    /**
     * The TextField to display ScoreBoard and Controls 
     */
   // TextField text = new TextField();
    
    /**
     * A button that allows user to start or quit game
     */
    Button start = new Button("Start");
    /**
     * TextArea to display ScoreBoard and Controls
     */
    public TextArea scoreBoard = new TextArea("",70,15,TextArea.SCROLLBARS_NONE);
	
    private int state;
    /**
     * Static value to flag the game's current state as started. 
     */
    public static final int STARTED =  1;
    /**
     * Static value to flag the game's current state as ended.
     */
    public static final int GAMEOVER = 2;
    
    /**
     * 
     */
    private int ButtonSwitch = 0;
    
    /**
     * Player object to hold player's username.
     */
    public Player user = new Player("holder");
    
    /**
     * Timer object...
     */
    private Timer gameOver;
    
    /**
     * Boolean to indicate whether or not the game is in a first pass.
     */
    private boolean firstPass = true;    
    
    /**
     * Boolean to keep track of game state.
     */
    private boolean checked = true;
    
    /**
     * Score object used to ...
     */
    private Score score = new Score();

    /**
     * Public constructor of game class to create a new Board to begin playing Tetris.
     */
    public Game()
    {
       board = new Board();
    }
	
    /**
     * Public method to change state of game to GAMEOVER.
     * @throws IOException 
     */
    public void quit() throws IOException
    {
	if(firstPass)
	{
	   firstPass = false;
           score.addScoreToBoard(board, user);

        // Handle components
        
            state = GAMEOVER; // change state
            changeButtonText("Start"); // reset button
            firstPass = false;
        }
    }

    /**
     * Used to start a game of Tetris.
     * Adds displays for the Tetris board and text for ScoreBoard and controls. 
     * @throws IOException 
     */
    public void start() throws IOException 
    {
	add(board);
		
	setResizable(false);
	start.setFocusable(false);
        add(start, BorderLayout.SOUTH); 
        
        initStart();
        
        initTimer();              

        score.populateScoreBoard();
        scoreBoard = score.getScoreBoardText();
        add(scoreBoard, BorderLayout.EAST);

        
        setTitle("Discount Tetris");
        setSize(520, 585); // board size
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);	        
   		
   	checked = true;
	}
	
    /**
     * Obtains player's username and begins the execution of Tetris.
     * Changes button depending on state of game.
     */
	private void initStart() // edits button text, starts and stops games
	{
           start.addActionListener(new ActionListener()
		{
	           public void actionPerformed(ActionEvent e)
	           {
		        try 
			{
	            	    if(ButtonSwitch % 2 == 0 || board.getState() == GAMEOVER)
	            	    {
	            	        checked = true;
	            		String userName = JOptionPane.showInputDialog("Please input your username: ");
	               	 	while(userName == null || userName.length() == 0)
	               	        {
	               	 	    userName = JOptionPane.showInputDialog("Please input your username (You must input a name): ");
	               	 	}
	               	 	user.setPlayerName(userName);
	            		board.start();
	            		firstPass = true;
	            		start.setLabel("Quit");
	            		ButtonSwitch++;
	            		
                                score.DisplayScoreBoard();

	               	 	gameOver.start(); 
	            	    }
	            	   else
	            	   {
	            		board.quit();
	            		checked = false;
	            		quit();
	            		start.setLabel("Start");
	            		ButtonSwitch = 0;
	            		gameOver.stop();	
	            	    }
		        } 
                        catch (FileNotFoundException e1) 
                        {
			   // TODO Auto-generated catch block
			    e1.printStackTrace();
			} 
                        catch (IOException e1) 
                        {
		           // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
	    	    }
		});
	}
	
	/**
         * Timer used to check game state and update button.
         */
	private void initTimer() 
	{
	   gameOver = new Timer(300, new checkState());
	}

        /**
         * Allows for the start/quit button to be altered.
         * @param text the new text to be added to the button
         */
	public void changeButtonText(String text) 
	{
	    start.setLabel(text);
	}
	
	private class checkState implements ActionListener 
        { // used to check state of board and edit button accordingly. Used by timer since I couldn't figure out PropertyChangeSupport

            @Override
            public void actionPerformed(ActionEvent e) {
        	try {
		    checkState();
		} 
                catch (IOException e1) {
	          // TODO Auto-generated catch block
		     e1.printStackTrace();
	        }
            }
        
            private void checkState() throws IOException
    	    {
    	        if(board.getState() == 2 && checked == true)
    	        {	
    		    checked = false;
    		    quit();
    		    changeButtonText("Start");
    		    ButtonSwitch = 0;
    	        }
    	        else if(board.getState() != 2)
    	        {
    		checked = true;
    	        }
    	    }
        }
        

}