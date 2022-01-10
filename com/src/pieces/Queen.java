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

    public Queen(String Color, String path) {
        super(Color,path); 
        this.Type = "Queen";
        this.Points = 9;
    }

    //Movments in Queen are Bishop's and Rook's mixed
    public void BuildPriors(){
        
        this.bishop_priors = new ArrayList<Point>();
        this.rook_priors = new ArrayList<Point>();
        this.AvailableMoves = new ArrayList<Point>();

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

            if (i != this.Pos.x){
                if (this.Panel[this.Pos.y][i].Piece != null ){
                    
                    if (i > this.Pos.x && !Mx_finded){
                        this.Mx = i;
                        if (this.Panel[this.Pos.y][i].Piece.Color != this.Color){
                            this.Mx++;
                        }
                        Mx_finded = true;
                    }

                    if (i < this.Pos.x && i > this.mx){
                        this.mx = i;
                        if (this.Panel[this.Pos.y][i].Piece.Color != this.Color){
                            this.mx--;
                        }
                    }
                }
                this.rook_priors.add(new Point(this.Pos.y,i));  
            }
            if (i != this.Pos.y){
                if (this.Panel[i][this.Pos.x].Piece != null){
                    
                    if (i > this.Pos.y &&  !My_finded){
                        this.My = i; 
                        if (this.Panel[i][this.Pos.x].Piece.Color != this.Color){
                            this.My++;    
                        }
                        My_finded = true;                      
                    }

                    if (i < this.Pos.y && i > this.my){
                        this.my = i;
                        if (this.Panel[i][this.Pos.x].Piece.Color != this.Color){
                            this.my--;
                        }  
                    }
                }
                this.rook_priors.add(new Point(i,this.Pos.x));  
            }
        }
        

        //Bishop's loop
        for (int i = 1; i <= 7; i++){
            
            if (this.Pos.x+i <= 7 && this.Pos.y+i <= 7 && this.Panel[this.Pos.y+i][this.Pos.x+i].Piece != null){
               if (this.Pos.x+i < this.rd){
                   this.rd = this.Pos.x+i;
               }       
            }
        
            if (this.Pos.x+i <= 7 && this.Pos.y-i >= 0 && this.Panel[this.Pos.y-i][this.Pos.x+i].Piece != null){
                if (this.Pos.x+i < this.ru){
                    this.ru = this.Pos.x+i;
                } 
             }

             if (this.Pos.x-i >= 0 && this.Pos.y+i <= 7 && this.Panel[this.Pos.y+i][this.Pos.x-i].Piece != null){
                if (this.Pos.x-i > this.ld){
                    this.ld = this.Pos.x-i;
                }   
             }

             if (this.Pos.x-i >= 0 && this.Pos.y-i >= 0 && this.Panel[this.Pos.y-i][this.Pos.x-i].Piece != null){
                if (this.Pos.x-i > this.lu){
                    this.lu = this.Pos.x-i;
                }
             }

             this.bishop_priors.add(new Point(this.Pos.y+i,this.Pos.x+i));
             this.bishop_priors.add(new Point(this.Pos.y-i,this.Pos.x+i));
             this.bishop_priors.add(new Point(this.Pos.y+i,this.Pos.x-i));
             this.bishop_priors.add(new Point(this.Pos.y-i,this.Pos.x-i));
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

    public void BuildAvailables(){
        this.Check = false;
        //Rook's desicion rule
        for (Point p : this.rook_priors) {
            if (p.x <= 7 && p.x >= 0 && p.y <= 7 && p.y >= 0){

                //Moves only if there is not a Piece of the same Color and Type is not King
                if (((this.Panel[p.y][p.x].Piece != null && 
                    (this.Panel[p.y][p.x].Piece.Color != this.Color)) 
                    || (this.Panel[p.y][p.x].Piece == null))
                    && (p.x > this.mx && p.x < this.Mx && p.y > this.my && p.y < this.My)) {
                        
                    if (this.Panel[p.y][p.x].Piece != null && 
                    (this.Panel[p.y][p.x].Piece.Color != this.Color) &&
                    this.Panel[p.y][p.x].Piece.Type =="King"){
                        this.Check = true;
                    }
                    else{
                        this.AvailableMoves.add(p);     
                    }
        
                }
    
            }
        }
    

        //Bishops's decision rule
        for (Point p : this.bishop_priors) {
            if (p.x < 8 && p.x >= 0 && p.y < 8 && p.y >= 0){
                //Moves only if there is not a Piece of the same Color and Type is not King
                if ((this.Panel[p.y][p.x].Piece != null && 
                    (this.Panel[p.y][p.x].Piece.Color != this.Color) 
                    || (this.Panel[p.y][p.x].Piece == null))) {

        
                        if (p.x > this.Pos.x && p.y > this.Pos.y && p.x <= this.rd){ 
                            
                            if (this.Panel[p.y][p.x].Piece != null && 
                                this.Panel[p.y][p.x].Piece.Color != this.Color &&
                                this.Panel[p.y][p.x].Piece.Type == "King"){
                                this.Check = true;
                            }

                            else {
                                if (this.Panel[p.y][p.x] != null && p.x == this.rd && 
                                    this.Panel[p.y][p.x].Piece.Color != this.Color){
                                    this.AvailableMoves.add(p);
                                }
                                else if (p.x != this.rd){
                                    this.AvailableMoves.add(p);
                                }
                            }
                        }

                        if (p.x > this.Pos.x && p.y < this.Pos.y && p.x <= this.ru){
                            
                            if (this.Panel[p.y][p.x].Piece != null && 
                                this.Panel[p.y][p.x].Piece.Color != this.Color &&
                                this.Panel[p.y][p.x].Piece.Type == "King"){
                                this.Check = true;
                            }
                            else {
                                if (this.Panel[p.y][p.x] != null && p.x == this.ru && 
                                    this.Panel[p.y][p.x].Piece.Color != this.Color){
                                    this.AvailableMoves.add(p);
                                }
                                else if (p.x != this.ru){
                                    this.AvailableMoves.add(p);
                                }
                            }
                        }

                        if (p.x < this.Pos.x && p.y > this.Pos.y && p.x >= this.ld){
                            
                            if (this.Panel[p.y][p.x].Piece != null && 
                                this.Panel[p.y][p.x].Piece.Color != this.Color &&
                                this.Panel[p.y][p.x].Piece.Type == "King"){
                                this.Check = true;
                            }
                            else {

                                if (this.Panel[p.y][p.x] != null && p.x == this.ld && 
                                    this.Panel[p.y][p.x].Piece.Color != this.Color){
                                    this.AvailableMoves.add(p);
                                }
                                else if (p.x != this.ld){
                                    this.AvailableMoves.add(p);
                                }
                            }
                        }

                        if (p.x < this.Pos.x && p.y < this.Pos.y && p.x >= this.lu){
                            
                            if (this.Panel[p.y][p.x].Piece != null && 
                                this.Panel[p.y][p.x].Piece.Color != this.Color &&
                                this.Panel[p.y][p.x].Piece.Type == "King"){
                                this.Check = true;
                            }
                            else {

                                if (this.Panel[p.y][p.x] != null && p.x == this.lu && 
                                    this.Panel[p.y][p.x].Piece.Color != this.Color){
                                    this.AvailableMoves.add(p);
                                }
                                else if (p.x != this.lu){
                                    this.AvailableMoves.add(p);
                                }
                            }   
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
