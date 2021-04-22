/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uah.cs.cs321.tetris2;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkryn
 */
        
public class CreateBlock {
    
    public static Block newBlock()
    {
        Block block = new Block();
            
    	block = Block.randomBlock();
        return block;
    } 
    
    private static Block getFirstBlock() {
         
        Block firstBlock = new Block();
        nextBlock();
        firstBlock = getNextBlock();
        nextBlock();
        return firstBlock;
    }
     
    private static void nextBlock() {
        
        nextBlock = newBlock();
        int type = nextBlock.getType();

     }
     
    public static Block getNextBlock() {
       
        return nextBlock;    
    }
     
    public static Block getCurrentBlock() {
        
        Block currentBlock = new Block();

        if(i == 0) {
            currentBlock = getFirstBlock();
            i = 1;
            return currentBlock;
        }
        else 
        {
            currentBlock = nextBlock;
            nextBlock();
            return currentBlock;
        }
    }
    
    private static Block nextBlock = new Block();
    static int i = 0;
}
