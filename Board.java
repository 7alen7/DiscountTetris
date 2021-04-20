package tetris;

// Packages used for this class
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Random;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Board Object Class
 * 
 * <p> Defines the various attributes of the tetris board and how it, and the blocks that make the board up, can be manipulated. 
 * @author CS321 Group 3
 * @version 3rd attempt to get this project to work.
 *
 */
public class Board extends JPanel {

	/**
	 * Holds the width of the board. Value is set to 10, to signify 10 blocks wide.
	 */
	private final int WIDTH = 10;
	
	/**
	 * Holds the height of the board. Value is set to 22, to signify 22 blocks wide.
	 */
	private final int HEIGHT = 22;
		
	
	/**
	 * Holds the state of the board, can be set to STARTED, GAMEOVER, or PAUSED (1, 2, or 3)
	 */
	private int state;
	
	/**
	 * Static value set to denote the game is in a started/playing state. Integer value of 1.
	 */
	public static final int STARTED =  1;
	/**
	 * Static value set to denote the game is in a stopped/game over state. Integer value of 2.
	 */
    public static final int GAMEOVER = 2;
    /**
	 * Static value set to denote the game is in a paused state. Integer value of 3.
	 */
    public static final int PAUSED =   3;
    
    /**
     * A second check for the variable isFalling. Used to be absolutely sure that the current block is/isn't falling.
     */
    private boolean blockState = false;
    
    /**
     * Holds the score of the current game on the board. Is edited by removeLines() function. Incremented by 10 for every block placed.
     */
    private int score = 0;
    /**
     * Used to check if, after the last lines were removed, this current block placement also removed lines.
     * This feature rewards players for removing lines in continuous play segments. 
     */
    private int BackToBackCount = 1;
    /**
     * Used to check if a line was removed during the last play segment. 
     * True if a line was removed, false otherwise. Initially set to false.
     */
    private boolean removedLastRound = false;
    
    /**
     * Used to hold the current falling block. Will be edited by functions until finally placed on the board.
     */
    private Block falling;
    /**
     * Used to check if the falling block is still in a falling state. Double checked by the blockState boolean variable.
     */
    private boolean isFalling = true;
    /**
     * A double array used to hold the types of blocks that are currently populating the board.
     * The integer values in this array correspond to the static values found in Block class.
     */
    private int[][] block;
    
    /**
     * A timer that is used to interrupt the flow of program execution, causes blocks to fall down, and checks the state of the currently falling block.
     * 
     */
    Timer cycle;
    
    /**
     * A string that denotes the current chosen customization type for the board. Initially set to "Basic"
     */
    private String customizationOption = BASIC;
    
    /**
     * A static string that is used to signify the board has "Basic" customization selected.
     */
    public static final String BASIC =   	"Basic";
    /**
     * A static string that is used to signify the board has "Hacker" customization selected.
     */
    public static final String HACKER =    "Hacker";
    /**
     * A static string that is used to signify the board has "Chicago Cubs" customization selected.
     */
    public static final String CUBS ="Chicago Cubs";
    /**
     * A static string that is used to signify the board has "Rave" customization selected.
     */
    public static final String RAVE =        "Rave";
    /**
     * Used to alternate the color of the lines on the board to simulate rave lights. Values can be 0, 1, 2, 3, or 4.
     * Used in conjunction with raveSetting2.
     */
    private int raveSetting = 0;
    /**
     * Used to alternate the color of the lines on the board to simulate rave lights.
     * Used in conjunction with raveSetting.
     */
    private int raveSetting2 = 0;
    
    /**
     * String value that denotes the current board difficulty and scoring system. Initially set to "Basic"
     */
    private String intensity = "Basic";
    /**
     * A static string used to denote that the board has the "Basic" difficulty selected. 
     */
    public static final String BASIC_INTENSITY = "Basic";
    /**
     * A static string used to denote that the board has the "Hard" difficulty selected. 
     */
    public static final String HARD = 			  "Hard";
    /**
     * A static string used to denote that the board has the "Easy" difficulty selected. 
     */
    public static final String EASY = 			  "Easy";
    
    /**
     * Used to check if the player wants to have shadows enabled on the game board. Initially 0.
     * If 0, the game will draw shadows under the falling block, otherwise it won't.
     */
    private int shadows = 0;
    
