package com.src.pieces;

import com.src.utils.*;
import java.util.ArrayList;


public class Bishop extends Piece {
    
    public int ru;
    public int rd;
    public int lu;
    public int ld;

    

    public Bishop(String color, String path) {
        super(color,path);
        this.type = "Bishop";
        this.child = this;
        this.points = 3;
    }

    private void build_priors(){

    
        this.ru = 8;
        this.rd = 8;
        this.lu=  -1;
        this.ld = -1;



        for (int i = 1; i <= 7; i++){
            
            if (this.pos.x+i <= 7 && this.pos.y+i <= 7 && this.panel[this.pos.y+i][this.pos.x+i].object != null){
               if (this.pos.x+i < this.rd){
                   this.rd = this.pos.x+i;
               }       
            }
        
            if (this.pos.x+i <= 7 && this.pos.y-i >= 0 && this.panel[this.pos.y-i][this.pos.x+i].object != null){
                if (this.pos.x+i < this.ru){
                    this.ru = this.pos.x+i;
                } 
             }

             if (this.pos.x-i >= 0 && this.pos.y+i <= 7 && this.panel[this.pos.y+i][this.pos.x-i].object != null){
                if (this.pos.x-i > this.ld){
                    this.ld = this.pos.x-i;
                }   
             }

             if (this.pos.x-i >= 0 && this.pos.y-i >= 0 && this.panel[this.pos.y-i][this.pos.x-i].object != null){
                if (this.pos.x-i > this.lu){
                    this.lu = this.pos.x-i;
                }
             }

             this.prior_moves.add(new Point(this.pos.y+i,this.pos.x+i));
             this.prior_moves.add(new Point(this.pos.y-i,this.pos.x+i));
             this.prior_moves.add(new Point(this.pos.y+i,this.pos.x-i));
             this.prior_moves.add(new Point(this.pos.y-i,this.pos.x-i));
        }

        if (this.ru == 8){
            this.ru = 8;
        }
        if (this.rd== 8){
            this.rd = 8;
        }
        if (this.lu == -1){
            this.lu = -1;
        }
        if (this.ld == -1){
            this.ld = -1;
        } 

    }

    private void build_aviables(){
        
        for (Point p : this.prior_moves) {
            if (p.x < 8 && p.x >= 0 && p.y < 8 && p.y >= 0){
                //Moves only if there is not a piece of the same color and type is not King
                if (((this.panel[p.y][p.x].object != null && 
                    (this.panel[p.y][p.x].object.child.color != this.color) &&
                     this.panel[p.y][p.x].object.child.type != "King") 
                    || (this.panel[p.y][p.x].object == null))) {

        
                        if (p.x > this.pos.x && p.y > this.pos.y && p.x <= this.rd){ 
                            
                            if (this.panel[p.y][p.x] != null && p.x == this.rd && 
                               this.panel[p.y][p.x].object.child.color != this.color){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }
                            else if (p.x != this.rd){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }
                        }

                        if (p.x > this.pos.x && p.y < this.pos.y && p.x <= this.ru){
                            
                            if (this.panel[p.y][p.x] != null && p.x == this.ru && 
                                this.panel[p.y][p.x].object.child.color != this.color){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }
                            else if (p.x != this.ru){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }
                        }

                        if (p.x < this.pos.x && p.y > this.pos.y && p.x >= this.ld){
                            
                            if (this.panel[p.y][p.x] != null && p.x == this.ld && 
                                this.panel[p.y][p.x].object.child.color != this.color){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }
                            else if (p.x != this.ld){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }
                        }

                        if (p.x < this.pos.x && p.y < this.pos.y && p.x >= this.lu){
                            
                            if (this.panel[p.y][p.x] != null && p.x == this.lu && 
                            this.panel[p.y][p.x].object.child.color != this.color){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }
                            else if (p.x != this.lu){
                                this.highlight_green(p.y,p.x);
                                this.aviable_moves.add(p);
                            }   
                        }
                }       
            }
        }
    }

    public void show_moves(){
        
        this.prior_moves = new ArrayList<Point>();
        this.aviable_moves = new ArrayList<Point>();

        this.build_priors();
        this.build_aviables();
        
    }

}
