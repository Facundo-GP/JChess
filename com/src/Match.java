package com.src;
 

import com.src.utils.PieceListener;
import com.GUI.menus.MatchMenu;

//Match class, handles interaction beetween players in a game
public class Match{

    //Menu associated with the match
    public MatchMenu Menu;

    //Players playing the match
    public Player BlackPlayer;
    public Player WhitePlayer;

    //Board
    public Board Board;
    
    //Match state
    public String State;

    //Listener for pieces movments
    private PieceListener PieceListener;
    
    //Initial set up
    public Match(Player PlayerOne, Player PlayerTwo){
        this.BlackPlayer = PlayerOne;
        this.WhitePlayer = PlayerTwo;
        this.BlackPlayer.Opponent = this.WhitePlayer;
        this.BlackPlayer.OpponentKing = this.WhitePlayer.Pieces.get(4);
        this.WhitePlayer.Opponent = this.BlackPlayer;
        this.WhitePlayer.OpponentKing = this.BlackPlayer.Pieces.get(4);
        this.State = "init";
        
    }

    //Start match, creates a board
    public Board Start(){
        this.Board = new Board(this.BlackPlayer,this.WhitePlayer);
        this.BlackPlayer.Game = this;
        this.WhitePlayer.Game = this;
        this.PieceListener = this.Board.PieceListener;
        return this.Board;
    }

    //Resets a match to the initial state
    public void Reset(){
        this.Menu.Game.RenderMatchPanel();
    } 

}
