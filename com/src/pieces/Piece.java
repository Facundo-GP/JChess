package com.src.pieces;

import com.src.utils.*;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;


public class Piece {

    public String color;
    public Point pos = new Point(0,0);
    public PieceImage image;
    public String type;
    public Piece child;
    public JChessPanel[][] panel;
    public ArrayList<Point> aviable_moves;
    public ArrayList<Point> prior_moves;


    Piece(){
    }

    Piece(String color, String path) {
        this.image = new PieceImage(path);
        this.color = color;
    }

    public void draw_in(JChessPanel[][] panel, int y, int x){
        this.panel = this.image.draw_in(panel,y,x);
        this.pos.update(y,x);
        panel[y][x].object = this;
        


    }

    public  void erase_from(JChessPanel[][] panel,int y,int x){
        this.panel = this.image.erase_from(panel,y,x);
        this.panel[y][x].object=null;
    
    }

    public void killed() {
        this.erase_from(this.panel,this.pos.y,this.pos.x);
    }

    public void highlight_green(int y, int x){
        //this.panel[y][x].setBackground(new Color(113,216,97));
        this.panel[y][x].setBackground(new Color(149,201,141));
        this.panel[y][x].setBorder(BorderFactory.createLineBorder(new Color(40,40,40)));
    }

    //Childs methods

    //Draws boxes aviables to move
    public void show_moves(){}

    //executes the movement
    public void move(int y, int x){
        if (this.panel[y][x].object != null){
            this.panel[y][x].object.killed();
        }
        this.erase_from(this.panel,this.pos.y,this.pos.x);
        this.draw_in(this.panel, y, x);
    }


}

