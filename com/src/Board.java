package com.src;

import java.awt.*;
import javax.swing.*;


import com.src.utils.PieceListener;

import com.src.pieces.Bishop;
import com.src.pieces.King;
import com.src.pieces.Knight;
import com.src.pieces.Pawn;
import com.src.pieces.Queen;
import com.src.pieces.Rook;

import com.src.utils.JChessPanel;




public class Board extends JPanel {

    public int cols = 8;
    public int rows = 8;
    public String[] paths = {"/com/GUI/assets/b_bishop.png",
                            "/com/GUI/assets/b_king.png",
                            "/com/GUI/assets/b_knight.png",
                            "/com/GUI/assets/b_pawn.png",
                            "/com/GUI/assets/b_queen.png",
                            "/com/GUI/assets/b_rook.png",
                            "/com/GUI/assets/w_bishop.png",
                            "/com/GUI/assets/w_king.png",
                            "/com/GUI/assets/w_knight.png",
                            "/com/GUI/assets/w_pawn.png",
                            "/com/GUI/assets/w_queen.png",
                            "/com/GUI/assets/w_rook.png",
                        };

    public JChessPanel[][] squares = new JChessPanel[rows][cols];
    
    private void set_board(){

         //black pieces
        Player p1 = new Player("Esteban","black");
        for (int i = 0; i <= 7; i++){
            p1.pieces.get(i).draw_in(squares,0,i);
            p1.pieces.get(i+8).draw_in(squares,1,i);
        }
      
        //white pieces
        Player p2 = new Player("Facundo","white");
        for (int i = 0; i <= 7; i++){
            p2.pieces.get(i).draw_in(squares,7,i);
            p2.pieces.get(i+8).draw_in(squares,6,i);
        }
        
        
    }
    
    public Board(){

		setLayout(new GridBagLayout());
        PieceListener listener = new PieceListener();
       
   
        for (int j = 0; j < 8; j++){
            for (int k = 0; k < 8; k++){
                squares[j][k] = new JChessPanel();
                squares[j][k].setPreferredSize( new Dimension(60,60));
                if ((j+k) % 2 == 0){
                    squares[j][k].setBackground(Color.GRAY); 
                }
                else {
                    squares[j][k].setBackground(Color.LIGHT_GRAY);
                }
             
                squares[j][k].addMouseListener(listener);
                GridBagConstraints c = new GridBagConstraints();
                c.gridx=k;
                c.gridy=j;
                add(squares[j][k], c);
                squares[j][k].gridy = j;
                squares[j][k].gridx = k;
                squares[j][k].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
                
            }
                
        }
        this.set_board();
    

    }
    
}
