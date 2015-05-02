
import java.util.Arrays;

/**
 * Team One
 * Gomoku
 * CSCE 320 - Spring 2015
 * 3/16/2015
 * Java - JVM
 * Sources:
 * 
 * Revisions:
 * 3/14/2015 - Class created by Joseph Bowley.
 */
public class GameBoard {
    private final int[][] board;
    private final int NOTPLAYED = 0;
    private final int PLAYERONE = 1;
    private final int PLAYERTWO = 2;
    
    /**
     * GameBoard constructor.
     * Passes in dimensions of the board.
     * @param x
     * @param y 
     */
    public GameBoard(int x, int y)
    {
        board = new int[x][y];
        for(int i = 0; i < x; i++){
    		for(int k = 0; k <y; k++){
    		board[i][k]=0;
    		}
    	}
        
    }
    
    public GameBoard(){
    	board = new int[30][30];
    	for(int i = 0; i < 30; i++){
    		for(int k = 0; k <30; k++){
    		board[i][k]=0;
    		}
    	}
    }
    /**
     * Makes a move on the board for one of the user player active in a game.
     * @param x
     * @param y
     */
    public void moveMadeSelf(int x, int y)
    {
        	board[x][y] = 1;
        
    }
    
    
    /**
     * Makes a move on the board for one of the opponent player active in a game.
     * @param x
     * @param y
     */
    public void moveMadeOpponent(int x, int y)
    {
        	board[x][y] = 2;
        
    }
    
    /**
     * Returns the representation of the gameBoard
     * @return 
     */
    public int[][] getBoard()
    {
        return board;
    }
    
    /**
     * Returns if there is a five in a row on the board.
     * @return 
     */
    public boolean isFiveInARow()
    {
        return false; // not yet made
    }
    public void printBoard()
    {
    	for(int i = 0; i < 30; i++){
    		for(int k = 0; k <30; k++){
    		System.out.print(board[i][k]);
    		}
    		System.out.println();
    	}
    }
    
    
    /**
     * Checks to see if a selected move is a valid move.
     * @param x
     * @param y
     */
	public boolean validate(int x, int y) {
		if(board[x][y] == 0){
        	return true;
        }else{
        	return false;
        }
	}
}
