package com.GUI;

import java.awt.*;
import javax.swing.*;
import com.src.Board;
import com.src.Player;

public class GUI extends JFrame {
    
    public void render(){
        //init
        JFrame frame = new JFrame("JChess");
        Container container = frame.getContentPane();
        
        GridBagLayout gbl = new GridBagLayout();
        container.setLayout(gbl);
        
        GridBagConstraints gbcnt = new GridBagConstraints();
        
        //Componets
        Title title = new Title(gbcnt,"JChess");
        frame.getContentPane().add(title,gbcnt);

        PlayerInfo player1 = new PlayerInfo(gbcnt,"Facundo","black");
        frame.getContentPane().add(player1,gbcnt);
        
        ShowBoard board = new ShowBoard(gbcnt);
        frame.getContentPane().add(board,gbcnt);
        
        PlayerInfo player2 = new PlayerInfo(gbcnt,"Luis","white");
        frame.getContentPane().add(player2,gbcnt);

        //end
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
