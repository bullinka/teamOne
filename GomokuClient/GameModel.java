

/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 5/1/2015 - Class created by Jon Julius. Began with implementing
 * setter for gameboard and validate methods for opponent and self. 
 * 5/2/2015 - Added more to validate methods, not checks for win conditions
 * and turn order. 
 * 5/5/2015 - Added methods isValid, getBoardValue, makeMove to aid in AI implementation. 
 * 5/9/2015 - Added method restBoard to reset gameboard after game. 
 * 5/16/2015 - Added comments and cleaned up code.  
 * 
 */
public class GameModel {

    private GameBoard board;
    private Constants cons = new Constants();

    /**
     * Default constructor of GameModel. Initializes the game's gameboard.
     */
    public GameModel() {
        board = new GameBoard();
    }

    /**
     * Checks an inputed set of coordinates for validity. It first checks if it's the users turn. It then checks
     * if it is a valid move. After that it calls the moveMadeSelf method in gameboard to update the gameboard. 
     * It then checks to see if the move is a winning move. Checks for users moves. 
     * @param x The horizontal component of the inputed move. 
     * @param y The vertical component of the inputed move.
     * @return "notTurn" is returned if it isn't the users turn. "invalid" is returned if it is an invalid move.
     * "valid" is returned if the move was valid and the gameboard was updated. "win" if the move was a winning move. 
     */
    public String validateSelf(int x, int y) {
        if (board.getTurnOrder()) {
            if (board.validate(x, y)) {
                board.moveMadeSelf(x, y);
                if (board.isFiveInARowSelf(x, y)) {
                    board.resetBoard();
                    return cons.WIN;
                } else {
                    return cons.VALID;
                }
            } else {
                return cons.INVALID;
            }
        } else {
            return cons.NOTTURN;
        }


    }

    /**
     * Checks an inputed set of coordinates for validity. It first checks if it's the users turn. It then checks
     * if it is a valid move. After that it calls the moveMadeSelf method in gameboard to update the gameboard. 
     * It then checks to see if the move is a winning move. Check for opponents moves.
     * @param x The horizontal component of the inputed move. 
     * @param y The vertical component of the inputed move.
     * @return "notTurn" is returned if it isn't the users turn. "invalid" is returned if it is an invalid move.
     * "valid" is returned if the move was valid and the gameboard was updated. "win" if the move was a winning move. 
     */
    public String validateOpponent(int x, int y) {
        if (board.validate(x, y)) {
            board.moveMadeOpponent(x, y);
            if (board.isFiveInARowOpponent(x, y)) {
                board.resetBoard();
                return cons.WIN;
            } else {
                return cons.VALID;
            }
        } else {
            return cons.INVALID;
        }

    }

  

    /**
     * This method sets the gameboard
     * @param gb is the inputed new gameboard.
     */
    public void setGameBoard(GameBoard gb) {
        this.board = gb;
    }

  

    /**
     * This method modifies the turn order and sets it to the desired turn.
     * @param b the current turn. 
     */
    public void setTurnOrder(boolean b) {
        board.setTurnOrder(b);

    }
    
    /**
     * This method checks the gameboard at a specific coordinate for an empty space.
      * @param x The horizontal component of the inputed coordinate. 
     * @param y The vertical component of the inputed coordinate.
     * @return true if gameboard at coordinate is 0, false if not. 
     */
    public boolean isValid(int x, int y){
    	return board.validate( x,  y);
    	
    }

	/**
	 * Updates the gameboard the inputed coordinates with a 1 to signify user making
	 * a move.
	 * @param x The horizontal component of the inputed coordinate. 
     * @param y The vertical component of the inputed coordinate.
	 */
	public void makeMove(int x, int y) {
		board.moveMadeSelf(x, y);
		
	}

	/**
	 * Returns the value of the gameboard at the given coordinates. 
	 * @param x The horizontal component of the inputed coordinate. 
     * @param y The vertical component of the inputed coordinate.
	 * @return 0 if no move is empty, 1 if it is the user's space,
	 * 2 if it is the opponent.
	 */
	public int getBoardValue(int x, int y) {
		return board.getBoardValue(x, y);
	}

	/**
	 * Sets the board to all zeros. 
	 */
	public void resetBoard() {
		board.resetBoard();
		
	}
	
	
}