    /**
     * Used to check if the player wants to have a Dropkick Murphys/Irish style to the board. Initially set to 0.
     * If secret is set to 1, the board will display one of the band's album covers (and look pretty cool), otherwise the game has normal customization.
     */
    private int secret = 0;
    
    /**
     * The JLabel that has items from the board class placed on it
     */
    public JLabel newLabel = new JLabel("");

    /**
     * Public constructor to create a new board object. Initializes the block[][] array, setsFocusable(true) and adds a keyListener.
     */
    public Board() 
    {
    	block = new int[WIDTH][HEIGHT];
    	setFocusable(true);
        addKeyListener(new keyPress());
        
        newLabel.setBounds(0, 0, 400, 400);
        add(newLabel);
        
    }

    /**
     * Returns the width value that each individual block should have to fit the board.
     * @return the integer value 35
     */
    public int getBlockWidth()
    {
    	return 350 / WIDTH;
    }

    /**
     * Returns the height value that each individual block should have to fit the board.
     * @return the integer value 18
     */
    public int getBlockHeight()
    {
    	return 400 / HEIGHT;
    }
    
    /**
     * Gets the state of the board
     * @return current state (STARTED, GAMEOVER, or PAUSED)
     */
    public int getState()
    {
    	return state;
    }

    /**
     * Used to start a new game of tetris on the board.
     * 
     * <P> Resets score, resets the board, gets a new falling block, resets the cycle timer, and updates state to STARTED.
     * @throws IOException if the file "scores.txt" cannot be found
     */
    public void start() throws IOException 
    {    	
    	
    	state = STARTED;
    	
   	 	score = 0;
        falling = new Block();
        block = new int [HEIGHT][WIDTH];
        reset();

        cycle = new Timer(300, new everySecond());
        cycle.start();
        newBlock();

    }
    
    /**
     * Will return the games current score
     * @return score
     */
    public int getScore()
    {
    	return score;
    }

    /**
     * Used to get the block type at position [y][x] in the block array.
     * @param x: width location on the board
     * @param y: height location on board
     * @return integer value that corresponds to the block type found at that location (i.e Block.LINE_FIGURE)
     */
    private int getShape(int x, int y) 
    {
    	return block[y][x];
    }

 
    /**
     * Ends the current game, sets state to GAMEOVER, displays a game over message and final score, and stops the cycle timer.
     */
    public void quit()
    {
    	int i = 0;
    	state = GAMEOVER;
    	JOptionPane.showMessageDialog(null, "Game Over!\nFinal Score: " + score, "Alert!", i);
    	cycle.stop();
    }

    @Override
    /**
     * Used to paint the current board.
     * <P>Paints components in this order: super.paintComponent(g) -> DKM album (if secret is on -> blocks already placed -> falling block shadow -> falling block - > boundary lines
     * @param g: graphics component for this board.
     */
    public void paintComponent(Graphics g) 
    {

        super.paintComponent(g);
        secretOn(g);
        drawElse(g);
        if(shadows == 0)
        {
        	drawGhost(g);
        }
        drawFalling(g);
        drawLines(g);
    }
    
