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
	public static final int RIGHT_ANGLE_FIGURE = 1;

	      /**
	       * A figure constant used to create a figure forming a left angle.
	       */
	public static final int LEFT_ANGLE_FIGURE = 6;

	      /**
	       * A figure constant used to create a figure forming a triangle.
	       */
	public static final int TRIANGLE_FIGURE = 7;


		private int type;
	    private int[][] x_y;
	    
        int[][][] x_yTable = new int[][][]{
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
            {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
            {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
            {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
            {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

	    public Block() {

	        x_y = new int[4][2];
	        createBlock(NO_BLOCK);
	    }
	    
	    

	    void createBlock(int blockType) {

	        for (int i = 0; i < 4; i++) {
	        	for(int j = 0; j < 2; j++)
	        	{
	        		x_y = x_yTable[blockType];
	        	}
	        }

 	        type = blockType;
	    }
	    
	    private void setX(int index, int x) {

	        x_y[index][0] = x;
	    }

	    private void setY(int index, int y) {

	        x_y[index][1] = y;
	    }

	    public int get_x(int index) {

	        return x_y[index][0];
	    }

	    public int get_y(int index) {

	        return x_y[index][1];
	    }

	    public int getType()
	    {
	    	return type;
	    }

	    
	    void randomBlock() 
	    {

	        Random r = new Random();
	        int x = Math.abs(r.nextInt()) % 7 + 1;

	        createBlock(x);
	    }

	    public int minX() 
	    {

	        int m = x_y[0][0];

	        for (int i = 0; i < 4; i++) {

	            m = Math.min(m, x_y[i][0]);
	        }

	        return m;
	    }


	    int minY() 
	    {

	        int m = x_y[0][1];

	        for (int i = 0; i < 4; i++) {

	            m = Math.min(m, x_y[i][1]);
	        }

	        return m;
	    }

	    public Block rotateCounter() 
	    {

	        if (type == SQUARE_FIGURE) {

	            return this;
	        }

	        Block result = new Block();
	        result.type = type;

	        for (int i = 0; i < 4; i++) {

	            result.setX(i, get_y(i));
	            result.setY(i, -1 * get_x(i));
	        }

	        return result;
	    }

	    public Block rotateClockwise() 
	    {

	        if (type == SQUARE_FIGURE) {

	            return this;
	        }

	        Block result = new Block();
	        result.type = type;

	        for (int i = 0; i < 4; i++) {

	            result.setX(i, -1 * get_y(i));
	            result.setY(i, get_x(i));
	        }

	        return result;
	    }
}

