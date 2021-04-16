package tetris;

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



public class Board extends JPanel {

	private final int WIDTH = 10;
	private final int HEIGHT = 22;
	
	private int linesRemoved = 0;		
	
	private int state;
	public static final int STARTED =  1;
    public static final int GAMEOVER = 2;
    public static final int PAUSED =   3;
    
    private boolean blockState = false;
    
    private int score = 0;
    private int BackToBackCount = 1;
    private boolean removedLastRound = false;
    
    private int lines = 0;

    
    private Block falling;
    private boolean isFalling = true;
    private int[][] block;
    
    Timer timer;
    
    private String customizationOption = BASIC;
    
    public static final String BASIC =   	"Basic";
    public static final String HACKER =    "Hacker";
    public static final String CUBS ="Chicago Cubs";
    public static final String RAVE =        "Rave";
    private int raveSetting = 0;
    private int raveSetting2 = 0;
    
    private String intensity = "Basic";
    public static final String BASIC_INTENSITY = "Basic";
    public static final String HARD = 			  "Hard";
    public static final String EASY = 			  "Easy";
    
    
    private int shadows = 0;
    
    private int secret = 0;
    
    public JLabel newLabel = new JLabel("");

    public Board() 
    {
    	block = new int[WIDTH][HEIGHT];
    	setFocusable(true);
        addKeyListener(new keyPress());
        
        newLabel.setBounds(0, 0, 400, 400);
        add(newLabel);
        
    }

    public int getBlockWidth()
    {
    	return 350 / WIDTH;
    }

    public int getBlockHeight()
    {
    	return 400 / HEIGHT;
    }
    
    
    public int getState()
    {
    	return state;
    }

    public void start() throws IOException 
    {    	
    	
    	state = STARTED;
    	
   	 	score = 0;
        falling = new Block();
        block = new int [HEIGHT][WIDTH];
        reset();

        timer = new Timer(300, new everySecond());
        timer.start();
        newBlock();

    }
    public int getScore()
    {
    	return score;
    }

    private int getShape(int x, int y) 
    {
    	return block[y][x];
    }

    public void quit(int doMessage)
    {
    	int i = 0;
    	state = GAMEOVER;
    	JOptionPane.showMessageDialog(null, "Game Over!\nFinal Score: " + score, "Alert!", i);
    	linesRemoved = 0;
    	timer.stop();
    }

    @Override
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

    private void oneLineDown() throws IOException 
    {

        if (!moveDownOne(falling) && state != GAMEOVER && state != PAUSED) 
        {

            isFalling = false;
        }
    }

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

    private void newBlock() throws IOException 
    {
    	System.out.println(score);
    	score += 10;
    	if(!placeBlock(falling))
    	{
    		falling.setBlock(Block.NO_BLOCK);
            timer.stop();
            
            quit(1);
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
    
    private boolean moveLeft(Block block2)
    {
    	int x, y;
    	int xVals[] = new int[4];
    	int yVals[] = new int[4];
    	
    	boolean failed = false;
    	
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
    
    private boolean moveRight(Block block2)
    {
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
    
    private boolean moveDownOne(Block block2)
    {
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

    private void removeLines() 
    {
    	int[] types = new int[10];
        int numFullLines = 0;
        boolean fullLine = true;       
        
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
        	
            lines += numFullLines;
            
            if(intensity.equals(BASIC_INTENSITY))
            {
            	switch(numFullLines)
            	{
            		case 1:
            			score += 40;
            			break;
            		case 2:
            			score += 100;
            			break;
            		case 3:
            			score += 300;
            			break;
            		case 4:
            			score += 1200;
            			break;
            	}
            }
            
            else if(intensity.equals(HARD))
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
            
            else if(intensity.equals(EASY))
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
    	
    	else if(customizationOption == HACKER)
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
    	
    	else if(customizationOption == CUBS)
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
    		
    		else if(customizationOption == RAVE)
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
    
    public void setCustomization(String style)
    {
    	customizationOption = style;
    }

    private class everySecond implements ActionListener {

        @Override
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

    class keyPress extends KeyAdapter {

        @Override
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