    /**
     * Will be used to draw the dropkick murphys background if and only if the variable secret is set to 1
     * @param g: graphics component for this board.
     */
    private void secretOn(Graphics g)
    {
    	if(secret == 1)
        {       
    		Image im;
    	try {
        	URL url = new URL("https://i1.sndcdn.com/artworks-d76dee38-ed46-4ac6-b59b-896a3c5ae71b-0-t500x500.jpg");
			im = ImageIO.read(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				im = null;
			}
        	g.drawImage(im, 0, 0, 375, 425, this);
        }
    }
    
    /**
     * Used to draw the current falling block
     * @param g: graphics component for this board.
     */
    private void drawFalling(Graphics g)
    {
    	int blocksTop = 25;
    	if (falling.getType() != Block.NO_BLOCK) {

            for (int i = 0; i < 4; i++) {

                int x = falling.getXpos() + falling.getSquareX(i);
                int y = falling.getYpos() - falling.getSquareY(i);

                drawBlocks(g, x * getBlockWidth(),
                        blocksTop + (HEIGHT - y - 1) * getBlockHeight(),
                        falling.getType());
            }
    	}
    }
    
    /**
     * Used to draw the shadows under the currently falling block
     * <P> The method says drawGhost, but it's more of a shadow than anything, sorry to disappoint.
     * @param g: graphics component for this board.
     */
    private void drawGhost(Graphics g)
    {
    	Block Ghost = new Block();
    	Ghost.setBlock(Block.GHOST);
    	int x = 0; int y = 0;
    	
    	int blocksTop = 25;
    	for(int i = 0; i < 4; i++)
    	{
    		
    		x = falling.getXpos() + falling.getSquareX(i);
            y = falling.getYpos() - falling.getSquareY(i);
            
    		while(y > 0 && block[y][x] == Block.NO_BLOCK)
    		{
    			y = y - 1;
    		}
    		
    		drawBlocks(g, x * getBlockWidth(),
                    blocksTop + (HEIGHT - y - 1) * getBlockHeight(),
                    Ghost.getType());
    	}
    }

    /**
     * Used to draw all blocks in the block[][] array. 
     * @param g: graphics component for this board.
     */
    private void drawElse(Graphics g) 
    {

    	int blocksTop = 25;
    	for (int i = 0; i < HEIGHT; i++) {

            for (int j = 0; j < WIDTH; j++) {

                int shape = getShape(j, HEIGHT - i - 1);

                if (shape != Block.NO_BLOCK) {

                    drawBlocks(g, j * getBlockWidth(),
                            blocksTop + i * getBlockHeight(), shape);
                }
            }
        }
    	int j = 0, i = 0;
    	int shape = getShape(j, HEIGHT - i - 1);
    	while(i < HEIGHT)
    	{
    		while(j < WIDTH)
    		{
    			shape = getShape(j, HEIGHT - i - 1);
    			drawBlocks(g, j * getBlockWidth(), blocksTop + i * getBlockHeight(), shape);
    			j++;
    		}
    		i++;
    	}
    }    

    
    /**
     * Used to drop the falling block to the lowest possible point. Will set isFalling to false once that location is found.
     * @throws IOException if scores.txt cannot be found/opened
     */
    private void hardDrop() throws IOException 
    {

        while (falling.getYpos() > 0) {

            if (!moveDownOne(falling) && state != GAMEOVER && state != PAUSED) 
            {

                break;
            }
        }
        isFalling = false;
    }

    /**
     * Used to move falling down one level (y - 1) so long as the game isn't over or paused
     * @throws IOException if scores.txt cannot be found/opened
     */
    private void oneLineDown() throws IOException 
    {

        if (!moveDownOne(falling) && state != GAMEOVER && state != PAUSED) 
        {

            isFalling = false;
        }
    }

    /**
     * Used to totally clear the board, resets block[][] as an array of all Block.NO_BLOCK integers.
     */
    private void reset() 
    {

    	for(int x = 0; x < WIDTH; x++)
    	{
    		for(int y = 0; y < HEIGHT - 2; y++)
    		{
    			block[y][x] = Block.NO_BLOCK;
    		}
    	}
    }

    /**
     * Will increment the score up by 10, and creates a new block object. Also checks if the block can be placed. If not, the game ends.
     * <P> Depending on difficulty settings, blocks can be random (Basic), all line pieces (Easy), or S and Square blocks (Hard).
     * @throws IOException if scores.txt cannot be found/opened
     */
    private void newBlock() throws IOException 
    {
    	System.out.println(score);
    	score += 10;
    	if(!placeBlock(falling))
    	{
    		falling.setBlock(Block.NO_BLOCK);
            cycle.stop();
            
            quit();
    	}
    	else if(intensity.equals(EASY))
    	{
    		falling.setBlock(2);
    	}
    	
    	else if(intensity.equals(HARD))
    	{
    		Random random = new Random();
    		int ran = random.nextInt(2 - 1 + 1) + 1;
    		if(ran == 1)
    		{
    			falling.setBlock(5);
    		}
    		else
    		{
    			falling.setBlock(3);
    		}
    	}
    	
    	else if(intensity.equals(BASIC_INTENSITY))
    	{
    		falling = Block.randomBlock();
    	}
    	
        falling.setXpos(5);
        falling.setYpos(20);
    }

    /**
     * Used to rotate the currently falling block, clockwise or counter clockwise. 
     * @param newBlock: new block that is a copy of the old falling object, just rotated one direction
     * @return true if there are currently no blocks in its way, false otherwise. A value of false will not allow the block to rotate
     */
    private boolean tryRotation(Block newBlock) 
    {

    	
    	int originalX = falling.getXpos();
    	
    	int originalY = falling.getYpos();
        for (int i = 0; i < 4; i++) 
        {
        	
            int x = falling.getXpos() + newBlock.getSquareX(i);
            int y = falling.getYpos() - newBlock.getSquareY(i);

            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) 
            {

                return false;
            }

            if (getShape(x, y) != Block.NO_BLOCK) 
            {

                return false;
            }
        }

        falling = newBlock;
        falling.setXpos(originalX);
        falling.setYpos(originalY);      

        repaint();        
        return true;
    }
    
