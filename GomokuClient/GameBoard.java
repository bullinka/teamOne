

/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 3/14/2015 - Class created by Joseph Bowley.
 */
public class GameBoard {

    private final int[][] board;
    private boolean turnOrder;

    /**
     * GameBoard constructor. Passes in dimensions of the board.
     *
     * @param x The desired Horizontal Size
     * @param y The desired Vertical Size
     */
    public GameBoard(int x, int y) {
        board = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                board[i][k] = 0;
            }
        }
        turnOrder = true;
    }

    /**
     * The Default constructor initializes the board array with dimensions
     * 30X30 with 0 in every value initializes the turn order.
     */
    public GameBoard() {
        board = new int[30][30];
        for (int i = 0; i < 30; i++) {
            for (int k = 0; k < 30; k++) {
                board[i][k] = 0;
            }
        }
        turnOrder = true;
    }

    /**
     * Sets turnOrder
     * @param to The desired turn.
     */
    public void setTurnOrder(boolean to) {
        turnOrder = to;
    }

    /**
     * Gets turn order
     * @return turnorder
     */
    public boolean getTurnOrder() {
        return turnOrder;
    }

    /**
     * Makes a move on the board for one of the user player active in a game.
     *
     * @param x
     * @param y
     */
    public void moveMadeSelf(int x, int y) {
        board[x][y] = 1;
        turnOrder = false;
    }

    /**
     * Makes a move on the board for one of the opponent player active in a
     * game.
     *
     * @param x
     * @param y
     */
    public void moveMadeOpponent(int x, int y) {
        board[x][y] = 2;
        turnOrder = true;

    }

    /**
     * Returns the representation of the gameBoard
     *
     * @return Returns the Board.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Returns if there is a five in a row on the board for
     * the user.
     *
     * @return true if there is five 1s in a row. False if not. 
     */
    public boolean isFiveInARowSelf(int x, int y) {

        if (horizontalSearch(x, y, 1)) {
            return true;
        } else if (verticalSearch(x, y, 1)) {
            return true;
        } else if (diagonalFowardlSearch(x, y, 1)) {
            return true;
        } else if (diagonalBackSearch(x, y, 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if there is a five in a row on the board for
     * the opponent.
     *
     * @return true if there is five 2s in a row. False if not. 
     */
    public boolean isFiveInARowOpponent(int x, int y) {

        if (horizontalSearch(x, y, 2)) {
            return true;
        } else if (verticalSearch(x, y, 2)) {
            return true;
        } else if (diagonalFowardlSearch(x, y, 2)) {
            return true;
        } else if (diagonalBackSearch(x, y, 2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks diagonal from current coordinate for five zs in a row. 
     * @param x Current horizontal coordinate
     * @param y Current Vertical coordinate
     * @param z Value checking for
     * @return true if five zs in a row. false if not
     */
    private boolean diagonalBackSearch(int x, int y, int z) {
        int count = 0;
        int result = 1;
        boolean flag = false;
        int i = x - 1;
        int j = y - 1;

        while (count < 5 && i >= 0 && j >= 0 && board[i][j] == z) {
            result++;
            i--;
            j--;
        }

        count = 0;
        i = x + 1;
        j = y + 1;
        while (count < 5 && i < 30 && j < 30 && board[i][j] == z) {
            result++;
            i++;
            j++;
        }

        if (result >= 5) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks diagonal from current coordinate for five zs in a row. 
     * @param x Current horizontal coordinate
     * @param y Current Vertical coordinate
     * @param z Value checking for
     * @return true if five zs in a row. false if not
     */
    private boolean diagonalFowardlSearch(int x, int y, int z) {
        int count = 0;
        int result = 1;
        boolean flag = false;
        int i = x - 1;
        int j = y + 1;

        while (count < 5 && i >= 0 && j < 30 && board[i][j] == z) {
            result++;
            i--;
            j++;
        }
        count = 0;
        i = x + 1;
        j = y - 1;
        while (count < 5 && i < 30 && j >= 0 && board[i][j] == z) {
            result++;
            i++;
            j--;
        }

        if (result >= 5) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks vertical from current coordinate for five zs in a row. 
     * @param x Current horizontal coordinate
     * @param y Current Vertical coordinate
     * @param z Value checking for
     * @return true if five zs in a row. false if not
     */
    private boolean verticalSearch(int x, int y, int z) {
        int count = 0;
        int result = 1;
        boolean flag = false;
        int i = y - 1;

        while (count < 5 && i >= 0 && board[x][i] == z) {
            result++;
            i--;
        }

        count = 0;
        i = y + 1;
        while (count < 5 && i < 30 && board[x][i] == z) {
            result++;
            i++;
        }

        if (result >= 5) {
            flag = true;
        }

        return flag;
    }

    /**
     * Checks horizontal from current coordinate for five zs in a row. 
     * @param x Current horizontal coordinate
     * @param y Current Vertical coordinate
     * @param z Value checking for
     * @return true if five zs in a row. false if not
     */
    private boolean horizontalSearch(int x, int y, int z) {
        int count = 0;
        int result = 1;
        boolean flag = false;
        int i = x - 1;

        while (count < 5 && i >= 0 && board[i][y] == z) {
            result++;
            i--;
        }

        count = 0;
        i = x + 1;

        while (count < 5 && i < 30 && board[i][y] == z) {
            result++;
            i++;
        }

        if (result >= 5) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks to see if a selected move is a valid move.
     *
     * @param x inputed horizontal component
     * @param y inputed vertical component
     */
    public boolean validate(int x, int y) {
        if (board[x][y] == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Resets board by setting all values to 0.
     */
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }
    
    /**
     * Checks if value at coordinates is 0.
     * 
     * @param x inputed horizontal component
     * @param y inputed vertical component
     * @return true if value at coordinates is 0, false if not.
     */
    public boolean isValid(int x, int y) {
		if(board[x][y] == 0){
			return true;
		}else{
		return false;
		}
    }

	/**
	 * Gets the value of the board at specified coordinates. 
	 * 
	 * @param x inputed horizontal component
     * @param y inputed vertical component
	 * @return The value of the board at the coordinates. 
	 */
	public int getBoardValue(int x, int y) {
		return board[x][y];
	}
}
