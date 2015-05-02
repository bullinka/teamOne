/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PLUCSCE
 */
public class GameModel {
	
	private GameBoard board;
	private GameAI ai;
    private GameController controller;
   
   
    public GameModel(){
    	board = new GameBoard();
    }
    
    public boolean validateSelf(int x, int y) {
    	 if (board.validate(x, y)){
      	   board.moveMadeSelf(x, y);
      	   return true;
         }else{
      	   return false; 
         }
		
	}
    
    public boolean validateOpponent(int x, int y) {
   	 if (board.validate(x, y)){
     	   board.moveMadeOpponent(x, y);
     	   return true;
        }else{
     	   return false; 
        }
		
	}

	public void setController(GameController gc)
    {
        this.controller = gc;
    }
    
    public void setGameBoard(GameBoard gb)
    {
        this.board = gb;
    }
    
    public void setAI(GameAI ai)
    {
        this.ai = ai;
    }
}
