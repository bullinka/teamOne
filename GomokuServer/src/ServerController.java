//package gomokuserver;

/*
 * Controlls what goes to and from the server model, view and client programs
 */

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
 *
 * @author Joseph Bowley
 */
public class ServerController implements Runnable {
    //fields
    private final Constants constant = new Constants();
    private final ServerModel model;
    private final ServerView view;
    private ServerSocket serverSocket;
    private InputStream in;
    private OutputStream out;
    private Thread worker;
    
    /**
     * constructor to initialize fields
     * @param m
     * @param v 
     */
    public ServerController( ServerModel m, ServerView v)
    {
        model = m;
        view = v;
        try {
            serverSocket = new ServerSocket(view.getPort());
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * tries to accept connections from clients and stores them into a list of connections
     */
    public void startServer()
    {
        while(true)
        {
            try {
                Socket socket = serverSocket.accept();
                in = socket.getInputStream();
                out = socket.getOutputStream();
                model.addConnection(new Player(socket, new DataOutputStream(out), new DataInputStream(in), this));
            } catch (IOException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setNumConnections();
        }
    }
    
    /**
     * Allows the view to show the number of connections
     */
    public void setNumConnections()
    {
        view.setNumConnections(model.getNumConnections());
    }

    /**
     * Starts the server
     */
    @Override
    public void run() {
       startServer();
    }
    
    /**
     * Starts a new thread of this to accept connections from clients
     */
    public void getConnections()
    {
        worker = new Thread(this);
        worker.start();
    }
    
    /**
     * Sets the server socket to null, sets all connections to null, and exits the program.
     */
    public void stopServer()
    {
        serverSocket = null;
        model.disconnect();
        System.exit(0);
    }
    
    /**
     * displays a message sent by a client to the view 
     * @param username
     */
    public void addPlayer(String username)
    {
        view.addNewPlayer(username + "\n");
    }
    
    /**
     * Posts a message to the server view
     * @param message 
     */
    public void postMessage(String message)
    {
        view.addMessageToView(message);
    }
    
    /**
     * removes a connection from the list of all connections
     * @param c 
     */
    public void removePlayer(Player c)
    {
        model.removeConnection(c);
        view.setNumConnections(model.getNumConnections());
        model.sendMessageToAll(constant.ONLINE + " " + model.getAllUsernames());
    }
    
    /**
     * registers in the specified player using their username and password
     * return true if successful false otherwise
     * @param uName
     * @param pWord
     * @return 
     */
    public boolean registerPlayer(String uName, String pWord)
    {
        return model.registerPlayer(uName, pWord);
    }
    
    /**
     * logs in the specified player using their username and password
     * return true if successful false otherwise
     * @param uName
     * @param pWord
     * @return 
     */
    public boolean loginPlayer(String uName, String pWord)
    {
        return model.login(uName, pWord);
    }
    
    /**
     * returns a string containing all online users usernames
     * @return 
     */
    public String getAllUserNames()
    {
        return model.getAllUsernames();
    }
    /**
     * Sends a challenge message to the specified player
     * @param challengee
     * @param message 
     */
    public void sendChallenge(String challengee, String message) {
        model.sendChallenge(challengee, message);
    }

    /**
     * Method to send a response to a challenging user
     * @param challenger
     * @param response 
     */
    public void sendResponse(String challenger, String response) {
         model.sendResponse(challenger, response); 
    }
    
    /**
     * Adds a user to matchmaking
     * @param username
     * @param p 
     */
   public void addToMatchMaking(String username, Player p){
       model.addToMatchMaking(username, p);
   }
   
    /**
     * Sends a message to all of the users online
     * @param message
     */
   public void sendMessageToAll(String message)
   {
       model.sendMessageToAll(message);
   }
   
   /**
    * returns the specified users IP address as a string
    * @param user
    * @return 
    */
   public String getUsersIPAddress(String user)
   {
       return model.getUsersIPAddress(user);
   }

    public void sendRescind(String challenger, String response) {
	model.sendRescind(challenger, response); 
	
    }
    
    /**
     * Tells the model to add a player to the statistics data
     * @param username 
     */
    public void addPlayerToStats(String username)
    {
        model.addPlayerToStats(username);
    }
    
    /**
     * Tells the model to add a win to the specified players statistics data 
     * @param user
     */
    public void addWinToPlayerStats(String user){
        model.addWinToPlayerStats(user);
    }
    
    /**
     * Tells the model to add a loss to the specified players statistics data 
     * @param user
     */
    public void addLossToPlayerStats(String user){
        model.addLossToPlayerStats(user);
    }
    
    /**
     * returns a string representation of all stored statistics
     * @return 
     */
    public String getAllStatistics()
    {
        return model.getAllStatistics();
    }
    
   /**
     * returns the number assigned to the end of each anonymous user
     * @return 
     */ 
    public int getAnonNum()
    {
        return model.getAnonNum();
    }
}
