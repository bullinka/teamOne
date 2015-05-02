
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
   
    /*public void setupIOStreams() {
        try {
            inStream = model.challengeeSocket.getInputStream();
            dataIn = new DataInputStream(inStream);
            outStream = model.challengeeSocket.getOutputStream();
            dataOut = new DataOutputStream(outStream);
            newGame();
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    /*public void setupIOStreamsOpponent(){
        try {
            inStreamOp = model.opponent.getInputStream();
            dataInOp = new DataInputStream(inStreamOp);
            outStreamOp = model.opponent.getOutputStream();
            dataOutOp = new DataOutputStream(outStreamOp);
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }*/
    
    
    public void setInputStream(InputStream s){
        System.out.println(" Set Input Stream");
        dataIn = new DataInputStream(s);
    }
    public void setOutputStream(OutputStream o){
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

    public boolean validateSelf(int x, int y) {

        if (gameModel.validateSelf(x, y)) {
            sendMove(x , y);
            return true;
        } else {
            return false;
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
    
    private void processMessage(String[] s){
        String m = s[0];
        int x = Integer.parseInt(s[1]);
        int y = Integer.parseInt(s[2]);
        switch (m) {
            case MOVE:
                gameModel.validateOpponent(x, y);
                break;
            default:
                break;
        }
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
}
