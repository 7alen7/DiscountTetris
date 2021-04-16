package tetris;

import java.util.Random;

public class Block {
	
	public static final int NO_BLOCK = 0;

	public static final int SQUARE_FIGURE = 5;

	      /**
	       * A figure constant used to create a figure forming a line.
	       */
	public static final int LINE_FIGURE = 2;

	      /**
	       * A figure constant used to create a figure forming an "S".
	       */
	public static final int S_FIGURE = 3;

	      /**
	       * A figure constant used to create a figure forming a "Z".
	       */
	public static final int Z_FIGURE = 4;

	      /**
	       * A figure constant used to create a figure forming a right angle.
	       */
	public static final int RIGHT_ANGLE_FIGURE = 6;

	      /**
	       * A figure constant used to create a figure forming a left angle.
	       */
	public static final int LEFT_ANGLE_FIGURE = 1;

	      /**
	       * A figure constant used to create a figure forming a triangle.
	       */
	public static final int TRIANGLE_FIGURE = 7;
	
	public static final int GHOST = 8;

		private int xPos;
		private int yPos;
		
		private int type = NO_BLOCK;
	    private int[][] x_y;	 
	    
	    private int rotateCount;

	    public Block() 
	    {

	    	xPos = 0;
	    	yPos = 0;
	        x_y = new int[4][2]; // hold deviation from center of main block
	    }
	    
	    public int getXpos()
	    {
	    	return xPos;
	    }
	    
	    public int getYpos()
	    {
	    	return yPos;
	    }
	    
	    public void setXpos(int x)
	    {
	    	xPos = x;
	    }
	    
	    public void setYpos(int y)
	    {
	    	yPos = y;
	    }
	    
	    

	    public void setBlock(int blockType) {

	        if(blockType == NO_BLOCK)
	        {
	        	x_y[0][0] = 0;
	        	x_y[0][1] = 0;
	        	x_y[1][0] = 0;
	    	    x_y[1][1] = 0;
	    	    x_y[2][0] = 0;
	    	    x_y[2][1] = 0;
	    	    x_y[3][0] = 0;
	    	    x_y[3][1] = 0;
	        			
	        }
	        
	        else if(blockType == S_FIGURE)
	        {
	    	    x_y[0][0] = 0; // [x][0] = X val
	        	x_y[0][1] = -1; // [x][1] = y val
	        	
	        	x_y[1][0] = 0;
	    	    x_y[1][1] = 0;
	    	    
	    	    x_y[2][0] = -1;
	    	    x_y[2][1] = 0;
	    	    
	    	    x_y[3][0] = -1;
	    	    x_y[3][1] = 1;
	        }
	        
	        else if(blockType == Z_FIGURE)
	        {
	    	    x_y[0][0] = 0;
	        	x_y[0][1] = -1;
	        	
	        	x_y[1][0] = 0;
	    	    x_y[1][1] = 0;
	    	    
	    	    x_y[2][0] = 1;
	    	    x_y[2][1] = 0;
	    	    
	    	    x_y[3][0] = 1;
	    	    x_y[3][1] = 1;
	        }
	        
	        else if(blockType == RIGHT_ANGLE_FIGURE)
	        {
	        	
	    	    x_y[0][0] = 1;
	        	x_y[0][1] = -1;
	        	
	        	x_y[1][0] = 0;
	    	    x_y[1][1] = -1;
	    	    
	    	    x_y[2][0] = 0;
	    	    x_y[2][1] = 0;
	    	    
	    	    x_y[3][0] = 0;
	    	    x_y[3][1] = 1;
	        }
	        
	        else if(blockType == LEFT_ANGLE_FIGURE)
	        {	    	    
	        	x_y[0][0] = -1;
	        	x_y[0][1] = -1;
	        	
	        	x_y[1][0] = 0;
	    	    x_y[1][1] = -1;
	    	    
	    	    x_y[2][0] = 0;
	    	    x_y[2][1] = 0;
	    	    
	    	    x_y[3][0] = 0;
	    	    x_y[3][1] = 1;
	    	    
	        }
	        
	        else if(blockType == TRIANGLE_FIGURE)
	        {
	        	x_y[0][0] = -1;
	        	x_y[0][1] = 0;
	        	
	        	x_y[1][0] = 0;
	    	    x_y[1][1] = 0;
	    	    
	    	    x_y[2][0] = 1;
	    	    x_y[2][1] = 0;
	    	    
	    	    x_y[3][0] = 0;
	    	    x_y[3][1] = 1;
	        }
	        
	        else if(blockType == LINE_FIGURE)
	        {
	        	x_y[0][0] = -1;
	        	x_y[0][1] = 0;
	        	
	        	x_y[1][0] = 0;
	    	    x_y[1][1] = 0;
	    	    
	    	    x_y[2][0] = 1;
	    	    x_y[2][1] = 0;
	    	    
	    	    x_y[3][0] = 2;
	    	    x_y[3][1] = 0;
	        }
	        
	        else if(blockType == SQUARE_FIGURE)
	        {
	        	x_y[0][0] = 0;
	        	x_y[0][1] = 0;
	        	
	        	x_y[1][0] = 1;
	    	    x_y[1][1] = 0;
	    	    
	    	    x_y[2][0] = 0;
	    	    x_y[2][1] = 1;
	    	    
	    	    x_y[3][0] = 1;
	    	    x_y[3][1] = 1;
	        }
	        

 	        type = blockType;
	    }

