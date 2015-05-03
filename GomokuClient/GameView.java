/**
 * This class manages the GameView.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PLUCSCE
 */
public class GameView extends javax.swing.JPanel {
    private final JButton[][] grid;
    private Map<JButton, String> buttonMap = new HashMap<JButton, String>();
    private GameController controller;
    private JButton quitB;
    private JPanel game = new JPanel();
    private JPanel quit = new JPanel();
    private JLabel gomoku;
    private String[] coordinates;
    private JButton resignB;
    private Constants consts = new Constants();
    private final ActionListener listener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton pressedButton = (JButton)e.getSource();
            System.out.println(buttonMap.get(pressedButton));
            coordinates = buttonMap.get(pressedButton).split(" ");
            String response = controller.validateSelf(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
            if(response.equals("valid"))
            {
                pressedButton.setBackground(Color.BLUE);
                pressedButton.setEnabled(false);
                
            }
            else if(response.equals("invalid"))
            {
                displayErrorMessage("That move is invalid.");
            }
            else if(response.equals("win")){
            	pressedButton.setBackground(Color.BLUE);
                pressedButton.setEnabled(false);
            	win();
            }
        }
    };

    /**
     * Creates new form GameView
     * @param width
     * @param length
     */
    public GameView(int width, int length){ //constructor
        
        this.setLayout(new BorderLayout());
        game.setLayout(new GridLayout(width, length));
        this.add(game, BorderLayout.CENTER);
   
        grid=new JButton[width][length]; //allocate the size of grid
        for(int y=0; y<length; y++){
                for(int x=0; x<width; x++){
                        grid[x][y]=new JButton(); //creates new button 
                        grid[x][y].addActionListener(listener);
                        //grid[x][y].setPreferredSize(new java.awt.Dimension(20,20));
                        game.add(grid[x][y]); //adds button to grid
                        buttonMap.put(grid[x][y], Integer.toString(x) + " " + Integer.toString(y));
                }
        }
        
        gomoku = new JLabel("Gomoku");
        gomoku.setFont(new java.awt.Font("Tahoma", 1, 24));
        this.add(gomoku, BorderLayout.NORTH);
        quitB = new JButton("Quit");
        quitB.setPreferredSize(new Dimension(70 , 25));
        
        resignB = new JButton("Resign");
        resignB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resignBActionPerformed(evt);
            }
        });
        
        quitB.setText("Quit");
        quitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBActionPerformed(evt);
            }
        });
        quit.add(quitB);
        
        quit.setSize(25 ,80);
        this.add(quit, BorderLayout.PAGE_END);
      
        this.setSize(500, 500);
    }
    /**
     * Method is called when quit button is clicked.  
     * Closes application.
     * @param evt 
     */
    private void quitBActionPerformed(ActionEvent evt){
        System.exit(0);
    }
    private void resignBActionPerformed(ActionEvent evt){
            controller.sendResign(consts.RESIGN);
            controller.resign();
            
    }
    
    public void displayMove(int x, int y)
    {
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

   public void setController( GameController cont){
       this.controller = cont;
    }
   
   public void win()
   {
       displayErrorMessage("You Win!");
       controller.switchToLobby();
   }
public void lose() {
	displayErrorMessage("You Lose!");
    controller.switchToLobby();
	
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}