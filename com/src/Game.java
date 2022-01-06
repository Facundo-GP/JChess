package com.src;
 

import com.src.*;
import com.src.pieces.*;

public class Game{

    public Player b_player;
    public Player w_player;
    public Board board;
    public String state;
    
    public Game(Player p1, Player p2){
        this.b_player = p1;
        this.w_player = p2;
        this.state = "init";
    }

    public Board start(){
        this.board = new Board(this.b_player,this.w_player);
        return this.board;
    }

    public void reset(){
        
        //Erease
        for (Piece p : b_player.pieces){
            if (p.state == "alive"){
                p.erase_from(this.board.table,p.pos.y,p.pos.x);
            }
        }
        for (Piece p : w_player.pieces){
            if (p.state == "alive"){
                p.erase_from(this.board.table,p.pos.y,p.pos.x);
            }
        }

        //Draw
        int cont = 0;
        for (Piece p : b_player.pieces){
            p.draw_in(this.board.table,cont / 8,cont % 8);
            p.state = "alive";
            cont++;
        }

        cont = 0;
        for (Piece p : w_player.pieces){
            p.draw_in(this.board.table,7 - (cont / 8),cont % 8);
            p.state = "alive";
            cont++;
        }

        this.b_player.points = 0;
        this.w_player.points = 0;

    } 

}
