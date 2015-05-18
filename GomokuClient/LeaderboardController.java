
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM 
 *
 * Revisions: 3/14/2015 - Class created by Karen Bullinger.
 * 5/9/2015 - Add getStats(), setupIOStreams(). -- Karen Bullinger
 * 5/12/2015 - Create view transition method. -- Carl Derline
 * 5/18/2015 - Commented code, removed print statements. -- Karen Bullinger
 */

/**
 * This class is responsibly for managing the leaderboard view and data.
 */

public class LeaderboardController {

    private LeaderboardView leaderboard;
    private ClientModel model;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;

    /**
     * Associates view with controller.
     *
     * @param view
     */
    public void setView(LeaderboardView view) {
        this.leaderboard = view;
    }

    /**
     * Associates controller with model
     *
     * @param m
     */
    public void setModel(ClientModel m) {
        this.model = m;
    }

    /**
     * Sets up input and output streams for contacting server.
     *
     * @param s
     * @param o
     */
    public void setIOStreams(InputStream s, OutputStream o) {
        dataIn = new DataInputStream(s);
        dataOut = new DataOutputStream(o);
    }

    /**
     * Request stats from server.
     */
    public void getStats() {
        try {
            dataOut.write("stats".getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            //Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
            //Add error handling
        }
    }

    /**
     * Triggers Leaderboard update.
     *
     * @param m
     */
    public void updateStatsBoard(String[] m) {
        leaderboard.updateTable(m);
    }

    /**
     * Transitions user from Leaderboard view to Lobby view.
     */
    public void statsLobbyTrans() {
        model.statsLobbytrans();
    }
}
