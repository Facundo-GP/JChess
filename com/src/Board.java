package com.src;

import java.awt.*;
import javax.swing.*;

import com.src.utils.PieceListener;
import com.src.utils.JChessPanel;


//Board class, implements graphical features of board and Listeners for click on pieces

public class Board extends JPanel{ 

    //Board of the match
    public JChessPanel[][] Boxes = new JChessPanel[8][8];
    
    //Listener to piece actions
    public PieceListener PieceListener;

    //Draws images for all pieces in the board
    private void setBoard(Player BlackPlayer, Player WhitePlayer){

         //Black pieces
        for (int i = 0; i <= 15; i++){
            BlackPlayer.Pieces.get(i).DrawIn(Boxes,(i / 8), i % 8);
            Boxes[i/8][i%8].Player = BlackPlayer; 
        }
      
        //White pieces
        for (int i = 0; i <= 15; i++){
            WhitePlayer.Pieces.get(i).DrawIn(Boxes,7 - (i / 8), i % 8);
            Boxes[7 - (i / 8)][i%8].Player = WhitePlayer;
        }
        
    }
    
    //Draws the board (background and squares)
    public Board(Player BlackPlayer, Player WhitePlayer){

        //Layout
		setLayout(new GridBagLayout());
        
        //Listener for pieces
        this.PieceListener = new PieceListener();
        this.PieceListener.Board = this;
       
        //Draw squares
        for (int j = 0; j < 8; j++){
            for (int k = 0; k < 8; k++){
                Boxes[j][k] = new JChessPanel();
                Boxes[j][k].setPreferredSize( new Dimension(60,60));
                if ((j+k) % 2 == 0){
                    Boxes[j][k].setBackground(Color.GRAY); 
                }
                else {
                    Boxes[j][k].setBackground(Color.LIGHT_GRAY);
                }
                
                //Adds listener to squares
                Boxes[j][k].addMouseListener(this.PieceListener);
                
                //Constraints for each square layout
                GridBagConstraints LayoutConstrains = new GridBagConstraints();
                LayoutConstrains.gridx=k;
                LayoutConstrains.gridy=j;
                add(Boxes[j][k], LayoutConstrains);
                Boxes[j][k].Gridy = j;
                Boxes[j][k].Gridx = k;
                Boxes[j][k].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
                
            }
                
        }

        //Draws pieces images
        this.setBoard(BlackPlayer,WhitePlayer);
    

    }
    
}
