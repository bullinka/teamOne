
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

/**
 * This class manages the lobby view.
 * 
 * Revisions:
 * 5/4/2015 - Add Leaderboard button and action listener. -- Karen Bullinger
 * 5/9/2015 - Removed unused imports.  Commented code.  Changed design. -- Karen Bullinger
 * 5/9/2015 - Fix bug that allows user to challenge previously selected player even if not online.  -- Jon Julius
 * 5/9/2015 - Add AI select drop down list. -- Karen Bullinger
 * 5/12/2015 - Changed wording in AI list.  Edited AI list action performed.
 * 5/16/2015 - Commenting code -- Karen Bullinger
 */
public class LobbyView extends javax.swing.JPanel {

    DefaultListModel<String> onlineModel;
    DefaultListModel<String> receivedModel;
    DefaultListModel<String> sentModel;
    LobbyController controller;
    String userSelected;
    String userSelectedReceived = "";

    /**
     * Creates new form LobbyView Sets up list models for use in managing view
     * data.
     */
    public LobbyView() {

        onlineModel = new DefaultListModel<>();
        receivedModel = new DefaultListModel<>();
        sentModel = new DefaultListModel<>();

        initComponents();

        onlinePlayersList.setModel(onlineModel);
        challengesReceivedList.setModel(receivedModel);
        challengesSentList.setModel(sentModel);

        onlinePlayersList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onlinePlayersListValueChanged(evt);
            }
        });

        challengesReceivedList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                challengesReceivedListValueChanged(evt);
            }
        });

    }

    /**
     * Associates view and controller.
     *
     * @param lc
     */
    public void setController(LobbyController lc) {
        this.controller = lc;
    }

    /**
     * Called from LobbyController when server sends updated list of online
     * players.
     *
     * Clears previous list of players and replaces with current online players.
     *
     * @param online
     */
    public void updateOnlinePlayerList(String[] online) {
        onlineModel.clear();
        for (int i = 1; i < online.length; i++) {
            onlineModel.addElement(online[i]);
            if (!receivedModel.contains(online[i - 1]) && !receivedModel.isEmpty()) {
                receivedModel.remove(i - 1);
            }

            if (!sentModel.contains(online[i - 1]) && !sentModel.isEmpty()) {
                sentModel.remove(i - 1);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gomokuL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        onlinePlayersList = new javax.swing.JList<String>();
        jScrollPane2 = new javax.swing.JScrollPane();
        challengesReceivedList = new javax.swing.JList<String>();
        jScrollPane3 = new javax.swing.JScrollPane();
        challengesSentList = new javax.swing.JList<String>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        challengeB = new javax.swing.JButton();
        acceptB = new javax.swing.JButton();
        rejectB = new javax.swing.JButton();
        quitB = new javax.swing.JButton();
        leaderboardB = new javax.swing.JButton();
        aiList = new javax.swing.JComboBox<String>();

        gomokuL.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        gomokuL.setText("Gomoku");

        onlinePlayersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(onlinePlayersList);

        challengesReceivedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(challengesReceivedList);

        challengesSentList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(challengesSentList);

        jLabel1.setText("Online Users");

        jLabel2.setText("Challenges Received");

        jLabel3.setText("Challenges Sent");

        challengeB.setText("Challenge");
        challengeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                challengeBActionPerformed(evt);
            }
        });

        acceptB.setText("Accept");
        acceptB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBActionPerformed(evt);
            }
        });

        rejectB.setText("Reject");
        rejectB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectBActionPerformed(evt);
            }
        });

        quitB.setText("Quit");
        quitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBActionPerformed(evt);
            }
        });

        leaderboardB.setText("Leaderboard");
        leaderboardB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaderboardBActionPerformed(evt);
            }
        });

        aiList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Difficulty", "Easy", "Medium", "Hard" }));
        aiList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aiListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(gomokuL))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(challengeB)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(rejectB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(acceptB, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                                .addGap(28, 28, 28)))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(quitB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(aiList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(leaderboardB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gomokuL)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(challengeB)
                    .addComponent(acceptB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rejectB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(aiList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitB)
                    .addComponent(leaderboardB))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * If a user is selected in the online players list, a challenge is sent to
     * that player through the server.
     *
     * @param evt
     */
    private void challengeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_challengeBActionPerformed
        if (userSelected.equals(null)) {
            //return;
        }
        if (userSelected.equals(controller.getUsername())) {
            displayErrorMessage("User cannot challenge self.");
        } else {
            updateSentList(userSelected);
            controller.sendChallenge(userSelected);
            userSelected = null;
        }
    }//GEN-LAST:event_challengeBActionPerformed

    /**
     * Action listener for acceptB. Sends accept challenge response to server
     * via LobbyController.
     *
     * @param evt
     */
    private void acceptBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptBActionPerformed
        String temp = userSelectedReceived;
        if (!temp.isEmpty()) {
        controller.sendAcceptResponse(temp);    }//GEN-LAST:event_acceptBActionPerformed
    }

    /**
     * Action listener for rejectB. Sends reject challenge response to server
     * via LobbyController.
     *
     * @param evt
     */
    private void rejectBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectBActionPerformed
        controller.sendRejectResponse(userSelectedReceived);
    }//GEN-LAST:event_rejectBActionPerformed

    /**
     * Action listener for quitB. Closes application when button is clicked.
     *
     * @param evt
     */
    private void quitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitBActionPerformed
    /**
     * Transitions user from lobby view to leaderboard view where player
     * statistics for all players can be seen.
     *
     * @param evt
     */
    private void leaderboardBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaderboardBActionPerformed
        controller.lobbyLeaderTrans();
    }//GEN-LAST:event_leaderboardBActionPerformed

    /**
     * Launches new AI game based on difficulty selected from drop down list.
     *
     * @param evt
     */
    private void aiListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aiListActionPerformed
        String difficulty = (String) aiList.getSelectedItem();
        if (!difficulty.equals("Select Difficulty")) {
            controller.aiGame(difficulty);
        }
    }//GEN-LAST:event_aiListActionPerformed

    /**
     * List selection listener for onlinePlayersList. Keeps track of which
     * player is currently selected in the list of online players.
     *
     * @param evt
     */
    private void onlinePlayersListValueChanged(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting() && !onlinePlayersList.isSelectionEmpty()) {
            userSelected = (String) onlinePlayersList.getSelectedValue();
        }
    }

    /**
     * List selection listener for receivedChallengesList. Keeps track of which
     * player is currently select in the list of challenges received.
     *
     * @param evt
     */
    private void challengesReceivedListValueChanged(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting() && !challengesReceivedList.isSelectionEmpty()) {
            userSelectedReceived = (String) challengesReceivedList.getSelectedValue();
        }
    }

    /**
     * Updates Challenges Sent list when user sends a new challenge.
     *
     * @param userSelected
     */
    private void updateSentList(String userSelected) {

        sentModel.addElement(userSelected);


    }

    /**
     * Updates Challenges Received list when a player receives a new challenge.
     *
     * @param userSelected
     */
    public void updateReceivedList(String userSelected) {
        receivedModel.addElement(userSelected);
    }

    /**
     * Removes user challenged from the Sent Challenges list if the user rejects
     * the challenge. Displays message to user.
     *
     * @param name username of user rejecting challenge
     */
    public void removeFromSentChallenges(String name) {

        sentModel.removeElement(name);
        displayErrorMessage("User " + name + " rejected your game challenge.");
    }

    /**
     * Method called when user rejects a challenge. Removes the rejected
     * username from the list.
     *
     * @param name of user to be removed
     */
    public void removeFromReceivedChallenges(String name) {
        receivedModel.removeElement(name);
    }

    /**
     * Displays the given error message in a dialog box.
     *
     * @param errorMessage error message to display
     */
    public void displayErrorMessage(String errorMessage) {

        JOptionPane.showMessageDialog(this, errorMessage);
    }

    /**
     * Returns list model for sentList..
     *
     * @return
     */
    public DefaultListModel<String> getSentList() {
        return sentModel;
    }

    /**
     * Removes the given name from sent challenges.
     *
     * @param name
     */
    public void rescindFromSentChallenges(String name) {
        sentModel.removeElement(name);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptB;
    private javax.swing.JComboBox<String> aiList;
    private javax.swing.JButton challengeB;
    private javax.swing.JList<String> challengesReceivedList;
    private javax.swing.JList<String> challengesSentList;
    private javax.swing.JLabel gomokuL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton leaderboardB;
    private javax.swing.JList<String> onlinePlayersList;
    private javax.swing.JButton quitB;
    private javax.swing.JButton rejectB;
    // End of variables declaration//GEN-END:variables
}
