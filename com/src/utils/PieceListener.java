package com.src.utils;

import java.awt.event.*;
import javax.swing.*;

import com.src.pieces.Piece;

import java.awt.Color;

public class PieceListener implements MouseListener {

    public Boolean choosing_move = false;
    public Piece object_selected;

    @Override
    public void mouseClicked(MouseEvent event) {
       
        Object source = event.getSource();
    
        if(source instanceof JChessPanel){
            JChessPanel temp = (JChessPanel) source;
            if (temp.object != null) 
            {
                
                if (choosing_move){
                    for (Point p : this.object_selected.child.aviable_moves) {
                        if (p.x == temp.gridx && p.y == temp.gridy){
                            this.object_selected.child.move(p.y,p.x);
                            this.choosing_move = false;
                        }
                    }

                    for (Point p : this.object_selected.child.aviable_moves) {

                        if ((p.x+p.y) % 2 == 0){
                            temp.object.panel[p.y][p.x].setBackground(Color.GRAY); 
                        }
                        else {
                            temp.object.panel[p.y][p.x].setBackground(Color.LIGHT_GRAY);
                        }
                       temp.object.panel[p.y][p.x].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
                    }
                    this.choosing_move = false;
                }
                else {
                    temp.object.child.show_moves();
                    this.choosing_move = true;
                    this.object_selected = temp.object;  
                }
                
            }

            else {
                if (choosing_move){
                    for (Point p : this.object_selected.child.aviable_moves) {
                        if (p.x == temp.gridx && p.y == temp.gridy){
                            this.object_selected.child.move(p.y,p.x);
                            this.choosing_move = false;
                        }
                    }

                    for (Point p : this.object_selected.child.aviable_moves) {

                        if ((p.x+p.y) % 2 == 0){
                            temp.object.panel[p.y][p.x].setBackground(Color.GRAY); 
                        }
                        else {
                            temp.object.panel[p.y][p.x].setBackground(Color.LIGHT_GRAY);
                        }
                       temp.object.panel[p.y][p.x].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
                    }
                }
            }   
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        
        Object source = arg0.getSource();
        if (!choosing_move){
            if(source instanceof JChessPanel){
                JChessPanel temp = (JChessPanel) source;
                if (temp.object != null) 
                {
                    temp.object.child.show_moves();
                }
                
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {

        Object source = arg0.getSource();
    
        if(source instanceof JChessPanel){
            JChessPanel temp = (JChessPanel) source;
            if (temp.object != null && !choosing_move) 
            {
                for (Point p : temp.object.child.aviable_moves) {
                    if ((p.x+p.y) % 2 == 0){
                        temp.object.panel[p.y][p.x].setBackground(Color.GRAY); 
                    }
                    else {
                        temp.object.panel[p.y][p.x].setBackground(Color.LIGHT_GRAY);
                    }
                    temp.object.panel[p.y][p.x].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
                }
            }
            
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

}