	    public int getSquareX(int squareNum) 
	    {

	        return x_y[squareNum][0];
	    }
	    
	    public void setSquareX(int squareNum, int val)
	    {
	    	x_y[squareNum][0] = val;
	    }

	    public int getSquareY(int squareNum) 
	    {

	        return x_y[squareNum][1];
	    }
	    
	    public void setSquareY(int squareNum, int val)
	    {
	    	x_y[squareNum][1] = val;
	    }

	    public int getType()
	    {
	    	return type;
	    }

	    
	    public int furthestLeftSquare() 
	    {

	        int[] vals = {x_y[0][0], x_y[1][0], x_y[2][0], x_y[3][0]};
	        int left = vals[0];
	        
	        for(int x = 0; x < 3; x++)
	        {
	        	if(vals[x] < left)
	        	{
	        		left = vals[x];
	        	}
	        }

	        return left;
	    }
	    
	    public int furthestRightSquare()
	    {

		        int[] vals = {x_y[0][0], x_y[1][0], x_y[2][0], x_y[3][0]};
		        int right = vals[0];
		        
		        for(int x = 0; x < 3; x++)
		        {
		        	if(vals[x] > right)
		        	{
		        		right = vals[x];
		        	}
		        }

		        return right;
	    }

	    public int getLowestSquare()
	    {
	    	int[] vals = {x_y[0][1], x_y[1][1], x_y[2][1], x_y[3][1]};
	    	int lowest = vals[0];
        
	    	for(int x = 0; x < 3; x++)
	    	{
	    		if(vals[x] > lowest)
	    		{
	    			lowest = vals[x];
	    		}
	    	}
	    	return lowest;
        }
	    	
	    public int getHighestSquare()
	    {
	    	int[] vals = {x_y[0][1], x_y[1][1], x_y[2][1], x_y[3][1]};
	    	int highest = vals[0];
        
	    	for(int x = 0; x < 3; x++)
	    	{
	    		if(vals[x] > highest)
	    		{
	    			highest = vals[x];
	    		}
	    	}
	    	return highest;
	    }
	    
	    public int getRotation()
	    {
	    	return rotateCount;
	    }
	    
	    public void setRotationCount(int num)
	    {
	    	rotateCount = num;
	    }
	    
	    public static Block randomBlock() 
	    {

	        Random r = new Random();
	        int x = 0;
	        
	        while(x == 0)
	        {
	        	x = Math.abs(r.nextInt()) % 8;
	        }
	        
	        Block newBlock = new Block();

	        newBlock.setBlock(x);
	        
	        return newBlock;
	    }

