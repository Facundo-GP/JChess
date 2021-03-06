package com.src.pieces;

import com.src.utils.*;
import java.util.ArrayList;

public class King extends Piece {

    public King(String Color,String path) {
        super(Color,path);
        this.Type = "King";
    }

    public void BuildPriors(){
        
        this.PriorMoves = new ArrayList<Point>();
        this.AvailableMoves = new ArrayList<Point>();

        this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x));
        this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x));
        this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x+1));
        this.PriorMoves.add(new Point(this.Pos.y,this.Pos.x+1));
        this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x+1));
        this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x-1));
        this.PriorMoves.add(new Point(this.Pos.y,this.Pos.x-1));
        this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x-1));
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
                
                if (this.SpecialRule){
                    for (Point pt: this.SpecialRulePos){
                        this.AvailableMoves.add(pt);
                    }
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

