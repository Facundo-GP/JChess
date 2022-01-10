package com.src.pieces;

import com.src.Player;
import com.src.utils.*;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

//Generic piece, implements method to draw, erase, move and reverse a move of a piece
//Also contains info of pieces, player that is used it and game in general
public class Piece {


    //Color and position of piece
    public String Color;
    public Point Pos = new Point(0,0);
    
    //Last positions and last piece killed
    private Point LastPos = new Point(0,0);
    private Piece LastPieceKilled;

    //Image icon, type of piece (Pawn,Rook,etc) and object of piece type
    public PieceImage Image;
    public String Type;
    
    //Game board
    public JChessPanel[][] Panel;
    
    //AvailableMoves (moves than a piece can do given a game context)
    //PriorMoves (moves than a piece can do regadless the game context)
    public ArrayList<Point> AvailableMoves;
    public ArrayList<Point> PriorMoves;

    //Piece states and general info
    public String State = "alive";
    public int Points;
    public Boolean Check = false;
    public Player Player;

    //flag saved for special movments for Pawn and King
    public Boolean SpecialRule = false;
    public Point SpecialRulePos;



    Piece(String Color, String path) {
        this.Image = new PieceImage(path);
        this.Color = Color;
    }

    //Draws piece in board in the specified coordinates
    public void DrawIn(JChessPanel[][] panel, int y, int x){

        this.Panel = this.Image.DrawIn(panel,y,x);
        
        this.LastPos.Update(this.Pos.y,this.Pos.x);
        this.Pos.Update(y,x);
        
        this.Panel[y][x].Piece = this;
        this.Panel[y][x].Piece.State = "alive";
        
        


    }

    //Erase piece for board in the specified coordinates
    public  void EraseFrom(JChessPanel[][] panel,int y,int x){
        this.Panel = this.Image.EraseFrom(panel,y,x);
        this.Panel[y][x].Piece=null;
    
    }

    //Implements the kill of a piece
    public void Killed() {
        this.EraseFrom(this.Panel,this.Pos.y,this.Pos.x);
        this.State = "killed";
    }

    //Highlights the boxes of the specified coordinates
    public void HighlightGreen(int y, int x){
        this.Panel[y][x].setBackground(new Color(149,201,141));
        this.Panel[y][x].setBorder(BorderFactory.createLineBorder(new Color(40,40,40)));
    }

    
    public void ShowAvailables(){
        for (Point p : this.AvailableMoves){
            this.HighlightGreen(p.y,p.x);
        }
    }
    
    public void BuildPriors(){}
    public void BuildAvailables(){}
    public void ShowMoves(){}


    //Executes the movement of a piece
    public void Move(int y, int x){
        if (this.Panel[y][x].Piece != null){
            this.Player.Score+=this.Panel[y][x].Piece.Points;
            this.Player.Info.Update();
            this.LastPieceKilled = this.Panel[y][x].Piece;
            this.Panel[y][x].Piece.Killed();
        }
        else {
            this.LastPieceKilled = null;  
        }

        this.Panel[y][x].Player = this.Player;
        
        
        this.EraseFrom(this.Panel,this.Pos.y,this.Pos.x);
        this.DrawIn(this.Panel, y, x);
        
     
    }

    //Reverse the last move done by a piece
    public void ReverseLastMove(){

        this.EraseFrom(this.Panel, this.Pos.y, this.Pos.x);

        if (this.LastPieceKilled != null) {
            this.Player.Score-=this.LastPieceKilled.Points;
            this.Player.Info.Update();
            this.LastPieceKilled.DrawIn(this.Panel,this.Pos.y,this.Pos.x);
            this.Panel[this.Pos.y][this.Pos.x].Player = this.LastPieceKilled.Player;
        }

        this.DrawIn(this.Panel, this.LastPos.y , this.LastPos.x);
    }


}

