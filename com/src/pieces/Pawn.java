package com.src.pieces;

import com.src.utils.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    

    public Pawn(String color, String path){
        super(color,path);
        this.type = "Pawn";
        this.child = this;
        this.points=1;
    }

    

    public void show_moves(){
        this.aviable_moves = new ArrayList<Point>();
        this.prior_moves = new ArrayList<Point>();

        //Basic moves with no Piece aviable for capture
        if (this.color == "black" && this.pos.y == 1 || this.color == "white" && this.pos.y == 6){
           
          if (this.color == "white"){
            this.prior_moves.add(new Point(this.pos.y-2,this.pos.x) );
            this.prior_moves.add(new Point(this.pos.y-1,this.pos.x) );

           }

           if (this.color == "black"){
            this.prior_moves.add(new Point(this.pos.y+2,this.pos.x) );
            this.prior_moves.add(new Point(this.pos.y+1,this.pos.x) );
           }
        }
        else{
            if (this.color == "white"){
                this.prior_moves.add(new Point(this.pos.y-1,this.pos.x) );
            }

            if (this.color == "black"){
                this.prior_moves.add(new Point(this.pos.y+1,this.pos.x) );
            }
      
        }

        //Piece aviables for capture
        
        if (this.color == "black"){
            this.prior_moves.add(new Point(this.pos.y+1,this.pos.x+1) );
            this.prior_moves.add(new Point(this.pos.y+1,this.pos.x-1) );   
                 
        }

        if (this.color == "white"){
            this.prior_moves.add(new Point(this.pos.y-1,this.pos.x+1) );
            this.prior_moves.add(new Point(this.pos.y-1,this.pos.x-1) );       
        }
        

        for (Point p : this.prior_moves) {
            if (p.x < 8 && p.x >= 0 && p.y < 8 && p.y >= 0){
                
                //Moves piece 2 squares only if no piece is blocking the path
                if (p.y == this.pos.y+2 || p.y == this.pos.y-2){
                    if (this.color == "black" && this.panel[this.pos.y+1][p.x].object == null
                        && this.panel[this.pos.y+2][p.x].object == null){
                        
                        this.highlight_green(p.y,p.x);
                        this.aviable_moves.add(p);
                    }

                    if (this.color == "white" && this.panel[this.pos.y-1][p.x].object == null
                        && this.panel[this.pos.y-2][p.x].object == null){
                        this.highlight_green(p.y,p.x);
                        this.aviable_moves.add(p);
                    }     
                }

                //Moves diagonal only if there is a piece of the oposite color and is not King
                else if (this.pos.x != p.x && this.panel[p.y][p.x].object != null 
                    && (this.panel[p.y][p.x].object.child.color != this.color)
                    && this.panel[p.y][p.x].object.child.type != "King"){
                    
                    this.highlight_green(p.y,p.x);
                    this.aviable_moves.add(p);  
                }

                //Moves 1 square if there is not a piece blocking the path
                else if (this.pos.x == p.x && this.panel[p.y][p.x].object == null){
                    this.highlight_green(p.y,p.x);
                    this.aviable_moves.add(p);  
                }
                
                //Special case of Pawn captured for move 2 squares
                

            
            }
        }
    }
}