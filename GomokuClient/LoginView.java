
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * Team One GUI Implementation Gomoku CSCE 320 - Spring 2015 3/16/2015 Java -
 * JVM Sources:
 *
 * Revisions: 3/14/2015 - View created by Karen Bullinger.
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

        setBackground(new java.awt.Color(204, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        quitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(79, 79, 79)
                        .addComponent(registerB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orL)
                            .addComponent(loginB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(aiList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
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
                .addGap(29, 29, 29)
                .addComponent(orL)
                .addGap(18, 18, 18)
                .addComponent(aiList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipAddressL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portL)
                    .addComponent(quitB))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents
    private LoginController controller;
    byte[] input = new byte[1024];
    char[] passwordArray;

    private void aiListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aiListActionPerformed
        String difficulty = (String) aiList.getSelectedItem();
        if (!difficulty.equals("Select Difficulty")) {
            controller.aiGame(difficulty);
        }    }//GEN-LAST:event_aiListActionPerformed

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
                System.out.println("Executing else.");
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
            System.out.println(controller);
            controller.newConnection();
            char[] passwordArray = passwordTF.getPassword();
            String passwordinput = new String(passwordArray);
            controller.login(usernameTF.getText(), passwordinput.hashCode());
            controller.setUsername(usernameTF.getText());
        }
    }//GEN-LAST:event_loginBActionPerformed

    private void quitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitBActionPerformed

    /**
     * Associates view with controller.
     *
     * @param cont
     */
    public void setController(LoginController cont) {
        this.controller = cont;

        System.out.println(controller);
    }

    public void setControllerView() {
        controller.setView(this);
    }

    private void quitBActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
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
    private javax.swing.JLabel portL;
    private javax.swing.JTextField portTF;
    private javax.swing.JButton quitB;
    private javax.swing.JButton registerB;
    private javax.swing.JLabel usernameL;
    public javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
