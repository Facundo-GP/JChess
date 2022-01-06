package com.src.pieces;


import com.src.utils.*;
import java.util.ArrayList;


public class Rook extends Piece {
    
    public int Mx;
    public int mx;
    public int My;
    public int my;

    public Rook(String color, String path) {
        super(color,path);
        this.type = "Rook";
        this.child = this;
    }

    public void show_moves(){
        
        this.aviable_moves = new ArrayList<Point>();
        this.prior_moves = new ArrayList<Point>();

        this.Mx = 8;
        this.mx = -1;
        this.My = 8;
        this.my = -1;
        
        Boolean Mx_finded = false;
        Boolean My_finded = false;

        for (int i = 0; i <= 7; i++){

            if (i != this.pos.x){
                if (this.panel[this.pos.y][i].object != null ){
                    
                    if (i > this.pos.x && !Mx_finded){
                        this.Mx = i;
                        if (this.panel[this.pos.y][i].object.child.color != this.color){
                            this.Mx++;
                        }
                        Mx_finded = true;
                    }

                    if (i < this.pos.x && i > this.mx){
                        this.mx = i;
                        if (this.panel[this.pos.y][i].object.child.color != this.color){
                            this.mx--;
                        }
                    }
                }
                this.prior_moves.add(new Point(this.pos.y,i));  
            }
            if (i != this.pos.y){
                if (this.panel[i][this.pos.x].object != null){
                    
                    if (i > this.pos.y &&  !My_finded){
                        this.My = i; 
                        if (this.panel[i][this.pos.x].object.child.color != this.color){
                            this.My++;    
                        }
                        My_finded = true;                      
                    }

                    if (i < this.pos.y && i > this.my){
                        this.my = i;
                        if (this.panel[i][this.pos.x].object.child.color != this.color){
                            this.my--;
                        }  
                    }
                }
                this.prior_moves.add(new Point(i,this.pos.x));  
            }
        }
        

        for (Point p : this.prior_moves) {
            if (p.x <= 7 && p.x >= 0 && p.y <= 7 && p.y >= 0){

                //Moves only if there is not a piece of the same color and type is not King
                if (((this.panel[p.y][p.x].object != null && 
                    (this.panel[p.y][p.x].object.child.color != this.color) &&
                     this.panel[p.y][p.x].object.child.type != "King") 
                    || (this.panel[p.y][p.x].object == null))
                    && (p.x > this.mx && p.x < this.Mx && p.y > this.my && p.y < this.My)) {
                 
                    this.highlight_green(p.y,p.x);
                    this.aviable_moves.add(p);  
                }       
            }
        }
    }
}
