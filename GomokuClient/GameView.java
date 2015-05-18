
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * This class manages the GUI of the game View. 
 */
/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 4/14/2015 - Class created by Joseph Bowley.
 * 5/1/2015 - Modified grid action listeners to chek for valid
 * moves and show them. Added win() lose() displayMove(), and
 * setController() methods. 
 * 5/2/2015 - Invalid moves display not a valid move instead
 * of not turn. Added to the win method to transition back
 * into Lobby.Added resign action listener
 * 5/8/2015 - Cleaned up GUI.
 * 5/9/2015 - Added turn notifications and updateTurnNotification
 * method.
 * 5/18/2015 - added constants and comments. 
 * 
 */
public class GameView extends javax.swing.JPanel {

    private JButton[][] grid;
    private Map<JButton, String> buttonMap = new HashMap<JButton, String>();
    private GameController controller;
    private Constants consts = new Constants();
    private int width;
    private int length;
    private String[] coordinates;
    
    
    /**
     * This action listener that listens for game moves.
     */
    private final ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton pressedButton = (JButton) e.getSource();
           
            coordinates = buttonMap.get(pressedButton).split(" ");
            String response = controller.validateSelf(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
            if (response.equals(consts.VALID)) {
                pressedButton.setBackground(Color.BLUE);
                pressedButton.setEnabled(false);

            } else if (response.equals(consts.INVALID)) {
                displayErrorMessage("That move is invalid.");
            } else if (response.equals(consts.WIN)) {
                pressedButton.setBackground(Color.BLUE);
                pressedButton.setEnabled(false);
                win();
            }
        }
    };

    /**
     * Creates new form GameView
     */
    public GameView() {
        initComponents();
        gameView(30, 30);
    }

    /**
     * Creates a grid of buttons and associated action listeners of
     * dimensions w X l.
     * @param w the width of the game grid.
     * @param l the length of the game grid
     */
    public void gameView(int w, int l) {
        this.length = l;
        this.width = w;
        game.setLayout(new GridLayout(width, length));
        this.add(game, BorderLayout.CENTER);
        grid = new JButton[width][length]; //allocate the size of grid
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = new JButton(); //creates new button 
                grid[x][y].addActionListener(listener);
                game.add(grid[x][y]); //adds button to grid
                buttonMap.put(grid[x][y], Integer.toString(x) + " " + Integer.toString(y));
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        game = new javax.swing.JPanel();
        quitB = new javax.swing.JButton();
        gameNotificationL = new javax.swing.JLabel();
        resignB = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Gomoku");

        game.setMaximumSize(new java.awt.Dimension(400, 400));
        game.setMinimumSize(new java.awt.Dimension(400, 400));
        game.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout gameLayout = new javax.swing.GroupLayout(game);
        game.setLayout(gameLayout);
        gameLayout.setHorizontalGroup(
            gameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        gameLayout.setVerticalGroup(
            gameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        quitB.setText("Quit");
        quitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBActionPerformed(evt);
            }
        });

        resignB.setText("Resign");
        resignB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resignBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(gameNotificationL))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(game, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quitB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resignB)
                .addGap(128, 128, 128))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(gameNotificationL)
                .addGap(18, 18, 18)
                .addComponent(game, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitB)
                    .addComponent(resignB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * This method updates the status text showing
     * the user if it is their turn.
     * @param b True if it is the users turn, false if it isn't
     */
    public void updateTurnNotification(boolean b){
        if(b){
        gameNotificationL.setText("It's your turn!");
        }
        else
        gameNotificationL.setText("It's not your turn.");
    }
    
    /**
     * Quits the program.
     * @param evt The event is the quit button being clicked
     */
    private void quitBActionPerformed(java.awt.event.ActionEvent evt) {
        controller.quit();
    }

    /**
     * User resigns from the game.
     * @param evt The event is the resign button beging clicked.
     */
    private void resignBActionPerformed(java.awt.event.ActionEvent evt) {
        controller.resign();
    }

    /**
     * Displays an opponent's move.
     * @param x x position on the grid.
     * @param y y position on the grid.
     */
    public void displayMove(int x, int y) {
        grid[x][y].setBackground(Color.RED);
        grid[x][y].setEnabled(false);
    }

    /**
     * Error message pop up box.
     *
     * @param errorMessage
     */
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    /**
     * Sets the controller for the view.
     * @param cont The view associated controller.
     */
    public void setController(GameController cont) {
        this.controller = cont;
    }

    /**
     * Method called when the user wins.
     */
    public void win() {
        displayErrorMessage("You Win!");
        if(controller.aiGame && !controller.loggedIn()){
           controller.switchToLogin();
        }
        else
            controller.switchToLobby();
    }

    /**
     * Method called when the user loses.
     */
    public void lose() {
        displayErrorMessage("You Lose!");
        if(controller.aiGame && !controller.loggedIn()){
                controller.switchToLogin();
        } else
            controller.switchToLobby();
    }

    /**
     * Resets the Grid to blanks.
     */
    public void resetBoard() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y].setBackground(null);
                grid[x][y].setEnabled(true);
            }
        }
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel game;
    private javax.swing.JLabel gameNotificationL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton quitB;
    private javax.swing.JButton resignB;
    // End of variables declaration//GEN-END:variables
}
