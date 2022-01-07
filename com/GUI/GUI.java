package com.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import java.awt.event.*;
import com.src.Board;
import com.src.Player;
import com.src.Game;

public class GUI extends JFrame implements KeyListener,MouseListener {
    
    private GamePanel gp;
    private StartMenu sm;
    private NameField nf1;
    private NameField nf2;
    private JFrame frame;
    private String state = "StartMenu";


    private void init(){
        
        this.frame = new JFrame("JChess");

        this.render_startmenu();

        //end
        this.frame.setLocationRelativeTo(null);
        this.frame.setBackground(Color.LIGHT_GRAY);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.revalidate();
    }

    private void render_startmenu(){
        
        this.frame.getContentPane().removeAll();

        this.sm = new StartMenu();
        this.frame.getContentPane().add(this.sm);
        
        if (this.gp != null){
            this.gp.setFocusable(false);
        }
        this.sm.setFocusable(true);
        this.sm.grabFocus();
        this.sm.addKeyListener(this);
        this.sm.start.button.addMouseListener(this);
        this.frame.revalidate();
        
        this.state = "StartMenu";

    }

    private void render_namefields(){
        
        GridBagConstraints gbcnt = new GridBagConstraints();

        this.nf1 = new NameField(gbcnt,"Player 1 name:",18,50,3);
        this.sm.add(this.nf1,gbcnt);

        this.nf2 = new NameField(gbcnt,"Player 2 name:",18,50,4);
        this.sm.add(this.nf2,gbcnt);
      
        this.frame.getContentPane().add(this.sm);

        this.sm.setFocusable(true);
        this.sm.grabFocus();
        this.sm.addKeyListener(this);
        this.nf1.textfield.addKeyListener(this);
        this.nf2.textfield.addKeyListener(this);

        this.frame.revalidate();
        this.state="SelectingNames";

    }

    private void render_gamepanel(){

        this.frame.getContentPane().remove(this.sm);
                
        String p1_name = this.nf1.textfield.getText();
        String p2_name = this.nf2.textfield.getText();
                
        if (p1_name.length() == 0){
            p1_name = "Player1";
        }
        if (p2_name.length() == 0){
            p2_name = "Player2";
        }
 
        this.gp = new GamePanel(p1_name,p2_name);

        this.frame.getContentPane().add(this.gp);
                
        this.sm.setFocusable(false);
        this.gp.setFocusable(true);
        this.gp.grabFocus();
        this.gp.addKeyListener(this);
                
        this.frame.revalidate();
        this.state = "Playing";

    }

    public void run(){
        this.init();
    }
  

    @Override
    public void mouseClicked(MouseEvent event) {
       
        Object source = event.getSource();
        if(source instanceof JButton){

            if (this.state == "StartMenu"){
                
                this.render_namefields();
            }

            else if (this.state == "SelectingNames"){
                this.render_gamepanel();
            }
            
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'r' && this.gp != null){
            
            this.gp.game.reset();
        }

        else if (e.getKeyChar() == KeyEvent.VK_ESCAPE){
            
            this.frame.dispose();
        }

        else if (e.getKeyChar() == KeyEvent.VK_ENTER){
            
            if (this.state == "StartMenu"){
                this.render_namefields();
            }
            
            else if (this.state == "SelectingNames"){
                this.render_gamepanel();
            }
            
        }

        else if (e.getKeyChar() == 'q'){
            this.render_startmenu();
        }
    } 

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

    public void keyReleased (KeyEvent e) {}   

    public void keyTyped(KeyEvent e) {}  


}
