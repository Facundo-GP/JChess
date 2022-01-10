package com.GUI;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import com.GUI.components.NameField;
import com.GUI.menus.MatchMenu;
import com.GUI.menus.StartMenu;

//Class that handles all menus and actions, the whole game
public class Game extends JFrame implements KeyListener,MouseListener {
    
    //Menus
    private MatchMenu MatchMenu;
    private StartMenu StartMenu;
    
    //Name fields
    private NameField NameField1;
    private NameField NameField2;
    
    //Frame that wrapps all menus
    private JFrame Frame;

    //Game state (used to switch between menus)
    private String State = "StartMenu";


    private void Init(){
        
        //Frame set up
        this.Frame = new JFrame("JChess");
        this.Frame.setLocationRelativeTo(null);
        this.Frame.setBackground(Color.LIGHT_GRAY);
        this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Frame.setVisible(true);
        this.Frame.revalidate();

        //Start menu
        this.RenderStartMenu();

    }

    //Method that renders the start menu
    private void RenderStartMenu(){
        
        //Removes all component of frame
        this.Frame.getContentPane().removeAll();

        //Creates a start menu, set it focusable, gives it a KeyListenes and MouseListener
        this.StartMenu = new StartMenu();
        this.Frame.getContentPane().add(this.StartMenu);
        
        if (this.MatchMenu != null){
            this.MatchMenu.setFocusable(false);
        }
        this.StartMenu.setFocusable(true);
        this.StartMenu.grabFocus();
        this.StartMenu.addKeyListener(this);
        this.StartMenu.Start.Button.addMouseListener(this);
        
        //revalidate to see changes
        this.Frame.revalidate();
        
        //Switch state to be consistents with the menu
        this.State = "StartMenu";

    }

    //Method that renders the name selection menu
    private void RenderNameSelection(){
        
        //Adds two components (name fields) to StartMenu
        GridBagConstraints LayourConstraints = new GridBagConstraints();

        this.NameField1 = new NameField(LayourConstraints,"Player 1 name:",18,50,3);
        this.StartMenu.add(this.NameField1,LayourConstraints);

        this.NameField2 = new NameField(LayourConstraints,"Player 2 name:",18,50,4);
        this.StartMenu.add(this.NameField2,LayourConstraints);
      
        this.Frame.getContentPane().add(this.StartMenu);

        //Sets menu to focusable and gives NameFields texfields a KeyListener
        this.StartMenu.setFocusable(true);
        this.StartMenu.grabFocus();
        this.StartMenu.addKeyListener(this);
        this.NameField1.TextField.addKeyListener(this);
        this.NameField2.TextField.addKeyListener(this);

        //revalidate to see changes
        this.Frame.revalidate();

        //Switch state to be consistents with the menu
        this.State="SelectingNames";

    }

    //Method that renders the match menu
    public void RenderMatchPanel(){

        //Removes StartMenu from frame
        this.Frame.getContentPane().removeAll();
            
        //Gets info for player names textfields
        String PlayerOneName = this.NameField1.TextField.getText();
        String PlayerTwoName = this.NameField2.TextField.getText();
            
        //Default players names
        if (PlayerOneName.length() == 0){
            PlayerOneName = "Player 1";
        }
        if (PlayerTwoName.length() == 0){
            PlayerTwoName = "Player 2";
        }
 
        //Adds MatchMenu to frame, set it focusable and gives it a KeyListener
        this.MatchMenu = new MatchMenu(this,PlayerOneName,PlayerTwoName);
        this.Frame.getContentPane().add(this.MatchMenu);
        this.StartMenu.setFocusable(false);
        this.MatchMenu.setFocusable(true);
        this.MatchMenu.grabFocus();
        this.MatchMenu.addKeyListener(this);
            
        //revalidate to see changes
        this.Frame.revalidate();

         //Switch state to be consistents with the menu
        this.State = "Playing";

    }

    //Starts the whole game
    public void run(){
        this.Init();
    }
  

    @Override
    public void mouseClicked(MouseEvent event) {
       
        Object source = event.getSource();
        if(source instanceof JButton){

            //If click StartButton being in StartMenu switch to NameSelectionMenu
            if (this.State == "StartMenu"){   
                this.RenderNameSelection();
            }

            //If click StartButton being in NameSelectionMenu switch to MatchMenu
            else if (this.State == "SelectingNames"){
                this.RenderMatchPanel();
            }
            
        }
    }

    public void keyPressed(KeyEvent e) {

        //If r key pressed being in MatchMenu, reset game
        if (e.getKeyChar() == 'r' && this.MatchMenu != null){
            
            this.MatchMenu.Match.Reset();
        }

        //If ESC key pressed, exit game
        else if (e.getKeyChar() == KeyEvent.VK_ESCAPE){
            
            this.Frame.dispose();
        }

        //If ENTER key pressed, advance to next menu (if being in MatchMenu do nothing)
        else if (e.getKeyChar() == KeyEvent.VK_ENTER){
            
            if (this.State == "StartMenu"){
                this.RenderNameSelection();
            }
            
            else if (this.State == "SelectingNames"){
                this.RenderMatchPanel();
            }
            
        }

        //If q key pressed, go to StartMenu
        else if (e.getKeyChar() == 'q'){
            this.RenderStartMenu();
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
