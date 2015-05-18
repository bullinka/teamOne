
import javax.swing.JOptionPane;

/**
 * Team One GUI Implementation Gomoku CSCE 320 - Spring 2015 3/16/2015 Java -
 * JVM
 *
 * Revisions: 3/14/2015 - View created by Karen Bullinger.
 * 5/9/2015 - Edited layout of view. Added action listener to quitB. -- Karen Bullinger
 * 5/16/2015 - Added play anonymous feature. - Karen Bullinger
 * 5/18/2015 - Adjusted view to maintain layout and removed unused imports.
 *                  - Karen Bullinger
 */

/*
 * This class is responsible for managing the Login View.
 */

public class LoginView extends javax.swing.JPanel {

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        gomokuL = new javax.swing.JLabel();
        usernameL = new javax.swing.JLabel();
        passwordL = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        passwordTF = new javax.swing.JPasswordField();
        aiList = new javax.swing.JComboBox();
        registerB = new javax.swing.JButton();
        loginB = new javax.swing.JButton();
        orL = new javax.swing.JLabel();
        ipTF = new javax.swing.JTextField();
        portTF = new javax.swing.JTextField();
        ipAddressL = new javax.swing.JLabel();
        portL = new javax.swing.JLabel();
        quitB = new javax.swing.JButton();
        playAnonB = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        gomokuL.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        gomokuL.setText("Gomoku");

        usernameL.setText("Username:");

        passwordL.setText("Password:");

        aiList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Difficulty", "Easy", "Moderate", "Hard" }));
        aiList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aiListActionPerformed(evt);
            }
        });

        registerB.setText("Register");
        registerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBActionPerformed(evt);
            }
        });

        loginB.setText("Login");
        loginB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBActionPerformed(evt);
            }
        });

        orL.setText("OR");

        ipAddressL.setText("IP Address");

        portL.setText("Port");

        quitB.setText("Quit");

        playAnonB.setText("Play Anonymously");
        playAnonB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playAnonBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ipAddressL)
                    .addComponent(portL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(portTF, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(ipTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quitB)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(usernameL)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(gomokuL))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(79, 79, 79)
                                    .addComponent(passwordL))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(passwordTF)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aiList, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playAnonB)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(orL))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(registerB, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loginB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gomokuL)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameL)
                    .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordL)
                    .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerB)
                    .addComponent(loginB))
                .addGap(43, 43, 43)
                .addComponent(playAnonB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aiList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipAddressL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portL)
                    .addComponent(quitB))
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private LoginController controller;
    byte[] input = new byte[1024];
    char[] passwordArray;

    /**
     * Launches AI game when difficult is selected from drop down list.
     *
     * @param evt
     */
    private void aiListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aiListActionPerformed
        String difficulty = (String) aiList.getSelectedItem();
        if (!difficulty.equals("Select Difficulty")) {
            controller.aiGame(difficulty);
        }    }//GEN-LAST:event_aiListActionPerformed
    /**
     * Initializes registration process when Register button is clicked.
     *
     * @param evt
     */
    private void registerBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBActionPerformed
        if (!ipTF.getText().equals("")) {
            controller.host = ipTF.getText();
        }
        if (!portTF.getText().equals("")) {
            controller.port = Integer.parseInt(portTF.getText());
        }

        if (usernameTF.getText().length() > 0 && passwordTF.getPassword().length > 0) {
            if (usernameTF.getText().contains(" ") || new String(passwordTF.getPassword()).contains(" ")) {
                displayErrorMessage("Username/password cannot contain spaces.");
            } else {
                controller.newConnection();
                char[] passwordArray = passwordTF.getPassword();
                String passwordinput = new String(passwordArray);
                controller.register(usernameTF.getText(), passwordinput.hashCode());
                controller.setUsername(usernameTF.getText());
            }
        } else {
            displayErrorMessage("Please enter a username and password.");

        }
    }//GEN-LAST:event_registerBActionPerformed

    /**
     * Initializes login process when login button is pressed.
     *
     * @param evt
     */
    private void loginBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBActionPerformed
        if (!ipTF.getText().equals("")) {
            controller.host = ipTF.getText();
        }
        if (!portTF.getText().equals("")) {
            controller.port = Integer.parseInt(portTF.getText());
        }

        if (usernameTF.getText().length() < 1 || passwordTF.getPassword().length < 1) {
            displayErrorMessage("Please enter a username and password.");
        } else {
            controller.newConnection();
            String passwordinput = new String(passwordArray);
            controller.login(usernameTF.getText(), passwordinput.hashCode());
            controller.setUsername(usernameTF.getText());
        }
    }//GEN-LAST:event_loginBActionPerformed
    /**
     * Closes application when quit button is pressed.
     *
     * @param evt
     */
    private void quitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitBActionPerformed
    /**
     * Logs user in as an anonymous player when Play Anonymous button is
     * pressed.
     *
     * @param evt
     */
    private void playAnonBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playAnonBActionPerformed
        if (!ipTF.getText().equals("")) {
            controller.host = ipTF.getText();
        }
        if (!portTF.getText().equals("")) {
            controller.port = Integer.parseInt(portTF.getText());
        }

        controller.newConnection();

        String userTemp = controller.loginAnon();
        controller.setUsername(userTemp);
    }//GEN-LAST:event_playAnonBActionPerformed

    /**
     * Associates view with controller.
     *
     * @param cont
     */
    public void setController(LoginController cont) {
        this.controller = cont;

    }

    /**
     * Associates controller with view.
     */
    public void setControllerView() {
        controller.setView(this);
    }

    /**
     * Error message pop up box.
     *
     * @param errorMessage
     */
    void displayErrorMessage(String errorMessage) {

        JOptionPane.showMessageDialog(this, errorMessage);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox aiList;
    private javax.swing.JLabel gomokuL;
    private javax.swing.JLabel ipAddressL;
    private javax.swing.JTextField ipTF;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton loginB;
    private javax.swing.JLabel orL;
    private javax.swing.JLabel passwordL;
    public javax.swing.JPasswordField passwordTF;
    private javax.swing.JButton playAnonB;
    private javax.swing.JLabel portL;
    private javax.swing.JTextField portTF;
    private javax.swing.JButton quitB;
    private javax.swing.JButton registerB;
    private javax.swing.JLabel usernameL;
    public javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
