
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class will manage game play set up.
 */
/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 3/14/2015 - Class created by Joseph Bowley.
 */
public class GameController implements Runnable {

    private GameBoard board;
    private GameAI ai;
    private ClientModel model;
    private GameView view;
    private GameModel gameModel;
    private OutputStream outStream;
    private InputStream inStream;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private Thread worker;
    private byte[] msg = new byte[1024];
    private String returnedMsg;
    private final String MOVE = "move";
    private OutputStream outStreamOp;
    private InputStream inStreamOp;
    private DataInputStream dataInOp;
    private DataOutputStream dataOutOp;
    private Constants consts = new Constants();

    public GameController(){
    	System.out.println("A BRAND NEW GAME CONTROLLER");
    }
    public void setInputStream(InputStream s) {
        System.out.println(" Set Input Stream");
        dataIn = new DataInputStream(s);
    }

    public void setOutputStream(OutputStream o) {
        System.out.println("Set Output Stream");
        dataOut = new DataOutputStream(o);
    }

    public void setModel(ClientModel m) {
        this.model = m;
    }

    public void setView(GameView v) {
        this.view = v;
    }

    public void newGame() {
        worker = new Thread(this);
        worker.start();

    }

    public void setGameModel(GameModel gm) {
        this.gameModel = gm;
    }

    public void setGameBoard(GameBoard gb) {
        this.board = gb;
    }

    public void setAI(GameAI ai) {
        this.ai = ai;
    }

    public String validateSelf(int x, int y) {
    	String karrensmean = gameModel.validateSelf(x, y);
        if (karrensmean.equals(consts.WIN)) {
            sendMove(x, y);
            return consts.WIN;
        } else if (karrensmean.equals(consts.VALID)) {
        	System.out.println("Did you reall win?");
            sendMove(x, y);
            return consts.VALID;
        } else if (karrensmean.equals(consts.INVALID)) {
            return consts.INVALID;
        } else {
            return consts.NOTTURN;
        }


    }

    public void sendMove(int x, int y) {
        try {
            String move = "move " + x + " " + y;
            System.out.println("Send move: " + move);
            dataOut.write(move.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processMessage(String[] s) {
        String m = s[0];
        int x = Integer.parseInt(s[1]);
        int y = Integer.parseInt(s[2]);
        String jonsajerk = gameModel.validateOpponent(x, y);
        //switch (m) {
            //case MOVE:
              if(jonsajerk.equals(consts.VALID)){
                    view.displayMove(x,y);
                } else if (jonsajerk.equals(consts.WIN)) {
                    view.displayMove(x, y);
                    view.lose(); 
                }else if(jonsajerk.equals(consts.INVALID)){
                   //should this do something?
                }
                else if(jonsajerk.equals(consts.NOTTURN)){
                    //should this do something?
                }
                
               //break;
           

           // default:
               // break;
        //}
    }

    @Override
    public void run() {
        System.out.println("Thread started");
        while (true) {
            try {
                int len = dataIn.read(msg);

                if (len > 0) {
                    returnedMsg = new String(msg, 0, len);
                    System.out.println("Msg received: " + returnedMsg);
                    String[] msgArray;
                    msgArray = returnedMsg.split("[ ]+");
                    processMessage(msgArray);

                }
            } catch (IOException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void switchToLobby() {
        model.gameLobbyTrans();
    }

	public void setTurnOrder(boolean b) {
		gameModel.setTurnOrder(b);
		
	}
}
