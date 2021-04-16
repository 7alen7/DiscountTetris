package tetris;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.Timer;

import tetris.Board.keyPress;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Game extends JFrame{
	
	private Board board;
	
	TextField text = new TextField();
	Button start = new Button("Start");
	private JLabel currentScoreDisplay;
	public TextArea scoreBoardText = new TextArea("",70,15,TextArea.SCROLLBARS_NONE);
	
	private int state;
	public static final int STARTED =  1;
    public static final int GAMEOVER = 2;
	
    public int scores[] = {0, 0, 0, 0, 0};
    public String names[] = {"Player1", "Player2", "Player3", "Player4", "Player5"};
    private int scoreBoardCount = 0;
    
    private int ButtonSwitch = 0;
    
    
    private Player user = new Player("holder");
       
    private Timer gameOver;
    
    private boolean firstPass = true;
    
    private JRadioButton basic = new JRadioButton("Basic Style");
	private JRadioButton hacker = new JRadioButton("Hacker Style");
	private JRadioButton ChicagoCubs = new JRadioButton("Chicago Cubs Style");
	private JRadioButton rave = new JRadioButton("Rave Style");
	private ButtonGroup group = new ButtonGroup();
	
	private boolean checked = true;
	
	public Game()
	{
		board = new Board();
	}
	
	public void quit() throws IOException
    {
		if(firstPass)
		{
		firstPass = false;
    	int counter = 0;
        int counter2 = 0;
        if(true)
        {
       	 while(scores[counter] < board.getScore())
       	 {
       		 counter++;
       		 if(counter == 5)
       		 {
       			 break;
       		 }
       		 if(scores[counter] == 0)
       		 {
       			 counter2++;
       		 }
       	 }
       	 if(counter == 1)
       	 {
       		 scores[0] = board.getScore();
       		 names[0] = user.getName();
       	 }
       	 else if(counter == 2)
       	 {
       		 scores[0] = scores[1];
       		 scores[1] = board.getScore();
       		 
       		 names[0] = names[1];
       		 names[1] = user.getName();
       	 }
       	 else if(counter == 3)
       	 {
       		 scores[0] = scores[1];
       		 scores[1] = scores[2];
       		 scores[2] = board.getScore();
       		 
       		 names[0] = names[1];
       		 names[1] = names[2];
       		 names[2] = user.getName();
       	 }
       	 else if(counter == 4)
       	 {
       		 scores[0] = scores[1];
       		 scores[1] = scores[2];
       		 scores[2] = scores[3];
       		 scores[3] = board.getScore();
       		 
       		 names[0] = names[1];
       		 names[1] = names[2];
       		 names[2] = names[3];
       		 names[3] = user.getName();
       	 }
       	 else if(counter == 5)
       	 {
       		 scores[0] = scores[1];
       		 scores[1] = scores[2];
       		 scores[2] = scores[3];
       		 scores[3] = scores[4];
       		 scores[4] = board.getScore();
       		 
       		 names[0] = names[1];
       		 names[1] = names[2];
       		 names[2] = names[3];
       		 names[3] = names[4];
       		 names[4] = user.getName();
       		 
       	 }
        }
        
        PrintWriter w2 = new PrintWriter("scores.txt");
        w2.print("");
        w2.close();
        FileWriter writer = new FileWriter("scores.txt", true);
        
        int i = 4;
        while(i >= counter2)
        {
       	 writer.write(names[i]);
       	 writer.write("\n");
       	 writer.write(String.valueOf(scores[i]));
       	 writer.write("\n");
       	 i--;
        }
        writer.close();
        // Handle components
        
        state = GAMEOVER;
        changeButtonText("Start");
        firstPass = false;
		}
    }

	public void start() throws IOException 
	{
		add(board);
		
		setResizable(false);
		start.setFocusable(false);
        add(start, BorderLayout.SOUTH); 
        initStart();
        
        initTimer();              

        scoreBoardText.setEditable(false);
        scoreBoardText.setFocusable(false);
        
        calculateScoreBoardCount();
        populateScoreBoard();

        add(scoreBoardText, BorderLayout.EAST);
   		currentScoreDisplay = new JLabel(" 0");
	    add(currentScoreDisplay, BorderLayout.NORTH);
        
        
        setTitle("Discount Tetris");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);	        
   		
   		add(scoreBoardText, BorderLayout.EAST);
   		checked = true;
	}
	
	private void initStart()
	{
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
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
	            		
	            		scoreBoardText.setEditable(false);
	            		scoreBoardText.setText("");
	               	 	scoreBoardText.setText(" High Scores                  \n"+
	                            " ---------------------\n" + "User - Score\n\n");
	               	 	calculateScoreBoardCount();
	               	 	int i = 4;
	               	 	int tempCtr = 5;
	               	 	while(tempCtr > 0)
	               	 	{
	               			scoreBoardText.append(names[i] + " - " + scores[i] + "\n");
	               			i--;
	               			tempCtr--;
	               		}
	               	 	scoreBoardText.append("\nPress the Exit button while in the middle of a game to clear the scoreboard.\n\nPress 'f' to view customization options\n\nPress 'i' to view intensity options\n\nPress 's' to enable/disable shadows\n\nPress 'm' for a secret");
	               	 	gameOver.start();
	            	}
	            	else
	            	{
	            		board.quit(0);
	            		checked = false;
	            		quit();
	            		start.setLabel("Start");
	            		ButtonSwitch = 0;
	            		gameOver.stop();
	            		
	            		
	            		
	            	}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
		});
	}
	
	
	private void initTimer()
	{
		gameOver = new Timer(300, new checkState());
	}

	private void populateScoreBoard() throws FileNotFoundException {
       	if(scoreBoardCount == 0)
       	{
       		scoreBoardText.setEditable(false);
       		scoreBoardText.setText(" High Scores                  \n"+
                    " ---------------------\n\n" + "No current High Scores\n\nGuess that means I can make it up?\n"
                    		+ "\n\nUser\tScore\n\nDan\t99999");
       	} 
       	else
       	{
       		File fileName = new File("scores.txt");
       		Scanner reader = new Scanner(fileName);
       		int counter = 0;
            while(reader.hasNextLine())
            {
            	names[counter] = reader.nextLine();
           	 	scores[counter] = Integer.parseInt(reader.nextLine());
           	 	counter++;
           	 	if(counter >= 5)
           	 	{
           	 		counter = 5;
           	 		break;
           	 	}
            }
            
            PrintWriter writer = new PrintWriter(fileName);
            writer.print("");
            writer.close();
            
            reader.close();
            int count = 5;
            int temp;
            String tempS;
            for (int i = 0; i < count; i++) 
            {
                for (int j = i + 1; j < count; j++) { 
                    if (scores[i] > scores[j]) 
                    {
                        temp = scores[i];
                        tempS = names[i];
                        scores[i] = scores[j];
                        names[i] = names[j];
                        scores[j] = temp;
                        names[j] = tempS;
                    }
                }
            }
            
            
            for(int i = 0; i < 5; i++)
            {
            	scores[i] = scores[i];
            	names[i] = names[i];
            }
            

       		scoreBoardText.setEditable(false);
       		scoreBoardText.setText(" High Scores                  \n"+
                    " ---------------------------\n\n" + "User - Score\n\n");
       		int i = 4;
       		int tempCtr = counter;
       		System.out.println(scoreBoardCount);
       		while(tempCtr > 0)
       		{
       			scoreBoardText.append(names[i] + " - " + scores[i] + "\n");
       			i--;
       			tempCtr--;
       		}
       		
       		scoreBoardText.append("\n\n\nPress 'f' to view customization options\n\nPress 'i' to view intensity options\n\nPress 's' to disable shadows\n\nPress 'm' for a secret");
       	}
		
	}

	public void changeButtonText(String text)
	{
		start.setLabel(text);
	}
	
	public void calculateScoreBoardCount() throws IOException
    {

   	 	File fileName = new File("scores.txt");
   	 	if(fileName.createNewFile())
   	 	{
   	 		scoreBoardCount = 0;
   	 	}
   	 	else
   	 		
   	 	{
   	 		Scanner reader = new Scanner(fileName);
   	 		while(reader.hasNextLine())
   	 		{
   	 			reader.nextLine();
   	 			reader.nextLine();
   	 			scoreBoardCount++;
   	 		}
   	 		System.out.println(scoreBoardCount);
   	 		reader.close();
   	 	}
    }
	
	private class checkState implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        	try {
				checkState();
			} catch (IOException e1) {
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

