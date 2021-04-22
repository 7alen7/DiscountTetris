
package edu.uah.cs.cs321.tetris2;

import java.awt.Font;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JPanel;


public class Score extends JPanel {
     
    public Score() {
        
        for(int i = 0; i < 5; i++) {
            scores[i] = 0;
        }
        for(int j = 1; j <= 5; j++) {
            names[j-1] = "Player" + j;
        }
        scoreBoardCount = 0;
        
    }
    
    public void populateScoreBoard() throws FileNotFoundException 
        { // reads info from scores.txt
       	    if(scoreBoardCount == 0)
            {
       		scoreBoardText.setEditable(false);
                scoreBoardText.setFont(new Font("Courier", 1, 12));
       		scoreBoardText.setText(" High Scores                  \n"+
                    " --------------------\n\n" + "No current High Scores\n\nGuess that means I can make it up?\n"
                    		+ "\n\nUser\tScore\n\nDan\t99999");
       	    } 
            else
       	    {
       		File fileName = new File("scores.txt");
       		Scanner reader = new Scanner(fileName);
       		int counter = 0;
                while(reader.hasNextLine())
                {
            	    names[counter] = reader.nextLine(); // gets associated name
           	    scores[counter] = Integer.parseInt(reader.nextLine()); // gets socres value
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
                    for (int j = i + 1; j < count; j++) 
                    { //sort score and name arrays so they go highest to lowest
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
            
                for(int i = 0; i < 5; i++) // don't know what this does, scared to remove it
                {
            	scores[i] = scores[i];
            	names[i] = names[i];
                }
            
       		scoreBoardText.setEditable(false);
                scoreBoardText.setFont(new Font("Courier", 1, 12));
       		scoreBoardText.setText(" High Scores                  \n"+
                    " -------------------------\n\n" + "User - Score\n\n");
       		int i = 4;
       		int tempCtr = counter;
       		System.out.println(scoreBoardCount);
       		while(tempCtr > 0)
       		{
       		    scoreBoardText.append(names[i] + " - " + scores[i] + "\n"); // display sorted scores
       		    i--;
       		    tempCtr--;
       		}
       		// append instructions to scoreboard TextArea
       		scoreBoardText.append("\n\n\nPress 'f' to view customization options\n\nPress 'i' to view intensity options\n\nPress 's' to disable shadows\n\nPress 'm' for a secret");
       	    }
		
	}
    
    private void calculateScoreBoardCount() throws IOException // calculates how many values should be displayed on scoreboard for new game. Equal to number of scores in scores.txt
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
    
    public void addScoreToBoard(Board board, Player user) throws IOException {
        
         int counter = 0; // keep total of scores that are below the current score
         int counter2 = 0; // keep track of scores that are equal to 0
           
           if(true)
           {
       	    while(scores[counter] < board.getScore()) // used to find how many scores in the list are above the game's current score 
       	    {   
       	        counter++;
       		if(counter == 5)
                    break;
                    
       		if(scores[counter] == 0)
       	            counter2++;
       		   
       	    }
            switch (counter) {
            // These next if statements move the scores down according to where the newest score falls in the rankings
                case 1:
                    scores[0] = board.getScore();
                    names[0] = user.getName();
                    break;
                case 2:
                    scores[0] = scores[1];
                    scores[1] = board.getScore();
                    names[0] = names[1];
                    names[1] = user.getName();
                    break;
                case 3:
                    scores[0] = scores[1];
                    scores[1] = scores[2];
                    scores[2] = board.getScore();
                    names[0] = names[1];
                    names[1] = names[2];
                    names[2] = user.getName();
                    break;
                case 4:
                    scores[0] = scores[1];
                    scores[1] = scores[2];
                    scores[2] = scores[3];
                    scores[3] = board.getScore();
                    names[0] = names[1];
                    names[1] = names[2];
                    names[2] = names[3];
                    names[3] = user.getName();
                    break;
                case 5:
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
                    break;
                default:
                    break;
            }
           }
        
            PrintWriter w2 = new PrintWriter("scores.txt"); // create new writer
            w2.print(""); // clear writer file
            w2.close(); // close
            FileWriter writer = new FileWriter("scores.txt", true); // create new writer to append
        
            int i = 4;
            while(i >= counter2) // add scores and names to file that are not equal to 0 (have to be earned)
            {
       	         writer.write(names[i]);
       	         writer.write("\n");
       	         writer.write(String.valueOf(scores[i]));
       	         writer.write("\n");
       	         i--;
            }
            writer.close();
    }
    
    public void DisplayScoreBoard() {
        
        try {
        scoreBoardText.setEditable(false);
        scoreBoardText.setFont(new Font("Courier", 1, 12));
        scoreBoardText.setText("");
	scoreBoardText.setText(" High Scores                  \n\n"+
	         " ------------------\n" + "User - Score\n\n");
	calculateScoreBoardCount();
	int i = 4;
	int tempCtr = 5;
	while(tempCtr > 0)
	{
	    scoreBoardText.append(names[i] + " - " + scores[i] + "\n");
	    i--;
	    tempCtr--;
	}
        
	scoreBoardText.append("\n\nControls\n\n------------------\n"
                + "\nPress the Exit button while in the middle of a game to "
                + "clear the scoreboard.\n\nPress 'f' to view customization options\n"
                + "\nPress 'i' to view intensity options\n"
                + "\nPress 's' to enable/disable shadows\n\nPress 'm' for a secret");
    
        }
        catch(IOException el) {
            el.printStackTrace();
        }
       
        
    }
    
    public TextArea getScoreBoardText() {
        try {
            
            scoreBoardText.setEditable(false);
            scoreBoardText.setFocusable(false);
            calculateScoreBoardCount();
            populateScoreBoard();
            return scoreBoardText;
        }
        catch(IOException el) {
             el.printStackTrace();
            return scoreBoardText;
        }

    }
   
    private int[] scores = new int[5];
    private String[] names = new String[5];
    private int scoreBoardCount;
    
    public TextArea scoreBoardText = new TextArea("", 75, 15, TextArea.SCROLLBARS_NONE);
    
   }
