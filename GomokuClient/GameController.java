
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class will manage game play set up.
 */
/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 
 * 3/14/2015 - Class created by Joseph Bowley.
 * 5/3/2015 - wrote sendMove() and processMessage(), debugged gameController 
 * being set to null, bug fixes for displaying moves on the game view.
 * 5/5/2015 - wrote sendWin(), sendLose(), setupMainInputStreams(), iMove(),
 * closeSockets()
 * 5/9/2015 - wrote loggedIn(), displayTurnnotice(). Added update notifications
 * 5/12/2015 - Bug fixes for properly switching to lobbyview after a game
 * and sending a win message after a game. Rewrote resign method. Added
 * switchToLobby().
 * 5/17/2015 - Added quit() to handle a user quitting the application
 *             in the middle of a game.
 * 5/18/2015 - added method comments
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
    private DataInputStream dataInOp, mainServerIn;
    private DataOutputStream dataOutOp, mainServerOut;
    private Constants consts = new Constants();
    private Socket opponent;
    private ServerSocket ss;
    private boolean server;
    public boolean aiGame; // if true it is an ai game. If false it is not.

    /**
     * Checks to see if the player is logged in or not.
     * @return true if the user is logged in, false otherwise.
     */
    public boolean loggedIn() {
        return model.loggedIn;
    }

    /**
     * Sets up an AI game.
     * @param isAI the boolean determining if the game is an AI game.
     * @param difficulty the difficulty of the AI.
     */
    public void setaiGame(boolean isAI, String difficulty) {
        aiGame = isAI;
        ai = new GameAI(difficulty, this);
        gameModel.setTurnOrder(true);
    }

    /**
     * Sets up the socket for the peer to peer connection. Will be used by
     * the player who challenges another player.
     * @param sock the socket.
     */
    public void setSocket(Socket sock) {
        this.opponent = sock;
        server = false;
    }

    /**
     * Sets up the serverSocket for the peer to peer connection. Will be used
     * by the player receiving a challenge from another player.
     * @param sock the ServerSocket
     */
    public void setServerSocket(ServerSocket sock) {
        this.ss = sock;
        server = true;
    }

    /**
     * Sets up the dataInputStream.
     *
     * @param s the inputStream
     */
    public void setInputStream(InputStream s) {
        dataIn = new DataInputStream(s);
    }

    /**
     * Sets up the dataOutputStream.
     *
     * @param s the inputStream
     */
    public void setOutputStream(OutputStream o) {
        dataOut = new DataOutputStream(o);
    }

    /**
     * Sets up the DataInputStream and DataOutputStream for the connection with
     * the server. These are different streams than the ones used for the
     * peer to peer connection.
     * @param s the InputStream
     * @param o the OutputStream
     */
    public void setMainInputStreams(InputStream s, OutputStream o) {
        this.mainServerIn = new DataInputStream(s);
        this.mainServerOut = new DataOutputStream(o);
    }

    /**
     * associates the ClientModel with the GameController.
     *
     * @param m the ClientModel
     */
    public void setModel(ClientModel m) {
        this.model = m;
    }

    /**
     * associates the GameView with the GameController.
     *
     * @param v the GameView
     */
    public void setView(GameView v) {
        this.view = v;
    }

    /**
     * creates a new thread when a game is started.
     */
    public void newGame() {
        worker = new Thread(this);
        worker.start();

    }

    /**
     * associates the GameModel with the GameController.
     *
     * @param gm the GameModel
     */
    public void setGameModel(GameModel gm) {
        this.gameModel = gm;
    }

    /**
     * associates the GameBoard with the GameController.
     *
     * @param gb the GameBoard
     */
    public void setGameBoard(GameBoard gb) {
        this.board = gb;
    }

    /**
     * associates the GameAI with the GameController.
     *
     * @param ai the GameAI
     */
    public void setAI(GameAI ai) {
        this.ai = ai;
    }

    /**
     * This method checks the validity of the move that you made during a game.
     * It also checks if your move was a winning move.
     *
     * @param x the x coordinate of your move
     * @param y the y coordinate of your move
     * @return The appropriate constant/methods from the Constants class
     * depending on the type of game (AI or two player) and if your turn is
     * valid or not.
     */
    public String validateSelf(int x, int y) {
        String validatedMove = gameModel.validateSelf(x, y);
        if (validatedMove.equals(consts.WIN)) {
            if (aiGame) {
                return consts.WIN;
            } else {
                sendMove(x, y);
                sendWin();
                closeSockets();
                return consts.WIN;
            }
        } else if (validatedMove.equals(consts.VALID)) {
            if (aiGame) {
                ai.sendMove(x, y);
                return consts.VALID;
            } else {
                sendMove(x, y);
                return consts.VALID;
            }
        } else if (validatedMove.equals(consts.INVALID)) {
            return consts.INVALID;
        } else {
            return consts.NOTTURN;
        }

    }

    /**
     * Sends a win message to the server with the player who won. This is used
     * to update the leaderboard view.
     */
    public void sendWin() {
        try {
            String win = consts.GAME + " " + model.username + " " + consts.WIN;
            mainServerOut.write(win.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            //Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
                    //null, ex);
            //add error handling
        }
    }

    /**
     * Sends a lose message to the server with the player who lost. This is used
     * to update the leaderboard view.
     */
    public void sendLose() {
        try {
            String lose = consts.GAME + " " + model.username + " " + consts.LOSE;
            mainServerOut.write(lose.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            //Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
                    //null, ex);
            //add error handling
        }
    }

    /**
     * Sends a message to the other player that their opponent has resigned.
     * Triggers a popup box on the opponents view and transitions both players
     * into the lobby view.
     *
     * @param res the resign message.
     */
    public void sendResign(String res) {
        try {
            dataOut.write(res.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    /**
     * Sends a message to the other player that their opponent has quit.
     * Triggers a popup box on the opponents view and transitions the remaining
     * player into the lobby view.
     *
     * @param q the quit message.
     */
    public void sendQuit(String q) {
        try {
            dataOut.write(q.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
			//Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
            //null, ex);
            //add error handling.
        }

    }

    /**
     * Sends your move to the other player via dataOut.
     *
     * @param x the x coordinate of the place you moved.
     * @param y the y coordinate of the
     */
    public void sendMove(int x, int y) {
        try {
            String move = consts.MOVE + " " + x + " " + y;
            dataOut.write(move.getBytes());
            view.updateTurnNotification(false);
            dataOut.flush();
        } catch (IOException ex) {
			//Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
            //null, ex);
            //add error handling.
        }
    }

    /**
     * This method takes a message from the server as a string array and
     * processes it according to what the message consists of.
     * @param s the message from the server.
     */
    private void processMessage(String[] s) {
        String m = s[0];
        if (s.length > 1) {
            int x = Integer.parseInt(s[1]);
            int y = Integer.parseInt(s[2]);

            String validate = gameModel.validateOpponent(x, y);

            if (validate.equals(consts.VALID)) {
                view.displayMove(x, y);
                view.updateTurnNotification(true);
            } else if (validate.equals(consts.WIN)) {
                view.displayMove(x, y);
                view.lose();
                sendLose();
                closeSockets();
            }
        } else if (m.equals(consts.RESIGN)) {
            try {
                view.displayErrorMessage("Opponent has resigned.");
                view.win();
                sendWin();

                if (server) {
                    try {
                        ss.close();
                    } catch (IOException ex) {
						//Logger.getLogger(GameController.class.getName()).log(
                        //Level.SEVERE, null, ex);
                        //add error handling.
                    }
                } else {
                    opponent.close();
                }

                model.gameLobbyTrans();
            } catch (IOException ex) {
				//Logger.getLogger(GameController.class.getName()).log(
                //Level.SEVERE, null, ex);
                //add error handling.
            }
        } else if (m.equals(consts.QUIT)) {
            try {
                view.displayErrorMessage("Opponent has quit.");
                view.win();
                sendWin();

                if (server) {
                    try {
                        ss.close();
                    } catch (IOException ex) {
						//Logger.getLogger(GameController.class.getName()).log(
                        //Level.SEVERE, null, ex);
                        //add error handling
                    }
                } else {
                    opponent.close();
                }

                model.gameLobbyTrans();
            } catch (IOException ex) {
				//Logger.getLogger(GameController.class.getName()).log(
                //Level.SEVERE, null, ex);
                //add error handling
            }
        }

    }

    /**
     * The run method. this reads in messages from the server and processes them
     * with processMessage().
     */
    @Override
    public void run() {
        System.out.println("Thread started");
        while (true) {
            try {
                int len = dataIn.read(msg);

                if (len > 0) {
                    returnedMsg = new String(msg, 0, len);
                    String[] msgArray;
                    msgArray = returnedMsg.split("[ ]+");
                    processMessage(msgArray);

                }
            } catch (IOException ex) {
				// Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
                // null, ex);
                return;
            }
        }
    }

    /**
     * Switches the panel to the lobby view through the frame class.
     */
    public void switchToLobby() {
        model.gameLobbyTrans();
        aiGame = false;
    }

    /**
     * Switches the panel to the login view through the frame class.
     */
    public void switchToLogin() {
        model.gameLoginTrans();
        gameModel.resetBoard();
        aiGame = false;
    }

    /**
     * Calls setTurnOrder in gameModel and gameboard, which establishes who's
     * turn it currently is during a game.
     *
     * @param b the boolean value. It's a player's turn if the boolean is true.
     */
    public void setTurnOrder(boolean b) {
        gameModel.setTurnOrder(b);
        displayTurnNotice(b);

    }

    /**
     * Updates the JLabel in the game view to say if it is your turn or not. It
     * is your turn if b is true, and it is not your turn if b is false.
     *
     * @param b the boolean value that determines if it is your turn.
     */
    public void displayTurnNotice(boolean b) {
        if (b) {
            view.updateTurnNotification(true);
        } else {
            view.updateTurnNotification(false);
        }
    }

    /**
     * Manages what happens when a user resigns. If the user is in an AI game,
     * the view switches to the lobby view. If it is a two player game, the
     * program switches to the lobby view for both players and closes the peer
     * to peer connection.
     */
    public void resign() {

        if (model.loggedIn && aiGame) {
            switchToLobby();
        } else if (model.loggedIn && !aiGame) {
            sendResign(consts.RESIGN);
            switchToLobby();
            closeSockets();
        } else {
            switchToLogin();
        }
    }

    /**
     * Manages what happens when a user quits. The program switches to the lobby
     * view for the remaining player, and exits the application for the person
     * that quit the game. this also closes the peer to peer connection.
     */
    public void quit() {
        if (model.loggedIn && aiGame) {
            switchToLobby();
        } else if (model.loggedIn && !aiGame) {
            sendQuit(consts.QUIT);
            closeSockets();
            System.exit(0);
        } else {
            switchToLogin();
        }
    }

    /**
     * This method closes the socket and serversocket for the peer to peer
     * connection. It is used when a game between two players ends.
     */
    public void closeSockets() {
        if (server) {
            try {
                ss.close();

            } catch (IOException ex) {
				// Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
                // null, ex);
            }
        } else {
            try {
                opponent.close();

            } catch (IOException ex) {
				// Logger.getLogger(GameController.class.getName()).log(Level.SEVERE,
                // null, ex);
            }
        }
    }

    /**
     * Updates the view with the move made by the AI player.
     *
     * @param x the x coordinate of the move by the AI
     * @param y the y coordinate of the move by the AI
     */
    public void aiMove(int x, int y) {
        String validatedMove = gameModel.validateOpponent(x, y);
        if (validatedMove.equals(consts.VALID)) {
            view.displayMove(x, y);
        } else if (validatedMove.equals(consts.WIN)) {
            view.displayMove(x, y);
            view.lose();
        }
    }

}
