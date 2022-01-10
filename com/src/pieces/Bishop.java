package com.src.pieces;

import com.src.utils.*;
import java.util.ArrayList;


public class Bishop extends Piece {
    
    public int ru;
    public int rd;
    public int lu;
    public int ld;

    

    public Bishop(String Color, String path) {
        super(Color,path);
        this.Type = "Bishop";
        this.Points = 3;
    }

    public void BuildPriors(){

        this.PriorMoves = new ArrayList<Point>();
        this.AvailableMoves = new ArrayList<Point>();
    
        this.ru = 8;
        this.rd = 8;
        this.lu=  -1;
        this.ld = -1;



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

             this.PriorMoves.add(new Point(this.Pos.y+i,this.Pos.x+i));
             this.PriorMoves.add(new Point(this.Pos.y-i,this.Pos.x+i));
             this.PriorMoves.add(new Point(this.Pos.y+i,this.Pos.x-i));
             this.PriorMoves.add(new Point(this.Pos.y-i,this.Pos.x-i));
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
        for (Point p : this.PriorMoves) {
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
