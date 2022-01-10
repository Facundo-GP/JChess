package com.src;

import com.src.pieces.*;
import com.GUI.components.PlayerInfo;
import com.GUI.utils.IconPaths;

import java.util.ArrayList;

//Player class, handles state of player and his info
public class Player extends IconPaths{

    //Pieces of player
    public ArrayList<Piece> Pieces = new ArrayList<Piece>();
    
    //General info
    private String Color;
    public int Score;
    public PlayerInfo Info;
    
    //Game that player is playing
    public Match Game;
    
    //Opponent info
    public Player Opponent;
    public Piece OpponentKing;
    
    //Player states
    public Boolean InCheck = false;
    public String State;


    //Assign pieces to player
    private void setPieces(){

        if (this.Color == "black"){
            
            this.Pieces.add(new Rook(this.Color,this.Paths[5]));
            this.Pieces.add(new Knight(this.Color,this.Paths[2]));
            this.Pieces.add(new Bishop(this.Color,this.Paths[0]));
            this.Pieces.add(new Queen(this.Color,this.Paths[4]));
            this.Pieces.add(new King(this.Color,this.Paths[1]));
            this.Pieces.add(new Bishop(this.Color,this.Paths[0]));
            this.Pieces.add(new Knight(this.Color,this.Paths[2]));
            this.Pieces.add(new Rook(this.Color,this.Paths[5]));

            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[3]));

            for (Piece p : this.Pieces){
                p.Player = this;
            } 
            
            this.State = "Waiting";
        }

        else {

            this.Pieces.add(new Rook(this.Color,this.Paths[11]));
            this.Pieces.add(new Knight(this.Color,this.Paths[8]));
            this.Pieces.add(new Bishop(this.Color,this.Paths[6]));
            this.Pieces.add(new Queen(this.Color,this.Paths[10]));
            this.Pieces.add(new King(this.Color,this.Paths[7]));
            this.Pieces.add(new Bishop(this.Color,this.Paths[6]));
            this.Pieces.add(new Knight(this.Color,this.Paths[8]));
            this.Pieces.add(new Rook(this.Color,this.Paths[11]));

            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            this.Pieces.add(new Pawn(this.Color,this.Paths[9]));
            

            for (Piece p : this.Pieces){
                p.Player = this;
            } 
            this.State = "Playing";

        }
    
    }

    //Sets player general info
    public Player(String color){
        this.Color = color;
        this.Score = 0;
        this.setPieces();
    }

}
