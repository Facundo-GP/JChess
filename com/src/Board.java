package com.src;

import java.awt.*;
import javax.swing.*;

import com.src.utils.PieceListener;
import com.src.utils.JChessPanel;




public class Board extends JPanel {


    public JChessPanel[][] table = new JChessPanel[8][8];
    
    private void set_board(Player b_player, Player w_player){

         //black pieces
        for (int i = 0; i <= 15; i++){
            b_player.pieces.get(i).draw_in(table,(i / 8), i % 8);
        }
      
        //white pieces
        for (int i = 0; i <= 15; i++){
            w_player.pieces.get(i).draw_in(table,7 - (i / 8), i % 8);

        }
        
    }
    
    public Board(Player b_player, Player w_player){

		setLayout(new GridBagLayout());
        PieceListener listener = new PieceListener();
       
   
        for (int j = 0; j < 8; j++){
            for (int k = 0; k < 8; k++){
                table[j][k] = new JChessPanel();
                table[j][k].setPreferredSize( new Dimension(60,60));
                if ((j+k) % 2 == 0){
                    table[j][k].setBackground(Color.GRAY); 
                }
                else {
                    table[j][k].setBackground(Color.LIGHT_GRAY);
                }
             
                table[j][k].addMouseListener(listener);
                GridBagConstraints c = new GridBagConstraints();
                c.gridx=k;
                c.gridy=j;
                add(table[j][k], c);
                table[j][k].gridy = j;
                table[j][k].gridx = k;
                table[j][k].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
                
            }
                
        }
        this.set_board(b_player,w_player);
    

    }
    
}
