package tetris;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to keep track of the current and next block on the board
 * during a game of Tetris.
 * 
 */
        
public class BlockTracker {
        
   /**
    *  Block object used to hold next block to be deployed on board.
    */
    private static Block nextBlock = new Block();
    /**
    *  Flag to indicate whether the current block is the first block to be deployed.
    */
    static int i = 0;
        
    /**
    * Sets block object to a random block.
    * @return block object to be deployed on the board.
    */
    public static Block newBlock()
    {
        Block block = new Block();
            
    	block = Block.randomBlock();
        return block;
    } 
    
    /**
    * Gets the first block to be deployed on the board
    * when Tetris is first initiated.
    * @return block object to be set onto board.
    */
    private static Block getFirstBlock() {
         
        Block firstBlock = new Block();
        nextBlock();
        firstBlock = getNextBlock();
        nextBlock();
        return firstBlock;
    }
    
    /**
    *  Used to initialize the next block to a new block.
    */  
    private static void nextBlock() {
        
        nextBlock = newBlock();
     }
     
    /**
    * Used to get the nextBlock to be deployed onto the board.
    * @return next block objext to be displayed on the board.
    */
    public static Block getNextBlock() {
       
        return nextBlock;    
    }
     
     /**
     * Gets current block to be deployed on the board.
     * @return block object to be deployed on the board.
     */
    public static Block getCurrentBlock() {
        
        Block currentBlock = new Block();
         
        // If game of Tetris just started, must get the first block
        if(i == 0) {
            currentBlock = getFirstBlock();
            i = 1;
            return currentBlock;
        }
        else // Set next block to current block.
        {
            currentBlock = nextBlock;
            nextBlock();
            return currentBlock;
        }
    }
    

}
