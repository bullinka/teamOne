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
    	System.out.println(diff);
        if(diff.equals("Easy")){
        	difficulty = 0;
        }else if(diff.equals("Moderate")){
        	difficulty = 1;
        }else if(diff.equals("Hard")){
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
     * Makes a move in the game board as an opponent player.
     * @param x
     * @param y 
     */
    public void makeMove(int x, int y)
    {
    	
    	//System.out.println(analyzeGameboardHard(x, y));
    	if(difficulty == 0){
    		System.out.println("Easy difficulty selected");
	        int i = randomGenerator.nextInt(30);
	        int j = randomGenerator.nextInt(30);
	        boolean flag = model.isValid(i, j);
	        while(!flag){
	        	i = randomGenerator.nextInt(30);
	            j = randomGenerator.nextInt(30);
	            flag = model.isValid(i, j);
	        }
	        
	        model.makeMove(i,j);
	        controller.aiMove(i, j);
	     
    	}
    	if (difficulty == 1){
    		analyzeGameboardMedium(x, y);
    	}
    	if (difficulty == 2){
	    	 analyzeGameboardHard(x , y);
	    	 
    	}
    }

	private void analyzeGameboardHard(int x, int y) {
		String direction = "";
		int max = 0;
		if(horizontalSearch(x,y)> max){
			max = horizontalSearch(x,y);
			direction = "h";
		}
		if(verticalSearch(x,y) > max) {
			max = verticalSearch(x,y);
			direction = "v";
		}
		if(diagonalFowardSearch(x,y)> max){
			max = diagonalFowardSearch(x,y);
			direction = "df";
		}
		if(diagonalBackSearch(x,y)> max){
			max = diagonalBackSearch(x,y);
			direction = "db";
		}
		System.out.println(direction);
		
		
		aiMakeMove(x,y,direction);
		
	}
	
	
	public void aiMakeMove(int x, int y, String dir)
	{
		boolean flag = true;
		int i = x - 1, j = y;
		if(dir.equals("h")){
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
		if(dir.equals("v")){
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
		
		if(dir.equals("df")){
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
		
		if(dir.equals("db")){
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
		
		if(flag){
			System.out.println("Easy difficulty selected");
	        i = randomGenerator.nextInt(30);
	        j = randomGenerator.nextInt(30);
	        boolean valid = model.isValid(i, j);
	        while(!valid){
	        	i = randomGenerator.nextInt(30);
	            j = randomGenerator.nextInt(30);
	            valid = model.isValid(i, j);
	        }
	        
	        model.makeMove(i,j);
	        controller.aiMove(i, j);
		}
		
		/*if(dir.equals("h"))
		{
			int nextCell = model.getBoardValue(x+1, y);
			while(flag)
			{
				if(nextCell == 2) {
					System.out.println("nextCell = 2");
					nextCell = model.getBoardValue(x+1, y);
				}
				else if(nextCell == 0) {
					System.out.println("nextCell = 0");
					model.makeMove(x, y);
			        controller.aiMove(x+temp, y);
			        flag = false;
				}
				else {
					System.out.println("nextCell = 1");
					nextCell = model.getBoardValue(x-1, y);
					if(nextCell == 2) {
						nextCell = model.getBoardValue(x-1, y);
					}
					else if(nextCell == 0) {
						model.makeMove(x, y);
				        controller.aiMove(x-temp, y);
				        flag = false;
					}
					else {
						break;
					}
				}
			}
		}
		
		if(dir.equals("v"))
		{
			int nextCell = model.getBoardValue(x, y-1);
			System.out.println("Got next cell.");
			while(flag)
			{
				if(nextCell == 2) {
					System.out.println("nextCell = 2");
					nextCell = model.getBoardValue(x, y-1);
				}
				else if(nextCell == 0) {
					System.out.println("nextCell = 0");
					model.makeMove(x, y);
			        controller.aiMove(x, y-temp);
			        flag = false;
				}
				else {
					System.out.println("nextCell = 1");
					nextCell = model.getBoardValue(x, y+1);
					if(nextCell == 2) {
						nextCell = model.getBoardValue(x, y+1);
					}
					else if(nextCell == 0) {
						model.makeMove(x, y);
				        controller.aiMove(x, y+temp);
				        flag = false;
					}
					else {
						break;
					}
				}
			}
		}*/
	}

	private void analyzeGameboardMedium(int x, int y) {
		String direction = "";
		int max = 0;
		int rand = randomGenerator.nextInt(4);
		if(rand<3){
		if(horizontalSearch(x,y)> max){
			max = horizontalSearch(x,y);
			direction = "h";
		}
		if(verticalSearch(x,y) > max) {
			max = verticalSearch(x,y);
			direction = "v";
		}
		if(diagonalFowardSearch(x,y)> max){
			max = diagonalFowardSearch(x,y);
			direction = "df";
		}
		if(diagonalBackSearch(x,y)> max){
			max = diagonalBackSearch(x,y);
			direction = "db";
		}
		}
		
		aiMakeMove(x,y,direction);
	}

	public void sendMove(int x, int y) {
		 String jonsajerk = model.validateOpponent(x, y);
	              if(jonsajerk.equals(consts.VALID)){
	                    makeMove(x,y);
	                } else if (jonsajerk.equals(consts.WIN)) {
	                  
	                }else if(jonsajerk.equals(consts.INVALID)){
	                   //should this do something?
	                }
	                else if(jonsajerk.equals(consts.NOTTURN)){
	                    //should this do something?
	                }
		
	}
	
	 private int horizontalSearch(int x, int y) {
	        int result = 1;
	        boolean flag = false;
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

	        if (result >= 5) {
	            flag = true;
	        }
	        return result;
	    }
	 
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
}