    /**
     * Used to place the current block on the board. If false, denotes that the blocks that have already been placed are too high, and the game ends.
     * @param block2: current falling block object that will be edited and then restored in the falling object.
     * @return value of true or false. If false, the game will end.
     */
    private boolean placeBlock(Block block2)
    {
    	for (int i = 0; i < 4; i++) 
    	{

            int x = 5;
            int y = 20 + falling.getLowestSquare();
            
            if(block[y][x] != Block.NO_BLOCK)
            {
            	return false;
            }
    	}
            
        falling = block2;
        falling.setXpos(falling.getXpos() - 1);
        falling.setYpos(falling.getYpos());
            
        repaint();
            
        return true;
    }
    
    /**
     * Used to move the current tetrominoe to the left 1 (falling.xPos - 1)
     * @param block2: current falling block object that will be edited and then restored in the falling object.
     * @return value of true or false. If false, the game will end.
     */
    private boolean moveLeft(Block block2)
    {
    	int x, y;
    	int xVals[] = new int[4];
    	int yVals[] = new int[4];
    	
    	boolean failed = false;
    	 //checks to make sure there is room
    	for (int i = 0; i < 4; i++) 
    	{

            x = falling.getXpos() - 1 + block2.getSquareX(i);
            xVals[i] = x;
            
            y = falling.getYpos() - block2.getSquareY(i);
            yVals[i] = y;
    	}
    	
    	for(int j = 0; j < 4; j++)
    	{
    		if (xVals[j] < 0 || xVals[j] >= WIDTH) 
    		{
    			failed = true;
    		}
            
    		else if(yVals[j] < 0 || yVals[j] >= HEIGHT)
    		{
    			failed = true;
    		}
            
            else if(block[yVals[j]][xVals[j]] != Block.NO_BLOCK)
            {
            	failed = true;
            }    	
    	}
    	
    	if(failed == false)
    	{
    		falling = block2;
    		falling.setXpos(falling.getXpos() - 1);
    		falling.setYpos(falling.getYpos());
            
        	repaint();
            
        	return true; 
    	}
    	
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * Used to move the current tetrominoe to the right 1 (falling.xPos + 1)
     * @param block2: current falling block object that will be edited and then restored in the falling object.
     * @return value of true or false. If false, the game will end.
     */
    private boolean moveRight(Block block2)
    { //checks to make sure there is room
    	for (int i = 0; i < 4; i++) 
    	{

            int x = falling.getXpos() + 1 + block2.getSquareX(i);
            int y = falling.getYpos() - block2.getSquareY(i);

            if (x < 0 || x >= WIDTH) 
            {
                return false;
            }
            
            else if(y < 0 || y >= HEIGHT)
            {
            	return false;
            }
            
            else if(block[y][x] != Block.NO_BLOCK)
            {
            	return false;
            }
    	}
            
        falling = block2;
        falling.setXpos(falling.getXpos() + 1);
        falling.setYpos(falling.getYpos());
            
        repaint();
            
        return true;  
    }
    
    /**
     * Used to move the current tetrominoe down one row
     * @param block2: current falling block object that will be edited and then restored in the falling object.
     * @return value of true or false. If false, the block will be considered at its lowest possible point, and a new block will be created.
     */
    private boolean moveDownOne(Block block2)
    {
	    //checks to make sure there is room
    	for (int i = 0; i < 4; i++) 
    	{

            int x = falling.getXpos() + block2.getSquareX(i);
            int y = falling.getYpos() - 1 - block2.getSquareY(i);

            if (x < 0 || x >= WIDTH) 
            {
                return false;
            }
            
            else if(y < 0 || y >= HEIGHT)
            {
            	return false;
            }
            
            else if(block[y][x] != Block.NO_BLOCK)
            {
            	return false;
            }
    	}
            
        falling = block2;
        falling.setXpos(falling.getXpos());
        falling.setYpos(falling.getYpos() - 1);
            
        repaint();
            
        return true;  
    }

    /**
     * Used to remove lines from the board, and shift all rows above it down one. Will also increment score based on difficulty settings.
     * 
     */
    private void removeLines() 
    {
    	int[] types = new int[10];
        int numFullLines = 0;
        boolean fullLine = true;       
        //Checks to see if there are lines full and how many
        for(int i = HEIGHT - 1; i >= 0; i--)
        {
        	fullLine = true;
        	for(int j = 0; j < WIDTH; j++)
        	{
        		types[j] = getShape(j, i);
        	}
        	
        	for(int x = 0; x < 10; x++)
        	{
        		if(types[x] == Block.NO_BLOCK)
        		{
        			fullLine = false;
        		}
        	}
        	
        	if(fullLine)
        	{
        		for (int k = i; k < HEIGHT - 1; k++) 
        		{
                    for (int j = 0; j < WIDTH; j++)
                    {
                        block[k][j] = getShape(j, k + 1);
                    }
        		}
        		numFullLines++;
        	}
        }

        if (numFullLines > 0) {
        	
        	if(removedLastRound)
        	{
        		System.out.println("BTBC: " + BackToBackCount);
        		score = BackToBackCount * 100 + score;
        		
        		if(BackToBackCount < 4)
        		{
        			BackToBackCount++;
        		}
        	}
        	else if(!removedLastRound)
        	{
        		BackToBackCount = 1;
        	}
        	
        	removedLastRound = true;       	       	
            
            if(intensity.equals(BASIC_INTENSITY))//Gives score for basic difficulty based on how many full lines
            {
            	switch(numFullLines)
            	{
            		case 1://One line
            			score += 40;
            			break;
            		case 2://Two lines
            			score += 100;
            			break;
            		case 3://Three lines
            			score += 300;
            			break;
            		case 4://Four lines
            			score += 1200;
            			break;
            	}
            }
            
            else if(intensity.equals(HARD))//Gives score for Hard difficulty
            {
            	switch(numFullLines)
            	{
            		case 1:
            			score += 10;
            			break;
            		case 2:
            			score += 20;
            			break;
            		case 3:
            			score += 30;
            			break;
            		case 4:
            			score += 40;
            			break;
            	}
            }
            
            else if(intensity.equals(EASY))//Gives score for Easy difficulty
            {
            	switch(numFullLines)
            	{
            		case 1:
            			score += 80;
            			break;
            		case 2:
            			score += 200;
            			break;
            		case 3:
            			score += 600;
            			break;
            		case 4:
            			score += 2000;
            			break;
            	}
            }
            score += 10;        
            
            blockState = true;
            falling.setBlock(Block.NO_BLOCK);
        }
        
        else if(numFullLines == 0)
        {
        	BackToBackCount = 1;
        }
    }

    /**
     * This function will be used to draw the blocks on the board, as well as set the background color, depending on which customization options have been chosen.
     * @param g: graphics component of the board
     * @param x: x location of the block to be drawn
     * @param y: y location of the block to be drawn
     * @param shape: objects shape value, used to denote color
     */
    private void drawBlocks(Graphics g, int x, int y, int shape) {
    	
    	
    	if(secret == 1)
    	{
    		switch(shape) {
    		case 0:
    			g.setColor(Color.WHITE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
		
    		case 1:
    			g.setColor(Color.GREEN);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 2:
    			g.setColor(Color.WHITE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 3: 
    			g.setColor(Color.ORANGE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 4:
    			g.setColor(Color.WHITE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 5:
    			g.setColor(Color.GREEN);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 6: 
    			g.setColor(Color.ORANGE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 7:
    			g.setColor(Color.GREEN);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		}
    	}
    	else if(customizationOption == BASIC)
    	{
    		setBackground(Color.white);
    		
    		switch(shape) {
    		case 0:
    			g.setColor(Color.BLACK);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
		
    		case 1:
    			g.setColor(Color.GREEN);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 2:
    			g.setColor(Color.YELLOW);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 3: 
    			g.setColor(Color.RED);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 4:
    			g.setColor(Color.PINK);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 5:
    			g.setColor(Color.ORANGE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 6: 
    			g.setColor(Color.MAGENTA);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 7:
    			g.setColor(Color.GRAY);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 8:
    			g.setColor(Color.BLACK);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;    		
    		}
    	}
    	
    	else if(customizationOption == HACKER)//Sets colors for Hacker background
    	{
    		setBackground(Color.black);
    		
    		switch(shape) {
    		case 0:
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
		
    		case 1:
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 2:
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 3: 
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 4:
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 5:
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 6: 
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 7:
    			g.setColor(Color.green);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 8:
    			g.setColor(Color.WHITE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		}
    	}
    	
    	else if(customizationOption == CUBS)//Sets colors for Cubs background
    	{
    		setBackground(Color.white);
    		
    		switch(shape) {
    		case 0:
    			g.setColor(Color.RED);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
		
    		case 1:
    			g.setColor(Color.BLUE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 2:
    			g.setColor(Color.RED);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 3: 
    			g.setColor(Color.BLUE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 4:
    			g.setColor(Color.RED);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 5:
    			g.setColor(Color.BLUE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 6: 
    			g.setColor(Color.RED);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 7:
    			g.setColor(Color.BLUE);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		case 8:
    			g.setColor(Color.BLACK);
    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    			break;
    		}
    	}
    		
    		else if(customizationOption == RAVE)//Sets colors for Rave background
        	{  
    			if(raveSetting2 >= 0 && raveSetting2 < 3)
    			{
    				setBackground(Color.BLACK);
    				raveSetting2 = 0;
    				switch(shape) {
    				case 0:
    					g.setColor(Color.green);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    		
    				case 1:
    					g.setColor(Color.BLUE);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    				case 2:
    					g.setColor(Color.RED);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    				case 3: 
    					g.setColor(Color.MAGENTA);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    				case 4:
    					g.setColor(Color.GREEN);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    				case 5:
    					g.setColor(Color.CYAN);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    				case 6: 
    					g.setColor(Color.ORANGE);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    				case 7:
    					g.setColor(Color.YELLOW);
    					g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    					break;
    				case 8:
    	    			g.setColor(Color.DARK_GRAY);
    	    			g.fillRect(x + 1, y + 1, getBlockWidth() - 2, getBlockHeight() - 2);
    	    			break;
    				}
    			}    				  			
        	}
}
    
    /**
     * Used to draw lines between block spaces to help players maneuver more accurately. 
     * Colors will be adjusted based on customization options. 
     * @param g: graphics component of the board.
     */
    private void drawLines(Graphics g)
	{
    	switch(customizationOption)
    	{
    		case BASIC:
    			g.setColor(Color.BLACK);
    			break;
    		case HACKER:
    			g.setColor(Color.WHITE);
    			break;
    		case CUBS:
    			g.setColor(Color.BLUE);
    			break;
    		case RAVE:
    			if(raveSetting == 0)
    			{
    				g.setColor(Color.GREEN);    	
    				raveSetting++;
    			}
    			
    			else if(raveSetting == 1)
    			{
    				g.setColor(Color.CYAN);
    				raveSetting++;
    			}
    			
    			else if(raveSetting == 2)
    			{
    				g.setColor(Color.RED);
    				raveSetting++;
    			}
    			
    			else if(raveSetting == 3)
    			{
    				g.setColor(Color.MAGENTA);
    				raveSetting++;
    			}
    			
    			else if(raveSetting == 4)
    			{
    				g.setColor(Color.YELLOW);
    				raveSetting = 0;
    			}
    			break;
    			
    	}
    	//g.drawLine(0, 70, 350, 70);
		g.drawLine(35, 425, 35, 0);
		g.drawLine(70, 425, 70, 0);
		g.drawLine(105, 425, 105, 0);
		g.drawLine(140, 425, 140, 0);
		g.drawLine(175, 425, 175, 0);
		g.drawLine(210, 425, 210, 0);
		g.drawLine(245, 425, 245, 0);
		g.drawLine(280, 425, 280, 0);
		g.drawLine(315, 425, 315, 0);
		g.drawLine(350, 425, 350, 0);
	}
    
    /**
     * Used to change the String value of the variable customizationOption
     * @param style: String that holds the new customization style 
     */
    public void setCustomization(String style)
    {
    	customizationOption = style;
    }

    /**
     * Class that is used for the purpose of creating a game cycle. Will move blocks down at a regular interval, check the game state, and create new blocks when necessary.
     * Implemented because doing this functionality with for/while loops proved to be impossible (for me to understand, at least)
     * @author CS321-Group 3
     *
     */
    private class everySecond implements ActionListener {

        @Override
        /**
         * Is the actionPerformed function of the everySecond class.
         * Used to update block placements, game states, and create new blocks where necessary. 
         */
        public void actionPerformed(ActionEvent e) {

        	if(state == GAMEOVER || state == PAUSED)
        	{
        		return;
        	}
        	
        	if (blockState) {

                blockState = false;
                try {
					newBlock();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
            	
            	if(isFalling == false)
            	{
            		for (int i = 0; i < 4; i++) 
            		{

                        int x = falling.getXpos() + falling.getSquareX(i);
                        int y = falling.getYpos() - falling.getSquareY(i);
                        block[y][x] = falling.getType();
                        
                    }
            		removeLines();
            		isFalling = true;
            		blockState = true;
            	}
            	 
            	else
            	{
            		try {
            			oneLineDown();
            		} catch (IOException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		}
            	}
            }
        	
        	repaint();
        }
    }

    /**
     * keyPress class is used to get the value for various keys that are pressed and alters the board accordingly.
     * @author CS321 Group 3
     *
     */
    class keyPress extends KeyAdapter {

        @Override
        /**
         * keyPressed event. Contains functionality for right, left, up, and down arrows, as well as the space bar, I, M, F, and S keys.
         */
        public void keyPressed(KeyEvent e) {
        	
        	int pressed = e.getKeyCode();

            switch (pressed) {
                
                case KeyEvent.VK_RIGHT: 
                	moveRight(falling);
                	break;
                case KeyEvent.VK_LEFT:                 	
                	moveLeft(falling);
                	break;
                case KeyEvent.VK_DOWN: 
                	tryRotation(falling.rotateClockwise());
                	break;
                case KeyEvent.VK_UP: 
                	tryRotation(falling.rotateCounter());
                	break;
                case KeyEvent.VK_SPACE: 
                	try {
                		hardDrop();
                	} catch (IOException e1) {
					// TODO Auto-generated catch block
                		e1.printStackTrace();				
                	}    
                	break;
                case KeyEvent.VK_F:
                	state = PAUSED;
                	String[] options = {"Basic", "Hacker", "Chicago Cubs", "Rave"};
                	String s = (String) JOptionPane.showInputDialog(null, "What style would you like?", "Customization menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                	if(s == null || (s != null && ("".equals(s))))   
                	{
                	    s = "Basic";
                	}
                	setCustomization(s);
                	state = STARTED;
                	break;
                case KeyEvent.VK_I:
                	state = PAUSED;
                	String[] options2 = {"Basic", "Easy", "Hard"};
                	intensity = (String) JOptionPane.showInputDialog(null, "What intensity would you like?", "Difficulty menu", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);   
                	if(intensity == null || (intensity != null && ("".equals(intensity))))   
                	{
                	    intensity = "Basic";
                	}
                	state = STARTED;
                	break;
                case KeyEvent.VK_M:
                	if(secret == 0)
                	{
                		setOpaque(false);
                		secret = 1;
                	}
                	
                	else
                	{
                		setOpaque(true);
                		secret = 0;
                	}          
                	break;
                case KeyEvent.VK_S:
                	if(shadows == 0)
                	{
                		shadows = 1;
                	}
                	else
                	{
                		shadows = 0;
                	}
            }
        }
    }
}
