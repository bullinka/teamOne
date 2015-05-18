
/**
 * This class manages all pertinent data for the client application.
 * Communicates GUI transition from each controller to the frame class.
 */
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 3/14/2015 - Class created by Karen Bullinger. 
 * 4/5/2015 - Added createFrame() method. -- Karen Bullinger 
 * 4/11/2015 - Updated newServerConnection to return boolean -- Karen Bullinger
 * 5/1/2015 - Fixed board reset by calling newGame on gameController. -- Karen Bullinger
 * 5/2/2015 - Fixed turn order setting for game play. -- Jon Julius
 * 5/2/2015 - Added setSocket() and setServerSocket() method calls on gameController. -- Karen Bullinger
 * 5/5/2015 - Added instance of leaderboard controller, altered createFrame to accept leaderboardView. -- Karen Bullinger
 * 5/5/2015 - Added setLeaderboardController, corrected turn order, facilitated aiGameTrans. -- Jon Julius
 * 5/9/2015 - Added setUsername method, edited loginLobbyTrans to set title of frame. -- Karen Bullinger
 * 5/9/2015 -- Added leaderboard to lobby transition method. -- Carl Derline
 * 5/9/2015 - Fixed game play board reset in gameLobbyTrans(). -- Jon Julius
 * 5/9/2015 - Added methods to support resign functionality in game play. -- Karen Bullinger
 * 5/9/2015 - Add method to handle lostConnection over peer to peer connection. -- Karen Bullinger
 * 5/12/2015 - Bug fixes. -- Jon Julius
 * 5/16/2015 - Commenting code. -- Karen Bullinger
 * 5/18/2015 - Updating variable names to be more readable, commented code, removed unused imports. -- Karen Bullinger
 * 
 */

public class ClientModel implements Runnable {

    private LobbyController lobbyController;
    private LoginController loginController;
    private GameController gameController;
    private LeaderboardController leaderController;
    public Socket socket;
    public Socket challengeeSocket;
    public Socket opponent;
    public ServerSocket ss;
    public Frame frame;
    public String username;
    public final int gameHeight = 30;
    public final int gameWidth = 30;
    private Thread worker;
    private int port = 27200;
    public boolean turn;
    public GameView gameView;
    private Constants consts = new Constants();
    public boolean loggedIn = false;

    /**
     * Stores session username.
     */
    public void setUsername(String u) {
        this.username = u;
    }

    /**
     * Tracks whether user is logged in or not.
     */
    public void setLoggedIn(boolean b) {
        loggedIn = b;
    }

    /**
     * Creates a new frame to hold each needed view.
     *
     * @param login instance of login view
     * @param lobby instance of lobby view
     * @param game instance of game view
     */
    public void createFrame(LoginView login, LobbyView lobby, GameView game, LeaderboardView leader) {
        frame = new Frame(login, lobby, game, leader);
    }

    /**
     * Initializes new game.
     */
    public void createGameView() {
        frame.newGame();
    }

    /**
     * Creates new socket connection between client and server. Returns true if
     * socket is created.
     *
     * @param cont
     * @param host
     * @param port
     */
    public boolean newServerConnection() {

        try {
            socket = new Socket(loginController.host, loginController.port);

        } catch (UnknownHostException ex) {
            return false;
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
        return true;

    }

    /**
     * Associates model with lobbyController.
     *
     * @param cont
     */
    public void setLobbyController(LobbyController cont) {
        this.lobbyController = cont;// **see line 26
    }

    /**
     * Associates model with loginController.
     *
     * @param cont
     */
    public void setLoginController(LoginController cont) {
        this.loginController = cont;

    }

    /**
     * Associates model with gameController.
     *
     * @param cont
     */
    public void setGameController(GameController cont) {
        this.gameController = cont;
    }

    /**
     * Associates model with leaderboardController
     *
     * @param cont
     */
    public void setLeaderboardController(LeaderboardController cont) {
        this.leaderController = cont;
    }

    /**
     * Updates frame to display lobby view. Also updates frame title bar to
     * display current username.
     *
     * @param user
     */
    public void loginLobbyTrans() {
        loginController.closeView(consts.LOBBY);
        lobbyController.setupIOStreams();
        frame.setTitle("Gomoku || " + username);

    }

    /**
     * Updates
     *
     * @param online
     */
    void updateLobbyPlayers(String[] online) {
        lobbyController.updateOnlinePlayers(online);

    }

    /**
     * Creates a starts new thread that will accept incoming connection from
     * peer.
     */
    public void openGameConnection() {

        worker = new Thread(this);
        worker.start();

    }

    /**
     * Transitions from lobby view to game view.
     */
    public void lobbyGameTrans() {
        frame.updateView(consts.GAME);
    }

    /**
     * Transitions from game view to lobby view.
     */
    public void gameLobbyTrans() {
        frame.updateView(consts.LOBBY);
        frame.newGame();
    }

    /**
     * Transitions from leaderboard view to lobby view.
     */
    public void statsLobbytrans() {
        frame.updateView(consts.LOBBY);
    }

    /**
     * Transitions from lobby view to leaderboard view.
     */
    public void lobbyLeaderTrans() {
        frame.updateView(consts.LEADERBOARD);

        try {
            leaderController.setIOStreams(socket.getInputStream(), socket.getOutputStream());

        } catch (IOException e) {
            //e.printStackTrace();
            //add error handling
        }
        leaderController.getStats();

    }

    /**
     * Transitions from game view to login view.
     */
    public void gameLoginTrans() {
        frame.updateView(consts.LOGIN);
        frame.newGame();
    }

    /**
     * Started by openGameConnection. Creates new serverSocket and waits for a
     * client to connect to it. Sets up IO streams
     */
    @Override
    public void run() {
        try {
            ss = new ServerSocket(port);

            challengeeSocket = ss.accept();

            gameController.setServerSocket(ss);
            gameController.setInputStream(challengeeSocket.getInputStream());
            gameController.setOutputStream(challengeeSocket.getOutputStream());
            gameController.setMainInputStreams(socket.getInputStream(), socket.getOutputStream());
            gameController.newGame();
            gameController.setTurnOrder(true);

        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Connects to opponents server socket.
     *
     * @param m
     */
    void connectToOpponent(String m) {
        try {
            opponent = new Socket(m, port);

            gameController.setSocket(opponent);
            gameController.setInputStream(opponent.getInputStream());
            gameController.setOutputStream(opponent.getOutputStream());
            gameController.setMainInputStreams(socket.getInputStream(), socket.getOutputStream());
            gameController.newGame();
            gameController.setTurnOrder(false);

        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles GUI transition from login view to game view.
     *
     * @param difficulty
     */
    public void aiGameTrans(String difficulty) {
        gameController.setTurnOrder(true);
        gameController.setaiGame(true, difficulty);
        frame.updateView(consts.GAME);

    }

    /**
     * Pass stats to leaderboard view to update leaderboard.
     *
     * @param s
     */
    public void processStats(String[] s) {
        leaderController.updateStatsBoard(s);
    }

    /**
     * If connection is lost to server, transitions back to login view.
     */
    public void lostConnection() {
        frame.updateView(consts.LOGIN);
    }
}