	    public Block rotateCounter() 
	    {

	    	if(rotateCount >= 4)
	    	{
	    		rotateCount = 0;
	    	}
	    	Block rotated = new Block();
	        rotated.type = type;
	        rotateCount--;
	        
	        if(rotateCount < 0)
	    	{
	    		rotateCount = 3;
	    	}
	        rotated.rotateCount = rotateCount;
	        
	        
	        
	        if(rotated.type == SQUARE_FIGURE)
	        {
	        	rotated.x_y[0][0] = 0;
	        	rotated.x_y[0][1] = 0;
	        	
	        	rotated.x_y[1][0] = 1;
	        	rotated.x_y[1][1] = 0;
	    	    
	        	rotated.x_y[2][0] = 0;
	        	rotated.x_y[2][1] = 1;
	    	    
	        	rotated.x_y[3][0] = 1;
	        	rotated.x_y[3][1] = 1;
	    	    
	    	    return rotated;
	        }
	        
	        else if(rotated.type == NO_BLOCK)
	        {
	        	// do nothing
	        }
	        
	        else
	        {
	        	if(rotated.type == LINE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	    	        	rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 2;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = 2;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = -2;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = -1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -2;
	        		}
	        	}
	        	
	        	else if(rotated.type == S_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	   rotated.x_y[2][0] = 0;
	    	    	   rotated.x_y[2][1] = -1;
	    	    	    
	    	    	   rotated.x_y[3][0] = -1;
	    	    	   rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 1;
	        		}
	        }

	        	else if(rotated.type == Z_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = 0;
	        			rotated.x_y[0][1] = -1;
	        	
	        			rotated.x_y[1][0] = 0;
	        			rotated.x_y[1][1] = 0;
	    	    
	    	    		rotated.x_y[2][0] = 1;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	    		rotated.x_y[3][0] = 1;
	    	    		rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	   	rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 1;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = -1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        	}
	        	
	        	else if(rotated.type == RIGHT_ANGLE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = 1;
	        			rotated.x_y[0][1] = -1;
	        	
	        			rotated.x_y[1][0] = 0;
	    	    		rotated.x_y[1][1] = -1;
	    	    
	    	    		rotated.x_y[2][0] = 0;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	    		rotated.x_y[3][0] = 0;
	    	    		rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	   	rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 0;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 1;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = -1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        	}

	        	else if(rotated.type == LEFT_ANGLE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = -1;
	        			rotated.x_y[0][1] = -1;
	        	
	        			rotated.x_y[1][0] = 0;
	    	    		rotated.x_y[1][1] = -1;
	    	    
	    	    		rotated.x_y[2][0] = 0;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	   			rotated.x_y[3][0] = 0;
	    	   			rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	   	rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 0;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 1;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = -1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        	}

