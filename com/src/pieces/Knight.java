package com.src.pieces;

import com.src.utils.*;
import java.util.ArrayList;


public class Knight extends Piece {
    
    public Knight(String Color,String path) {
        super(Color,path);
        this.Type = "Knight";
        this.Points=3;
    }

    public void BuildPriors(){
        
        this.PriorMoves = new ArrayList<Point>();
        this.AvailableMoves = new ArrayList<Point>();

    
        this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x+2));
        this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x-2));
        this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x+2));
        this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x-2));
        this.PriorMoves.add(new Point(this.Pos.y+2,this.Pos.x+1));
        this.PriorMoves.add(new Point(this.Pos.y+2,this.Pos.x-1));
        this.PriorMoves.add(new Point(this.Pos.y-2,this.Pos.x+1));
        this.PriorMoves.add(new Point(this.Pos.y-2,this.Pos.x-1));
    }

    public void BuildAvailables(){
        this.Check = false;
        for (Point p : this.PriorMoves) {
            if (p.x < 8 && p.x >= 0 && p.y < 8 && p.y >= 0){

                //Moves only if there is not a Piece of the oposite Color and Type is not King
                if ((this.Panel[p.y][p.x].Piece != null && 
                    (this.Panel[p.y][p.x].Piece.Color != this.Color)
                    && this.Panel[p.y][p.x].Piece.Type != "King")
                    || (this.Panel[p.y][p.x].Piece == null)) {
                 
                    this.AvailableMoves.add(p);  
                }
                if (p.x == this.Player.OpponentKing.Pos.x && p.y == this.Player.OpponentKing.Pos.y){
                    this.Check = true;
                }    
            }
        }
    }
    
    public void ShowMoves(){
        
        this.BuildPriors();
        this.BuildAvailables();
        this.ShowAvailables();

    }
}