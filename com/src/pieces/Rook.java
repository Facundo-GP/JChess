package com.src.pieces;


import com.src.utils.*;
import java.util.ArrayList;


public class Rook extends Piece {
    
    public int Mx;
    public int mx;
    public int My;
    public int my;


    public Rook(String Color, String path) {
        super(Color,path);
        this.Type = "Rook";
        this.Points = 5;
    }

    public void BuildPriors(){
        
        this.PriorMoves = new ArrayList<Point>();
        this.AvailableMoves = new ArrayList<Point>();

        this.Mx = 8;
        this.mx = -1;
        this.My = 8;
        this.my = -1;
        
        Boolean Mx_finded = false;
        Boolean My_finded = false;

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
                this.PriorMoves.add(new Point(this.Pos.y,i));  
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
                this.PriorMoves.add(new Point(i,this.Pos.x));  
            }
        }
        
    }
    public void BuildAvailables(){
        this.Check = false;
        for (Point p : this.PriorMoves) {
            if (p.x <= 7 && p.x >= 0 && p.y <= 7 && p.y >= 0){

                //Moves only if there is not a Piece of the same Color and type is not King
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
    }

    public void ShowMoves(){
        
        this.BuildPriors();
        this.BuildAvailables();
        this.ShowAvailables();

    }

}
