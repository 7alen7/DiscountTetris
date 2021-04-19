package tetris;

import java.util.Random;

	/**
	 * Block Object Class
	 * 
	 * <p> Defines the various attributes of a tetris Tetrominoe and how they can be manipulated. 
	 * @author CS321 Group 3
	 * @version 3rd attempt to get this project to work.
	 *
	 */
public class Block {
	
	/**
	 *  A figure constant used to represent that the block is void
	 */
	public static final int NO_BLOCK = 0;

	/**
	 * A figure constant used to create a figure forming a square.
	 */
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
	
	/**
	 * A figure constant used to create a Ghost/Shadow block. Used to produce shadows in the board.
	 */
	public static final int GHOST = 8;

		/**
		 * x position of the entire tetrominoe. Used to find the central location of a cluster of 4 blocks.
		 */
		private int xPos;
		
		/**
		 * y position of the entire tetrominoe. Used to find the central location of a cluster of 4 blocks.
		 */
		private int yPos;
		
		/**
		 * Used to hold the type of block the object refers to. Initially defined as NO_BLOCK (int 0).
		 */
		private int type = NO_BLOCK;
	    	
		
		// Array of values that dictate where individual squares go
		/**
		 * Double array used to hold the values of each individual square in a tetrominoe. 
		 * x_y[4][2]: 4 is the number of squares in tetrominoe, 2 is used for x vs y of that square.
		 * EX: x_y[1][2] - 1 refers to the second square in a tetrominoe, and the 2 denotes that it is a Y value we're dealing with
		 */
		private int[][] x_y; 
	    
		/**
		 * Integer dictating the tetrominoe's current rotation status.  Initially 0, max value of 3. Will be reset to 0 if higher than 3.
		 */
	    private int rotateCount; // dictates what position the block is in {0, 1, 2, 3} for 4 possible rotations

	    
	    /**
	     * Constructor used to create a new tetrominoe. Sets xPos and yPos to 0. Initializes x_y array. 
	     */
	    public Block() // creates new block
	    {

	    	xPos = 0; // middle of block xPos
	    	yPos = 0; // middle of block yPos
	        x_y = new int[4][2]; // hold deviation from center of main block
	    }
	    
	    /**
             * Get the x position of the block.
             * 
             * @return The current x position of the block
             */
	    public int getXpos()
	    {
	    	return xPos;
	    }
	    
	    /**
             * Get the y position of the block.
             * 
             * @return The current y position of the block
             */
	    public int getYpos()
	    {
	    	return yPos;
	    }
	    
	    /**
             * Set the x position of the block.
             * 
             * @param x The new x position for the block
             */
	    public void setXpos(int x)
	    {
	    	xPos = x;
	    }
	    
	    /**
             * Set the y position of the block.
             * 
             * @param y The new y position for the block
             */
	    public void setYpos(int y)
	    {
	    	yPos = y;
	    }
	    
