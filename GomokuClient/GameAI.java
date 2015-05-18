import java.util.Random;

import javax.swing.DefaultListModel;

/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 5/5/2015 - Class created by Jon Julius and Carl Derline.
 * Implemented Easy AI, specifically make move and send move methods.
 * 5/6/2015 - Created analyze game board for hard. 
 * 5/8/2015 - Finished analyze game board for hard, and
 * finalized hard ai and moderate ai. Revised easy AI for efficiency.
 * 5/11/2015 - Commented and clean up code. 
 * 
 */ class GameAI {
    private int difficulty;
    private GameModel model;
    private Constants consts = new Constants();
    private Random randomGenerator = new Random();
    private GameController controller;
    private DefaultListModel<String> validMoves;
    
    /**
     * Game AI Constructor
     * @param gc the game controller
     * @param difficulty 
     */
    public GameAI(String diff, GameController gc)
    {
    	
        if(diff.equals(consts.EASY)){
        	difficulty = 0;
        }else if(diff.equals(consts.MODERATE)){
        	difficulty = 1;
        }else if(diff.equals(consts.HARD)){
        	difficulty = 2;
        }
        model = new GameModel();
        controller = gc;
        model.setTurnOrder(false);
        validMoves = new DefaultListModel<String>();
    }
    
   
    /**
     * Makes a move in the game board as an opponent player. calls the
	 * appropriate analyzeGameBoard depending on the difficulty.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void makeMove(int x, int y)
    {
    	updateValidMoveList();
    	String[] move;
    	if(difficulty == 0){
	      int k = randomGenerator.nextInt(validMoves.getSize()+1);
	     move = validMoves.getElementAt(k).split(" ");
	     int i = Integer.parseInt(move[0]);
	     int j = Integer.parseInt(move[1]);
	     model.makeMove(i,j);
	     controller.aiMove(i,j);
    	}
    	if (difficulty == 1){
    		analyzeGameboardMedium(x, y);
    	}
    	if (difficulty == 2){
	    	 analyzeGameboardHard(x , y);
	    	 
    	}
    }

	/**
	 * Analyzes the gameboard and determines the direction of the longest
	 * number of pieces the opponent has in a row using our search methods. 
	 * calls aiMakeMove with that direction.
	 * @param int x the x coordinate
	 * @param int y the y coordinate
	 */
	private void analyzeGameboardHard(int x, int y) {
		String direction = "";
		int max = 0;
		if(horizontalSearch(x,y)> max){
			max = horizontalSearch(x,y);
			direction = consts.HORIZONTAL;
		}
		if(verticalSearch(x,y) > max) {
			max = verticalSearch(x,y);
			direction = consts.VERTICAL;
		}
		if(diagonalFowardSearch(x,y)> max){
			max = diagonalFowardSearch(x,y);
			direction = consts.DIAGONALFOWARD;
		}
		if(diagonalBackSearch(x,y)> max){
			max = diagonalBackSearch(x,y);
			direction = consts.DIAGONALBACK;
		}
		
		
		
		aiMakeMove(x,y,direction);
		
	}
	
	/**
	 * The AI makes a move based on the direction that is passed in.
	 * The method searches the board in that direction until a blank
	 * piece or an opponent piece is encountered. if a blank is encountered,
	 * the AI places its piece there, else it puts its piece at the other end
	 * of the opponents row.
	 * @param int x the x coordinate
	 * @param int y the y coordinate
	 * @param String dir the direction passed in from analyzeGameBoard;
	 */
	public void aiMakeMove(int x, int y, String dir)
	{
		boolean flag = true;
		int i = x - 1, j = y;
		if(dir.equals(consts.HORIZONTAL)){
			while ( i >= 0 && model.getBoardValue(i, y) == 1) {
				i--;
        	}
			if(i >= 0 && model.getBoardValue(i, y) == 0){
				model.makeMove(i, y);
				controller.aiMove(i, y);
				flag = false;
			}else{
				i = x+1;
				while(flag && i < 30 && model.getBoardValue(i, y) == 1){
					i++;
	        	}
				if(i < 30 && model.getBoardValue(i, y) == 0){
					model.makeMove(i, y);
					controller.aiMove(i, y);
					flag = false;
				}
			}
		
		}
		i = x;
		j = y-1;
		if(dir.equals(consts.VERTICAL)){
			while ( j >= 0 && model.getBoardValue(i, j) == 1) {
				j--;
        	}
			if(i >= 0 && model.getBoardValue(i, j) == 0){
				model.makeMove(i, j);
				controller.aiMove(i, j);
				flag = false;
			}else{
				j = y + 1;
				while(flag && j < 30 && model.getBoardValue(i, j) == 1){
					j++;
	        	}
				if(j < 30 && model.getBoardValue(i, j) == 0){
					model.makeMove(i, j);
					controller.aiMove(i, j);
					flag = false;
				}
			}
		
		}
		
		i = x +1;
		j = y - 1;
		
		if(dir.equals(consts.DIAGONALFOWARD)){
			while ( j >= 0 && i < 30 && model.getBoardValue(i, j) == 1) {
				j--;
				i++;
        	}
			if(j >= 0 && i < 30 && model.getBoardValue(i, j) == 0){
				model.makeMove(i, j);
				controller.aiMove(i, j);
				flag = false;
			}else{
				i = x - 1;
				j = y + 1;
				while(flag && i >=0 && j < 30 && model.getBoardValue(i, j) == 1){
					j++;
					i--;
	        	}
				if(i >=0 && j < 30 && model.getBoardValue(i, j) == 0){
					model.makeMove(i, j);
					controller.aiMove(i, j);
					flag = false;
				}
			}
		
		}
		
		i = x - 1;
		j = y - 1;
		
		if(dir.equals(consts.DIAGONALBACK)){
			while ( j >= 0 && i >= 0 && model.getBoardValue(i, j) == 1) {
				j--;
				i--;
        	}
			if( j >= 0 && i >= 0 && model.getBoardValue(i, j) == 0){
				model.makeMove(i, j);
				controller.aiMove(i, j);
				flag = false;
			}else{
				i = x + 1;
				j = y + 1;
				while(flag && i < 30 && j < 30 && model.getBoardValue(i, j) == 1){
					j--;
					i--;
	        	}
				if(i < 30 && j < 30 && model.getBoardValue(i, j) == 0){
					model.makeMove(i, j);
					controller.aiMove(i, j);
					flag = false;
				}
			}
		
		}
		String[] move;
		if(flag){
			 int k = randomGenerator.nextInt(validMoves.getSize());
		     move = validMoves.getElementAt(k).split(" ");
		     int a = Integer.parseInt(move[0]);
		     int b = Integer.parseInt(move[1]);
		     model.makeMove(a,b);
		     controller.aiMove(a,b);
		}
		
	}

	/**
	 * Analyzes the gameboard and determines the direction of the longest
	 * number of pieces the opponent has in a row. Generates a random integer
	 * and one quarter of the time, the AI will make a random move instead of
	 * blocking the opponent. This is our "Medium" difficulty. calls aiMakeMove.
	 * @param int x the x coordinate
	 * @param int y the y coordinate
	 */
	private void analyzeGameboardMedium(int x, int y) {
		String direction = "";
		int max = 0;
		int rand = randomGenerator.nextInt(4);
		if(rand<3){
			if(horizontalSearch(x,y)> max){
				max = horizontalSearch(x,y);
				direction = consts.HORIZONTAL;
			}
			if(verticalSearch(x,y) > max) {
				max = verticalSearch(x,y);
				direction = consts.VERTICAL;
			}
			if(diagonalFowardSearch(x,y)> max){
				max = diagonalFowardSearch(x,y);
				direction = consts.DIAGONALFOWARD;
			}
			if(diagonalBackSearch(x,y)> max){
				max = diagonalBackSearch(x,y);
				direction = consts.DIAGONALBACK;
			}
		}
		
		aiMakeMove(x,y,direction);
	}

	/**
	 * Receives move from the player
	 * @param int x the x coordinate
	 * @param int y the y coordinate
	 */
	public void sendMove(int x, int y) {
		String validatedMove = model.validateOpponent(x, y);
		if(validatedMove.equals(consts.VALID)){
			makeMove(x,y);
		}
	}
	
	/**
	 * Horizontal search for counting how many in a row a player has.
	 * Starts on one side of the passed in x,y coordinate and counts
	 * the number of player pieces in a row, then does the same on the
	 * other side of the x,y coordinate.
	 * @param int x the x coordinate
	 * @param int y the y coordinate
	 * @return int result how many pieces a player has in a row.
	 */
	 private int horizontalSearch(int x, int y) {
	        int result = 1;
	        int i = x - 1;

	        while ( i >= 0 && model.getBoardValue(i, y) == 2) {
	            result++;
	            i--;
	        }
	     
	        i = x + 1;

	        while (i < 30 && model.getBoardValue(i, y) == 2) {
	            result++;
	            i++;
	        }

	        return result;
	    }
	 
	 /**
	 * Diagonal back search for counting how many in a row a player has.
	 * Starts on one side of the passed in x,y coordinate and counts
	 * the number of player pieces in a row, then does the same on the
	 * other side of the x,y coordinate.
	 * @param int x the x coordinate
	 * @param int y the y coordinate
	 * @return int result how many pieces a player has in a row.
	 */
	 private int diagonalBackSearch(int x, int y) {
	        int result = 1;
	        int i = x - 1;
	        int j = y - 1;

	        while (i >= 0 && j >= 0 && model.getBoardValue(i, j) == 2) {
	            result++;
	            i--;
	            j--;
	        }

	    
	        i = x + 1;
	        j = y + 1;
	        while (i < 30 && j < 30 && model.getBoardValue(i,j)==2) {
	            result++;
	            i++;
	            j++;
	        }
	        return result;
	    }

		/**
		 * Diagonal forward search for counting how many in a row a player has.
		 * Starts on one side of the passed in x,y coordinate and counts
		 * the number of player pieces in a row, then does the same on the
		 * other side of the x,y coordinate.
		 * @param int x the x coordinate
		 * @param int y the y coordinate
		 * @return int result how many pieces a player has in a row.
		 */
	    private int diagonalFowardSearch(int x, int y) {
	        int result = 1;
	        int i = x - 1;
	        int j = y + 1;

	        while (i >= 0 && j < 30 &&  model.getBoardValue(i,j) == 2) {
	       
	            result++;
	            i--;
	            j++;
	        }
	        i = x + 1;
	        j = y - 1;
	        while ( i < 30 && j >= 0 && model.getBoardValue(i,j) == 2) {
	            result++;
	            i++;
	            j--;
	        
	        }

	        return result;
	    }

		/**
		 * Vertical search for counting how many in a row a player has.
		 * Starts on one side of the passed in x,y coordinate and counts
		 * the number of player pieces in a row, then does the same on the
		 * other side of the x,y coordinate.
		 * @param int x the x coordinate
		 * @param int y the y coordinate
		 * @return int result how many pieces a player has in a row.
		 */
	    private int verticalSearch(int x, int y) {
	     
	        int result = 1;
	        int i = y - 1;

	        while (i >= 0 && model.getBoardValue(x,i) == 2) {
	            result++;
	            i--;
	        }

	     
	        i = y + 1;
	        while (i < 30 && model.getBoardValue(x,i) == 2) {
	            result++;
	            i++;
	        }  

	        return result;
	    }
	    
	    
	    /**
	     * Sets validMoves as a list of all valid moves on gameboard.
	     */
	    private void updateValidMoveList(){
	    	
	    	validMoves.clear();
	    	for (int i = 0; i < 30; i++) {
	             for (int k = 0; k < 30; k++) {
	                 if(model.isValid(i, k)){
	                	 validMoves.addElement(i + " " + k);
	                 }
	             }
	         }
	    }
}
