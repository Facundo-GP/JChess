package com.GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.src.Board;
import com.src.Player;
import com.src.Game;

public class GUI extends JFrame implements KeyListener {
    
    public Game game;

    public void render(){
        //init
        JFrame frame = new JFrame("JChess");
        frame.addKeyListener(this);
        Container container = frame.getContentPane();
        
        GridBagLayout gbl = new GridBagLayout();
        container.setLayout(gbl);
        
        GridBagConstraints gbcnt = new GridBagConstraints();
        
        //Componets
        Title title = new Title(gbcnt,"JChess");
        frame.getContentPane().add(title,gbcnt);

        PlayerInfo b_player = new PlayerInfo(gbcnt,"Facundo","black");
        frame.getContentPane().add(b_player,gbcnt);
        
        PlayerInfo w_player = new PlayerInfo(gbcnt,"Luis","white");
        frame.getContentPane().add(w_player,gbcnt);

        ShowBoard sb = new ShowBoard(gbcnt);
        this.game = new Game(b_player.player,w_player.player);
        frame.getContentPane().add(this.game.start(),gbcnt);

        //end
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'r'){
            this.game.reset();
        }
    } 

    public void keyReleased (KeyEvent e) {}   

    public void keyTyped(KeyEvent e) {}    


}
