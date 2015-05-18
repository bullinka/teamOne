//package gomokuserver;

/*
 * A view that represents the server classes
 * Displays messages sent to server for debugging purposes
 */

/**
 *
 * @author Joseph Bowley
 */
public class ServerView extends javax.swing.JFrame {
    
    private ServerController controller;
    private boolean serverUp = false;

    /**
     * Creates new form ServerView
     */
    public ServerView() {
        initComponents();
    }
    
    /**
     * Sets controller to cont argument
     * @param cont 
     */
    public void setController(ServerController cont)
    {
        this.controller = cont;
    }
    
    /**
     * Displays the number of connections in the view
     * @param c 
     */
    public void setNumConnections(int c)
    {
        playerNumberTF.setText(Integer.toString(c));
    }
    
    /**
     * Tells the controller to start accepting connections
     */
    public void startServer()
    {
        controller.getConnections();
    }
    
    /**
     * Returns the specified port in the gui as an int
     * @return 
     */
    public int getPort()
    {
        return Integer.parseInt(portTF.getText());
    }
    
    /**
     * Displays a message sent by a client on the view 
     * @param newPlayer
     */
    public void addNewPlayer(String newPlayer)
    {
        playersTA.append(newPlayer);
    }
    
    /**
     * Appends a message to the server view
     * Used for easier server debugging
     * @param message 
     */
    public void addMessageToView(String message)
    {
        playersTA.append(message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        playersTA = new javax.swing.JTextArea();
        startServerButton = new javax.swing.JButton();
        portTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        playerNumberTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        playersTA.setColumns(20);
        playersTA.setLineWrap(true);
        playersTA.setRows(5);
        jScrollPane1.setViewportView(playersTA);

        startServerButton.setText("Start Server");
        startServerButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerButtonActionPerformed(evt);
            }
        });

        portTF.setText("54321");

        jLabel1.setText("Port");

        jLabel2.setText("Number of Players");

        playerNumberTF.setColumns(3);
        playerNumberTF.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startServerButton)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19)
                        .addComponent(playerNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startServerButton)
                    .addComponent(portTF)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerButtonActionPerformed
       if(serverUp == false)
       {
            startServer();
            //System.out.println("serverStarted");
            startServerButton.setText("Exit Server");
            serverUp = true;
       }
       else
       {
           controller.stopServer();
           serverUp = false;
           startServerButton.setText("Start Server");
       }
    }//GEN-LAST:event_startServerButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField playerNumberTF;
    private javax.swing.JTextArea playersTA;
    private javax.swing.JTextField portTF;
    private javax.swing.JButton startServerButton;
    // End of variables declaration//GEN-END:variables
}
