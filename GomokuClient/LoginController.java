
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 3/14/2015 - Class created by Karen Bullinger. 4/5/2015 -
 * Implemented login and register methods 4/11/2015 - Corrected login method to
 * not create a new connection when one is already established. Added string
 * constants for Login/Register methods.
 * 5/16/205 - Added play anonymous feature
 */
public class LoginController {

    private LoginView view;
    private ClientModel model;
    private OutputStream outStream;
    private InputStream inStream;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private byte[] msg = new byte[1024];
    private String returnedMsg;
    private String success = "success";
    private String fail = "fail";
    public boolean connected = false;
    private int serverPort = 54321;
    String host = "127.0.0.1";
    int port = 54321;
    private Constants consts = new Constants();

    /**
     * Default constructor.
     */
    public LoginController() {
    }

    /**
     * Associates model with controller.
     * @param m     ClientModel
     */
    public void setModel(ClientModel m) {
        model = m;
    }
    /**
     * Associates view with controller
     * @param v    LoginView
     */
    public void setView(LoginView v) {
        view = v;
    }

    /**
     * Sets login view to visible in frame.
     */
    public void initLoginView() {
        model.frame.setVisible(true); //makes frame visible
    }

    public void closeView(String u) {
        model.frame.updateView(u);
    }

    public void setUsername(String user) {
        model.username = user;
    }

    /**
     * Process login. Sends username and hashed password to server, waits for
     * success or fail message. If login successful, GUI transitions to lobby.
     * If fail, pop up appears alerting user.
     *
     * @param user
     * @param password
     * @return true if login succeeds
     */
    public boolean login(String user, int password) {

        boolean waiting = true;
        user = user.toLowerCase();
        String info = consts.LOGIN + " " + user + " " + password;

        try {
            dataOut.write(info.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (waiting) {
                int len = dataIn.read(msg);

                if (len > 0) {
                    returnedMsg = new String(msg, 0, len);
                    String[] msgArray;
                    msgArray = returnedMsg.split("[ ]+");

                    if (msgArray[0].equals(consts.SUCCESS)) {
                        waiting = false;
                        model.setUsername(user);
                        model.setLoggedIn(true);
                        model.loginLobbyTrans(); /*if login successful, transition*/
                        model.updateLobbyPlayers(msgArray);

                        return true;
                    } else if (msgArray[0].equals(consts.FAIL)) {
                        waiting = false;
                        view.displayErrorMessage("Username/password incorrect.");
                        return false;
                    } else {
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Process registration. Sends username and hashed password to server, waits
     * for success or fail message. If login successful, GUI transitions to
     * lobby. If fail, pop up appears alerting user.
     *
     * @param user
     * @param password
     * @return true if registration succeeds
     */
    public boolean register(String user, int password) {

        boolean waiting = true;


        user = user.toLowerCase();
        String info = consts.REGISTER + " " + user + " " + password; //string to send to server


        try {
            dataOut.write(info.getBytes()); //send registration to server
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (waiting) {
            try {
                int len = dataIn.read(msg);

                if (len > 0) {
                    returnedMsg = new String(msg, 0, len);
                    String[] msgArray;
                    msgArray = returnedMsg.split("[ ]+");
                    if (msgArray[0].equals(consts.SUCCESS)) {
                        waiting = false;
                        model.setLoggedIn(true);
                        model.loginLobbyTrans();
                        model.updateLobbyPlayers(msgArray);
                        return true;
                    } else if (msgArray[0].equals(consts.FAIL)) {

                        view.displayErrorMessage("Registration failed.  Username taken.");

                        return false;
                    }

                }
                if (len < 0) { //no longer connected to Server -- how do we want to handle this?
                    return false;

                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    /**
     * Calls method in ClientModel to create new connection to server if client
     * is not already connected. Initializes input and output streams.
     */
    public void newConnection() {
        if (connected == false) {
            try {
                model.newServerConnection();

                connected = true;

                inStream = model.socket.getInputStream();
                dataIn = new DataInputStream(inStream);
                outStream = model.socket.getOutputStream();
                dataOut = new DataOutputStream(outStream);

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Transfers GUI from login to game GUI
     */
    public void aiGame(String difficulty) {
        model.aiGameTrans(difficulty);

    }
    /**
     * Logs in user with anonymous username and no password.
     * Returns string with anonymous username.
     * @return username
     */
    public String loginAnon() {

        boolean waiting = true;

        String info = consts.ANONYMOUS;


        try {
            dataOut.write(info.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (waiting) {
                int len = dataIn.read(msg);

                if (len > 0) {
                    returnedMsg = new String(msg, 0, len);
                    String[] msgArray;
                    msgArray = returnedMsg.split("[ ]+");

                    if (msgArray[0].equals(consts.SUCCESS)) {
                        waiting = false;
                        model.setUsername(msgArray[1]);
                        model.setLoggedIn(true);
                        model.loginLobbyTrans(); /*if login successful, transition*/
                        model.updateLobbyPlayers(msgArray);

                        return msgArray[1];
                    } else if (msgArray[0].equals(consts.FAIL)) {
                        waiting = false;
                        view.displayErrorMessage("Unable to login.");
                        return "";
                    } else {
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }
}
