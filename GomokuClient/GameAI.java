import java.util.Random;

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
public class GameAI {
    private GameBoard board;
    private int difficulty;
    private GameModel model;
    private Constants consts = new Constants();
    private Random randomGenerator = new Random();
    private GameController controller;
    
    /**
     * Game AI Constructor
     * @param gm 
     * @param v 
     * @param difficulty 
     */
    public GameAI(String diff, GameController gc)
    {
        if(diff.equals("easy")){
        	difficulty = 0;
        }else if(diff.equals("medium")){
        	difficulty = 1;
        }else if(diff.equals("hard")){
        	difficulty = 2;
        }
        model = new GameModel();
        controller = gc;
        model.setTurnOrder(false);
    }
    
    public void setGameBoard(GameBoard board)
    {
        this.board = board;
    }
    
    /**
     * Method to start the AI
     */
    public void playGame()
    {
        
    }
    
    /**
     * Makes a move in the game board as an opponent player.
     * @param x
     * @param y 
     */
    public void makeMove()
    {
    	if(difficulty == 0){
        int x = randomGenerator.nextInt(30);
        int y = randomGenerator.nextInt(30);
        boolean flag = model.isValid(x, y);
        while(!flag){
        	x = randomGenerator.nextInt(30);
            y = randomGenerator.nextInt(30);
            flag = model.isValid(x, y);
        }
        model.makeMove(x,y);
        controller.aiMove(x, y);
    	}else if (difficulty == 1){
    		analyzeGameboardMedium();
    	}else if (difficulty == 2){
    		analyzeGameboardHard();
    	}
    }

	private void analyzeGameboardHard() {
		
		
	}

	private void analyzeGameboardMedium() {
		
		
	}

	public void sendMove(int x, int y) {
		 String jonsajerk = model.validateOpponent(x, y);
	              if(jonsajerk.equals(consts.VALID)){
	                    makeMove();
	                } else if (jonsajerk.equals(consts.WIN)) {
	                  
	                }else if(jonsajerk.equals(consts.INVALID)){
	                   //should this do something?
	                }
	                else if(jonsajerk.equals(consts.NOTTURN)){
	                    //should this do something?
	                }
		
	}
}
