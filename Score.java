
package tetris;

import java.awt.Font;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JPanel;

/**
* A class to store score values and read score values to display for the player.
* Scoreboard will update after player begins a new game. 
*/
public class Score extends JPanel {
	
    /**
     * Integer array to hold scores on scoreboard.
     */
    private int[] scores = new int[5];
    /**
     * String array to hold player usernames.
     */
    private String[] names = new String[5];
    /**
     * Used to keep track of scoreboard state.
     */
    private int scoreBoardCount;
    /**
     * TextArea object used to display scoreboard and controls for player.
     */
    public TextArea scoreBoardText = new TextArea("", 75, 15, TextArea.SCROLLBARS_NONE);
     
    /**
     * Constructor class to intialize class fields to default values. 
     */
    public Score() {
        
        for(int i = 0; i < 5; i++) {
            scores[i] = 0;
        }
        for(int j = 1; j <= 5; j++) {
            names[j-1] = "Player" + j;
        }
        scoreBoardCount = 0;
        
    }
    
     /**
      *  Reads scores stored in text file to display for the player before Tetris game starts.
      * @throws FileNotFoundException 
      */
    public void populateScoreBoard() throws FileNotFoundException 
        {
       	    if(scoreBoardCount == 0)
            {
		// No current scores, display default.
       		scoreBoardText.setEditable(false);
                scoreBoardText.setFont(new Font("Courier", 1, 12));
       		scoreBoardText.setText(" High Scores                  \n"+
                    " --------------------\n\n" + "No current High Scores\n\nGuess that means I can make it up?\n"
                    		+ "\n\nUser\tScore\n\nDan\t99999");
       	    } 
            else
       	    {
		// Read scores from text file.
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
            
                for(int i = 0; i < 5; i++) 
                {
            	scores[i] = scores[i];
            	names[i] = names[i];
                }
                
		// Set scoreboard to dispaly current score values
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
    
    /**
     * Determines how many values shall be displayed on scoreboard for the new game.
     * @throws IOException
     */
    private void calculateScoreBoardCount() throws IOException 
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
	
    /**
    * Sorts score list with new submitted score and write to text file.
    * @param board board object holding new score.
    * @param user user object holding current player's username.
    * @throws IOException
    */
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
            // Move scores down according to where the newest score falls in the rankings
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
    
    /**
     * Display scoreboard along with controls while player is playing Tetris.
     */
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
    
    /**
     * Used to get TextArea object.
     * @return TextArea object to be added and displayed for player.
     */
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
   
    
   }