	    /**
             * Set the values of a block depending on the desired shape.
             * 
             * @param blockType The type of block shape to create
             */
            // Look to LINE_FIGURE for examples, think of them as coordinates on X/Y grid
	    public void setBlock(int blockType) { // set individual square's x values based on block type

                // Set block shape type based on blockType passed to function
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
	        	x_y[0][0] = -1; // square 1 x val
	        	x_y[0][1] = 0; // square 1 y val
	        	
	        	x_y[1][0] = 0; // square 2 x val
	    	    x_y[1][1] = 0; // square 2 y val
	    	    
	    	    x_y[2][0] = 1; // square 3 x val
	    	    x_y[2][1] = 0; // square 3 y val
	    	    
	    	    x_y[3][0] = 2; // square 4 x val
	    	    x_y[3][1] = 0; // square 4 y val
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

            /**
             * Get a specific square's x value.
             * 
             * @param squareNum The number of the square to get the x value of
             * @return returns the X value of the square specified (x_y[square specified][0])
             */
	    public int getSquareX(int squareNum) 
	    {

	        return x_y[squareNum][0]; // gets certain square's x value
	    }
	    
            /**
             * Set a specific square's x value.
             * 
             * @param squareNum The number of the square to set the x value of
             * @param val The x value to set
             */
	    public void setSquareX(int squareNum, int val)
	    {
	    	x_y[squareNum][0] = val; // sets certain squares's x val
	    }

            /**
             * Get a specific square's y value.
             * 
             * @param squareNum The number of the square to get the y value of
             * @return returns the Y value of the square specified (x_y[square specified][1])
             */
	    public int getSquareY(int squareNum) 
	    {

	        return x_y[squareNum][1]; // gets certain square's y val
	    }
	    
            /**
             * Set a specific square's y value.
             * 
             * @param squareNum The number of the square to set the y value of
             * @param val The y value to set
             */
	    public void setSquareY(int squareNum, int val)
	    {
	    	x_y[squareNum][1] = val; // sets certain square's y val
	    }

            /**
             * Get the shape type of the block.
             * 
             * @return The shape type of the block
             */
	    public int getType()
	    {
	    	return type;
	    }

            /**
             * Get the furthest left square of the block.
             * 
             * @return The x position of the furthest left square
             */
	    public int furthestLeftSquare() // finds the furthest left square of block grouping
	    {

	        int[] vals = {x_y[0][0], x_y[1][0], x_y[2][0], x_y[3][0]}; // array of block's x positions
	        int left = vals[0];
	        
                // Loop through x values
	        for(int x = 0; x < 3; x++)
	        {
                    // If current value is further left than current furthest left value
	        	if(vals[x] < left)
	        	{
                                // Update furthest left value
	        		left = vals[x];
	        	}
	        }

	        return left;
	    }
	    
            /**
             * Get the furthest right square of the block.
             * 
             * @return The x position of the furthest right square
             */
	    public int furthestRightSquare() // same ^ but right
	    {

		        int[] vals = {x_y[0][0], x_y[1][0], x_y[2][0], x_y[3][0]};
		        int right = vals[0];
		        
                        // Loop through x values
		        for(int x = 0; x < 3; x++)
		        {
                                // If current value is further right than current furthest right value
		        	if(vals[x] > right)
		        	{
                                        // Update furthest right value
		        		right = vals[x];
		        	}
		        }

		        return right;
	    }

            /**
             * Get the lowest square of the block.
             * 
             * @return The y position of the lowest square
             */
	    public int getLowestSquare() // finds lowest square in grouping
	    {
	    	int[] vals = {x_y[0][1], x_y[1][1], x_y[2][1], x_y[3][1]}; // array of all y positions
	    	int lowest = vals[0];
        
                // Loop through y values
	    	for(int x = 0; x < 3; x++)
	    	{
                        // If current value is lower than lowest value so far
	    		if(vals[x] > lowest)
	    		{
                                // Update lowest value
	    			lowest = vals[x];
	    		}
	    	}
	    	return lowest;
        }
	    
            /**
             * Get the highest square of the block.
             * 
             * @return The y position of the highest square
             */
	    public int getHighestSquare() // finds highest square in grouping
	    {
	    	int[] vals = {x_y[0][1], x_y[1][1], x_y[2][1], x_y[3][1]};
	    	int highest = vals[0];
        
                // Loop through y values
	    	for(int x = 0; x < 3; x++)
	    	{
                        // If current value is higher than highest value so far
	    		if(vals[x] > highest)
	    		{
                            // Update highest value
	    	            highest = vals[x];
	    		}
	    	}
                
	    	return highest;
	    }
	    
            /**
             * Get the rotational state of the block.
             * 
             * @return The rotation count
             */
	    public int getRotation() // returns rotation position
	    {
	    	return rotateCount;
	    }
	    
            /**
             * Set the rotational state of the block.
             * 
             * @param num The new rotational count
             */
	    public void setRotationCount(int num)
	    {
	    	rotateCount = num;
	    }
	    
            /**
             * Generates and returns a random block.
             * 
             * @return The new random block object
             */
	    public static Block randomBlock() // returns random block (cannot be ghost or no_block objects)
	    {

                // Create new random number generator
	        Random r = new Random();
                
                // Generate random number
	        int x = 0;
	        while(x == 0)
	        {
	        	x = Math.abs(r.nextInt()) % 8;
	        }
                
                // Create new block with randomized number as type
	        Block newBlock = new Block();
	        newBlock.setBlock(x);
	        
	        return newBlock;
	    }

            /**
             * Rotates the block counter-clockwise. Will alter the current block's
             * 
             * @return The newly rotated block
             */
	    public Block rotateCounter() // rotates the block counter-clockwise
	    {

                // Wrap rotation count greater than or equal to 4
	    	if(rotateCount >= 4)
	    	{
	    		rotateCount = 0;
	    	}
                
                // Create new block
	    	Block rotated = new Block();
	        rotated.type = type;
                
                // Decrement rotation count
	        rotateCount--;
                
                // Wrap rotation count greater than or equal to 4
	        if(rotateCount < 0)
	    	{
	    		rotateCount = 3;
	    	}
                
                // Store rotation count in new block
	        rotated.rotateCount = rotateCount;
	        
	        
	        // Don't rotate square blocks (there is no need)
	        if(rotated.type == SQUARE_FIGURE) // no need to rotate a block
	        {
	        	// Essentially do nothing
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
	        
                // Rotate block based on shape
	        else
	        {
	        	if(rotated.type == LINE_FIGURE) // based on rotateCount, positions the block with new x/y coords for all squares
	        	{
				//Think of this like a grid with x/y positions
	        		if(rotateCount == 0)
	        		{
	    	        	rotated.x_y[0][0] = -1; //x
	    	        	rotated.x_y[0][1] = 0; //y
	    	        	
	    	        	rotated.x_y[1][0] = 0; //x
	    	    	    rotated.x_y[1][1] = 0; //y
	    	    	    
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
	        	
	        	else if(rotated.type == S_FIGURE) // positions s block based on rotate count
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

	        	else if(rotated.type == Z_FIGURE) //
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
	        		else if(rotateCount == 3) //
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
	        	
	        	else if(rotated.type == RIGHT_ANGLE_FIGURE) //
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
             
                // Store rotation counter in new block
                rotated.rotateCount = rotateCount;               
                
                return rotated;
}
            /**
             * Rotates the block clockwise.
             * 
             * @return The newly rotated block
             */
	    public Block rotateClockwise() // rotates the block clockwise
	    {

                // Wrap rotation count greater than or equal to 4
	    	if(rotateCount >= 4)
	    	{
	    		rotateCount = 0;
	    	}
                
                // Create new block
	    	Block rotated = new Block();
	        rotated.type = type;
	        
	        // Don't rotate square blocks (there is no need)
	        if(rotated.type == SQUARE_FIGURE)
	        {
	        	// Essentially do nothing, again
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
	        
                // Rotate block based on shape
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
            
            // Increment rotation counter and store in new block
	    rotateCount++;
	    rotated.rotateCount = rotateCount;
            
	    return rotated;
	 }
}
