package com.src.pieces;

import com.src.utils.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    

    public Pawn(String Color, String path){
        super(Color,path);
        this.Type = "Pawn";
        this.Points=1;
    }

    public void BuildPriors(){
        
        this.PriorMoves = new ArrayList<Point>();
        this.AvailableMoves = new ArrayList<Point>();

        //Basic moves with no Piece available for capture
        if (this.Color == "black" && this.Pos.y == 1 || this.Color == "white" && this.Pos.y == 6){
           
          if (this.Color == "white"){
            this.PriorMoves.add(new Point(this.Pos.y-2,this.Pos.x) );
            this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x) );

           }

           if (this.Color == "black"){
            this.PriorMoves.add(new Point(this.Pos.y+2,this.Pos.x) );
            this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x) );
           }
        }
        else{
            if (this.Color == "white"){
                this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x) );
            }

            if (this.Color == "black"){
                this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x) );
            }
      
        }

        //Piece availables for capture
        
        if (this.Color == "black"){
            this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x+1) );
            this.PriorMoves.add(new Point(this.Pos.y+1,this.Pos.x-1) );   
                 
        }

        if (this.Color == "white"){
            this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x+1) );
            this.PriorMoves.add(new Point(this.Pos.y-1,this.Pos.x-1) );       
        }
        
    }

    public void BuildAvailables(){
        this.Check = false;
        for (Point p : this.PriorMoves) {
            if (p.x < 8 && p.x >= 0 && p.y < 8 && p.y >= 0){
                
                //Moves Piece 2 squares only if no Piece is blocking the path
                if (p.y == this.Pos.y+2 || p.y == this.Pos.y-2){
                    if (this.Color == "black" && this.Panel[this.Pos.y+1][p.x].Piece == null
                        && this.Panel[this.Pos.y+2][p.x].Piece == null){
                        this.AvailableMoves.add(p);
                    }

                    if (this.Color == "white" && this.Panel[this.Pos.y-1][p.x].Piece == null
                        && this.Panel[this.Pos.y-2][p.x].Piece == null){
                        this.AvailableMoves.add(p);
                    }     
                }

                //Moves diagonal only if there is a Piece of the oposite Color and is not King
                else if ((this.Pos.x != p.x && this.Panel[p.y][p.x].Piece != null 
                    && (this.Panel[p.y][p.x].Piece.Color != this.Color))){
                    
                    if (this.Pos.x != p.x && this.Panel[p.y][p.x].Piece != null &&
                       (this.Panel[p.y][p.x].Piece.Color != this.Color) &&
                       this.Panel[p.y][p.x].Piece.Type == "King"){
                        
                        this.Check = true;
                    }
                    else {
                        this.AvailableMoves.add(p);
                    }    
                    
                }

                //Moves 1 square if there is not a Piece blocking the path
                else if (this.Pos.x == p.x && this.Panel[p.y][p.x].Piece == null){
                    this.AvailableMoves.add(p);  
                }

                //Special case of Pawn captured for move 2 squares
                if (this.SpecialRule){
                    this.AvailableMoves.add(this.SpecialRulePos);
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