	        	else if(rotated.type == TRIANGLE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = -1;
	        			rotated.x_y[0][1] = 0;
	        	
	        			rotated.x_y[1][0] = 0;
	        			rotated.x_y[1][1] = 0;
	    	    
	    	    		rotated.x_y[2][0] = 1;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	    		rotated.x_y[3][0] = 0;
	    	    		rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	    rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 1;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = -1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        	}
	        }
	    rotated.rotateCount = rotateCount;
        return rotated;
}

	    public Block rotateClockwise() 
	    {

	    	if(rotateCount >= 4)
	    	{
	    		rotateCount = 0;
	    	}
	    	Block rotated = new Block();
	        rotated.type = type;
	        
	        
	        if(rotated.type == SQUARE_FIGURE)
	        {
	        	rotated.x_y[0][0] = 0;
	        	rotated.x_y[0][1] = 0;
	        	
	        	rotated.x_y[1][0] = 1;
	        	rotated.x_y[1][1] = 0;
	    	    
	        	rotated.x_y[2][0] = 0;
	        	rotated.x_y[2][1] = 1;
	    	    
	        	rotated.x_y[3][0] = 1;
	        	rotated.x_y[3][1] = 1;
	    	    
	    	    return rotated;
	        }
	        
	        else if(rotated.type == NO_BLOCK)
	        {
	        	// do nothing
	        }
	        
	        else
	        {
	        	if(rotated.type == LINE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	    	        	rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 2;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = 2;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = -2;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = -1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -2;
	        		}
	        	}
	        	
	        	else if(rotated.type == S_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	   rotated.x_y[2][0] = 0;
	    	    	   rotated.x_y[2][1] = -1;
	    	    	    
	    	    	   rotated.x_y[3][0] = -1;
	    	    	   rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 1;
	        		}
	        }

	        	else if(rotated.type == Z_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = 0;
	        			rotated.x_y[0][1] = -1;
	        	
	        			rotated.x_y[1][0] = 0;
	        			rotated.x_y[1][1] = 0;
	    	    
	    	    		rotated.x_y[2][0] = 1;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	    		rotated.x_y[3][0] = 1;
	    	    		rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	   	rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 1;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = -1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        	}
	        	
	        	else if(rotated.type == RIGHT_ANGLE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = 1;
	        			rotated.x_y[0][1] = -1;
	        	
	        			rotated.x_y[1][0] = 0;
	    	    		rotated.x_y[1][1] = -1;
	    	    
	    	    		rotated.x_y[2][0] = 0;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	    		rotated.x_y[3][0] = 0;
	    	    		rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	   	rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 0;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 1;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = -1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        	}

	        	else if(rotated.type == LEFT_ANGLE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = -1;
	        			rotated.x_y[0][1] = -1;
	        	
	        			rotated.x_y[1][0] = 0;
	    	    		rotated.x_y[1][1] = -1;
	    	    
	    	    		rotated.x_y[2][0] = 0;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	   			rotated.x_y[3][0] = 0;
	    	   			rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	   	rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 0;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 1;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = -1;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = -1;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        	}

	        	else if(rotated.type == TRIANGLE_FIGURE)
	        	{
	        		if(rotateCount == 0)
	        		{
	        			rotated.x_y[0][0] = -1;
	        			rotated.x_y[0][1] = 0;
	        	
	        			rotated.x_y[1][0] = 0;
	        			rotated.x_y[1][1] = 0;
	    	    
	    	    		rotated.x_y[2][0] = 1;
	    	    		rotated.x_y[2][1] = 0;
	    	    
	    	    		rotated.x_y[3][0] = 0;
	    	    		rotated.x_y[3][1] = 1;
	        		}
	        		else if(rotateCount == 1)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = -1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    				
	    	    	    rotated.x_y[2][0] = 0;
	    	    	   	rotated.x_y[2][1] = 1;
	    	    	    				
	    	    	    rotated.x_y[3][0] = -1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        		else if(rotateCount == 2)
	        		{
	        			rotated.x_y[0][0] = 1;
	    	        	rotated.x_y[0][1] = 0;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = -1;
	    	    	    rotated.x_y[2][1] = 0;
	    	    	    
	    	    	    rotated.x_y[3][0] = 0;
	    	    	    rotated.x_y[3][1] = -1;
	        		}
	        		else if(rotateCount == 3)
	        		{
	        			rotated.x_y[0][0] = 0;
	    	        	rotated.x_y[0][1] = 1;
	    	        	
	    	        	rotated.x_y[1][0] = 0;
	    	    	    rotated.x_y[1][1] = 0;
	    	    	    
	    	    	    rotated.x_y[2][0] = 0;
	    	    	    rotated.x_y[2][1] = -1;
	    	    	    
	    	    	    rotated.x_y[3][0] = 1;
	    	    	    rotated.x_y[3][1] = 0;
	        		}
	        	}
	        }
	    rotateCount++;
	    rotated.rotateCount = rotateCount;
        return rotated;
	 }
}

