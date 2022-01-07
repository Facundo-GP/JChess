package com.src.pieces;


import com.src.utils.*;
import java.util.ArrayList;


public class Queen extends Piece {

    public int ru;
    public int rd;
    public int lu;
    public int ld;

    public int Mx;
    public int mx;
    public int My;
    public int my;

    public ArrayList<Point> bishop_priors;
    public ArrayList<Point> rook_priors;

    public Queen(String color, String path) {
        super(color,path); 
        this.type = "Queen";
        this.child = this;
        this.points = 9;
    }

    //Movments in Queen are Bishop's and Rook's mixed
    private void build_priors(){
        
        //Same as Bishop
        this.ru = 8;
        this.rd = 8;
        this.lu=  -1;
        this.ld = -1;

        //Same as Rook
        this.Mx = 8;
        this.mx = -1;
        this.My = 8;
        this.my = -1;

        Boolean Mx_finded = false;
        Boolean My_finded = false;


        //Rook's loop
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
                this.rook_priors.add(new Point(this.pos.y,i));  
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
                this.rook_priors.add(new Point(i,this.pos.x));  
            }
        }
        

        //Bishop's loop
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

             this.bishop_priors.add(new Point(this.pos.y+i,this.pos.x+i));
             this.bishop_priors.add(new Point(this.pos.y-i,this.pos.x+i));
             this.bishop_priors.add(new Point(this.pos.y+i,this.pos.x-i));
             this.bishop_priors.add(new Point(this.pos.y-i,this.pos.x-i));
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
        
        //Rook's desicion rule
        for (Point p : this.rook_priors) {
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
    

        //Bishops's decision rule
        for (Point p : this.bishop_priors) {
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
        
        this.bishop_priors = new ArrayList<Point>();
        this.rook_priors = new ArrayList<Point>();
        this.aviable_moves = new ArrayList<Point>();

        this.build_priors();
        this.build_aviables();
        
    }

}
