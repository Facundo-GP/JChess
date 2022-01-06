package com.src;

import com.src.pieces.*;
import com.src.utils.Point;
import com.GUI.IconPaths;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends IconPaths{

    public ArrayList<Piece> pieces = new ArrayList<Piece>();
    public String name;
    public String color;
    public int points;

    private void set_pieces(){

        if (this.color == "black"){
            
            pieces.add(new Rook(this.color,this.paths[5]));
            pieces.add(new Knight(this.color,this.paths[2]));
            pieces.add(new Bishop(this.color,this.paths[0]));
            pieces.add(new Queen(this.color,this.paths[4]));
            pieces.add(new King(this.color,this.paths[1]));
            pieces.add(new Bishop(this.color,this.paths[0]));
            pieces.add(new Knight(this.color,this.paths[2]));
            pieces.add(new Rook(this.color,this.paths[5]));

            pieces.add(new Pawn(this.color,this.paths[3]));
            pieces.add(new Pawn(this.color,this.paths[3]));
            pieces.add(new Pawn(this.color,this.paths[3]));
            pieces.add(new Pawn(this.color,this.paths[3]));
            pieces.add(new Pawn(this.color,this.paths[3]));
            pieces.add(new Pawn(this.color,this.paths[3]));
            pieces.add(new Pawn(this.color,this.paths[3]));
            pieces.add(new Pawn(this.color,this.paths[3])); 
        }

        else {

            pieces.add(new Rook(this.color,this.paths[11]));
            pieces.add(new Knight(this.color,this.paths[8]));
            pieces.add(new Bishop(this.color,this.paths[6]));
            pieces.add(new Queen(this.color,this.paths[10]));
            pieces.add(new King(this.color,this.paths[7]));
            pieces.add(new Bishop(this.color,this.paths[6]));
            pieces.add(new Knight(this.color,this.paths[8]));
            pieces.add(new Rook(this.color,this.paths[11]));

            pieces.add(new Pawn(this.color,this.paths[9]));
            pieces.add(new Pawn(this.color,this.paths[9]));
            pieces.add(new Pawn(this.color,this.paths[9]));
            pieces.add(new Pawn(this.color,this.paths[9]));
            pieces.add(new Pawn(this.color,this.paths[9]));
            pieces.add(new Pawn(this.color,this.paths[9]));
            pieces.add(new Pawn(this.color,this.paths[9]));
            pieces.add(new Pawn(this.color,this.paths[9])); 

        }
    
    }


    public Player(String name, String color){
        this.name = name;
        this.color = color;
        this.points = 0;
        this.set_pieces();
    }


}
