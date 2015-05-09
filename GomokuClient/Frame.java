/**
 * This class manages the card layout and all necessary views.
 */

import java.awt.CardLayout;

/**
 *
 * @author Karen
 */
public class Frame extends javax.swing.JFrame {
    
    /**
     * Creates new form frame
     */
    public Frame(LoginView login, LobbyView lobby, GameView game, LeaderboardView leader) {
        loginView1 = login;
        lobbyView1 = lobby;
        gameView1 = game;
        leaderView1 = leader;
        initComponents();
        this.setTitle("Gomoku");
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPane = new javax.swing.JPanel();
        loginPane = new javax.swing.JPanel();
        lobbyPane = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();
        PanThree = new javax.swing.JLabel();
        leaderPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout loginPaneLayout = new javax.swing.GroupLayout(loginPane);
        loginPane.setLayout(loginPaneLayout);
        loginPaneLayout.setHorizontalGroup(
            loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPaneLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(loginView1, 400, 500, 600)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        loginPaneLayout.setVerticalGroup(
            loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPaneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(loginView1, 400, 500, 600)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        mainPane.add(loginPane, "loginPane");

        javax.swing.GroupLayout lobbyPaneLayout = new javax.swing.GroupLayout(lobbyPane);
        lobbyPane.setLayout(lobbyPaneLayout);
        lobbyPaneLayout.setHorizontalGroup(
            lobbyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lobbyPaneLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(lobbyView1, 400, 500, 600)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        lobbyPaneLayout.setVerticalGroup(
            lobbyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lobbyPaneLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(lobbyView1, 400,500,600)
                .addContainerGap())
        );

        mainPane.add(lobbyPane, "lobby");
        lobbyPane.getAccessibleContext().setAccessibleName("lobby");
        lobbyPane.getAccessibleContext().setAccessibleDescription("");


        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
				.addComponent(gameView1, 600,600,600)
                .addContainerGap(551, Short.MAX_VALUE))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameView1, 600,600,600)
                .addContainerGap(375, Short.MAX_VALUE))
        );

        mainPane.add(gamePanel, "game");
        
         javax.swing.GroupLayout leaderPanelLayout = new javax.swing.GroupLayout(leaderPanel);
        leaderPanel.setLayout(leaderPanelLayout);
        leaderPanelLayout.setHorizontalGroup(
            leaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaderPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
				.addComponent(leaderView1, 400,400,400)
                .addContainerGap(551, Short.MAX_VALUE))
        );
        leaderPanelLayout.setVerticalGroup(
            leaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leaderView1, 400,400,400)
                .addContainerGap(375, Short.MAX_VALUE))
        );

        mainPane.add(leaderPanel, "leaderboard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPane, 600, 600, 600)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPane, 600, 600, 600)
                .addContainerGap(20, Short.MAX_VALUE))
        );

       //this.setMaximumSize(new java.awt.Dimension(500,100));
        pack();
    }// </editor-fold>//GEN-END:initComponents

     public void updateView(String l) {
        CardLayout card = (CardLayout)mainPane.getLayout();
        card.show(mainPane, l);
        
    }

    void updateOnlinePlayers(String[] online) {
        lobbyView1.updateOnlinePlayerList(online);
    }
    
    public void newGame()
    {
        //gameView1 = new GameView(30, 30);
        gameView1.resetBoard();
    }
 
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PanThree;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JPanel lobbyPane;
    private LobbyView lobbyView1;
    private javax.swing.JPanel loginPane;
    private LoginView loginView1;
    private javax.swing.JPanel mainPane;
    // End of variables declaration//GEN-END:variables
    private GameView gameView1;
    private LeaderboardView leaderView1;
    private javax.swing.JPanel leaderPanel;
	
  
}
