package com.src.pieces;

import com.src.utils.*;
import java.util.ArrayList;


public class Knight extends Piece {
    
    public Knight(String color,String path) {
        super(color,path);
        this.type = "Knight";
        this.child = this;
        this.points=3;
    }


    public void show_moves(){
        this.aviable_moves = new ArrayList<Point>();
        this.prior_moves = new ArrayList<Point>();

    
        this.prior_moves.add(new Point(this.pos.y+1,this.pos.x+2));
        this.prior_moves.add(new Point(this.pos.y+1,this.pos.x-2));
        this.prior_moves.add(new Point(this.pos.y-1,this.pos.x+2));
        this.prior_moves.add(new Point(this.pos.y-1,this.pos.x-2));
        this.prior_moves.add(new Point(this.pos.y+2,this.pos.x+1));
        this.prior_moves.add(new Point(this.pos.y+2,this.pos.x-1));
        this.prior_moves.add(new Point(this.pos.y-2,this.pos.x+1));
        this.prior_moves.add(new Point(this.pos.y-2,this.pos.x-1));

        
        for (Point p : this.prior_moves) {
            if (p.x < 8 && p.x >= 0 && p.y < 8 && p.y >= 0){

                //Moves only if there is not a piece of the oposite color and type is not King
                if ((this.panel[p.y][p.x].object != null && 
                    (this.panel[p.y][p.x].object.child.color != this.color)
                    && this.panel[p.y][p.x].object.child.type != "King")
                    || (this.panel[p.y][p.x].object == null)) {
                 
                    this.highlight_green(p.y,p.x);
                    this.aviable_moves.add(p);  
                }       
            }
        }
    }
}