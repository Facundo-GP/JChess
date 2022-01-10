package com.src.utils;

import java.awt.event.*;
import javax.swing.*;

import com.src.pieces.Piece;
import com.src.pieces.Queen;
import com.src.Player;
import com.src.Board;
import com.GUI.utils.IconPaths;
import java.util.ArrayList;

import java.awt.Color;

//Implements all interactions beetwen players and pieces
public class PieceListener implements MouseListener {

    //Listener states and panels
    private Boolean ChoosingMove = false;
    private Boolean PlayerCheck = false;
    private Boolean OpponentCheck = false;
    private JChessPanel ClickedPanel;
    private JChessPanel EnteredPanel;
    
    //Atributes used to reverse moves
    private Piece LastPieceMoved;
    private Point LastPiecePosition = new Point(0,0);
    private Piece Piece;
    public Board Board;

    private IconPaths IconPaths = new IconPaths();

    private void evalCheckmate(){
    }

    private Boolean RightCastlingAllowed(Piece EnteredPiece){
        
        Boolean Allowed = true;
  
        int Xpos = EnteredPiece.Pos.x;
        int Ypos = EnteredPiece.Pos.y;

        EnteredPiece.Move(Ypos,Xpos+1);
        this.evalPlayerCheck(EnteredPiece);
        Allowed &= !this.PlayerCheck;
        EnteredPiece.ReverseLastMove();

        EnteredPiece.Move(Ypos,Xpos+2);
        this.evalPlayerCheck(EnteredPiece);
        Allowed &= !this.PlayerCheck;
        EnteredPiece.ReverseLastMove();

        return Allowed;

    }

    private Boolean LeftCastlingAllowed(Piece EnteredPiece){
        
        Boolean Allowed = true;
        
        int Xpos = EnteredPiece.Pos.x;
        int Ypos = EnteredPiece.Pos.y;

        EnteredPiece.Move(Ypos,Xpos-1);
        this.evalPlayerCheck(EnteredPiece);
        Allowed &= !this.PlayerCheck;
        EnteredPiece.ReverseLastMove();

        EnteredPiece.Move(Ypos,Xpos-2);
        this.evalPlayerCheck(EnteredPiece);
        Allowed &= !this.PlayerCheck;
        EnteredPiece.ReverseLastMove();

        return Allowed;
    }


    //Promotes a pawn (Queen only)
    private void Promotion(){
        
        this.Piece.EraseFrom(this.Piece.Panel, this.Piece.Pos.y, this.Piece.Pos.x);
        
        JChessPanel[][] PromotionPanel = this.Piece.Panel;
        Point PromotionPos = this.Piece.Pos;
        String PromotionColor = this.Piece.Color;
        String PromotionIconPath;
        Player PromotionPlayer = this.Piece.Player;

        if (PromotionColor == "black"){
            PromotionIconPath = this.IconPaths.Paths[4];
        }
        else {
            PromotionIconPath = this.IconPaths.Paths[10];
        }

        Piece PromotedPiece = new Queen(PromotionColor,PromotionIconPath);
        PromotedPiece.Pos = PromotionPos;
        PromotedPiece.Panel = PromotionPanel;
        PromotedPiece.Player = PromotionPlayer;
        int cont = 0;
        for (Piece p : PromotedPiece.Player.Pieces){
            if (p != this.Piece){
                cont++;
            }
        }
        PromotedPiece.Player.Pieces.set(cont,PromotedPiece);
        this.Piece = PromotedPiece;
        this.Piece.DrawIn(this.Piece.Panel, this.Piece.Pos.y, this.Piece.Pos.x);
        
    
    }

    //Checks if the opponent is in check
    private void evalOpponentCheck(){
        int flag = 0;
        Player Player = this.Piece.Player;

        for (Piece p : Player.Pieces){
            if (p.State != "killed"){
                p.BuildPriors();
                p.BuildAvailables();
                if (p.Check == true){
                    this.OpponentCheck = true;
                    flag = 1;
                }
            }
        }
        if (flag == 0 && this.OpponentCheck == true){
            this.OpponentCheck = false;
        }

        if (this.OpponentCheck){
            Player.Opponent.InCheck = true;
        }
    }


    //Checks if a move put the player in check
    private void evalPlayerCheck(Piece Piece){
        int flag = 0;
        Player Player = Piece.Player.Opponent;
    
        for (Piece p : Player.Pieces){
            if (p.State != "killed"){
                p.BuildPriors();
                p.BuildAvailables();
                if (p.Check == true){
                    flag = 1;
                    this.PlayerCheck = true;
                }
            }
        }

        if (flag == 0 && this.PlayerCheck == true){
            this.PlayerCheck = false;
        }

        else {
            Player.Opponent.InCheck = false;
        }
    }

    //Changes state to Playing to Waiting and to Waiting to Playing before doing a move
    private void UpdatePlayerState(){
        if (this.ClickedPanel.Player.State == "Playing"){
            this.ClickedPanel.Player.State = "Waiting";
            this.ClickedPanel.Player.Opponent.State = "Playing"; 
        }
        else {
            this.ClickedPanel.Player.State = "Playing";
            this.ClickedPanel.Player.Opponent.State = "Waiting";
        }
    }


    //Clean HighLighted boxes
    public void CleanPaths(){
        
        if (this.Piece != null){
            for (Point p : this.Piece.AvailableMoves) {

                if ((p.x+p.y) % 2 == 0){
                    this.ClickedPanel.Piece.Panel[p.y][p.x].setBackground(Color.GRAY); 
                }
                else {
                    this.ClickedPanel.Piece.Panel[p.y][p.x].setBackground(Color.LIGHT_GRAY);
                }
            this.ClickedPanel.Piece.Panel[p.y][p.x].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            }
        }

        else {
            for (Point p : this.ClickedPanel.Piece.AvailableMoves) {
                if ((p.x+p.y) % 2 == 0){
                    this.ClickedPanel.Piece.Panel[p.y][p.x].setBackground(Color.GRAY); 
                }
                else {
                    this.ClickedPanel.Piece.Panel[p.y][p.x].setBackground(Color.LIGHT_GRAY);
                }
                this.ClickedPanel.Piece.Panel[p.y][p.x].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            }
        }
        

    }



    @Override
    public void mouseClicked(MouseEvent event) {
       
        Object source = event.getSource();
    
        if(source instanceof JChessPanel){
            this.ClickedPanel = (JChessPanel) source;
            
            if (this.ChoosingMove){

                if (this.ClickedPanel.Piece != null && this.ClickedPanel.Player.State == "Playing"){
                    this.CleanPaths();
                    this.Piece = this.ClickedPanel.Piece;
                    this.ClickedPanel.Piece.ShowMoves();   
                }
                else{   
                    Boolean MovmentAllowed = true;
                    for (Point p : this.Piece.AvailableMoves) {
                        if (p.x == this.ClickedPanel.Gridx && p.y == this.ClickedPanel.Gridy){
                        
                            
                            //Move to test if it is allowed
                            
                            this.Piece.Move(p.y,p.x);
                            this.CleanPaths();

                            this.evalPlayerCheck(this.Piece);
                            if (this.PlayerCheck){
                                MovmentAllowed = false;
                            } 
                            
                            this.Piece.ReverseLastMove();

                             
                            
                            

                            //If allowed moves again testing all special cases
                            if (MovmentAllowed){    
                                
                                //Special case of pawn first movment
                                if ((p.y == 5 && this.Board.Boxes[p.y-1][p.x].Piece != null) ||
                                    (p.y == 2 && this.Board.Boxes[p.y+1][p.x].Piece != null)){     
                                    if (this.LastPieceMoved == this.Board.Boxes[p.y+1][p.x].Piece ||
                                        this.LastPieceMoved == this.Board.Boxes[p.y-1][p.x].Piece){      
                                        if (this.LastPieceMoved.Type == "Pawn" &&
                                            this.Board.Boxes[p.y][p.x].Piece == null &&
                                            (this.LastPiecePosition.y == 6 || this.LastPiecePosition.y == 1)){
                                                
                                                this.LastPieceMoved.Move(p.y,p.x);
                                                
                                        }
                                    }
                                }

                                //Special case of castling
                                if (this.Piece.Type == "King" && this.Piece.SpecialRule){
                                    if (this.Piece.Color == "black"){
                                        if (p.x == 6 && p.y == 0){
                                             this.Piece.Panel[0][7].Piece.Move(0,5);
                                        }
                                        else if (p.x == 2 && p.y == 0){
                                            this.Piece.Panel[0][0].Piece.Move(0,3);
                                        }
                                    }
                                    else {
                                        if (p.x == 6 && p.y == 7){
                                            this.Piece.Panel[7][7].Piece.Move(7,5);
                                        }
                                        else if (p.x == 2 && p.y == 7){
                                            this.Piece.Panel[7][0].Piece.Move(7,3);
                                        }

                                    }
                                
                                }

                                //Special case of promotion (Queen selected by default)
                                if ((p.y == 7 || p.y == 0) && this.Piece != null && this.Piece.Type == "Pawn"){
                                    this.Promotion();
                                }

                               
                                this.LastPieceMoved = this.Piece;
                                this.LastPiecePosition.x = this.Piece.Pos.x;
                                this.LastPiecePosition.y = this.Piece.Pos.y;

                                this.Piece.Move(p.y,p.x);
                                this.UpdatePlayerState();
                                this.CleanPaths();

                                this.evalOpponentCheck();
                                    if (this.OpponentCheck){
                                        this.evalCheckmate();
                                }
                                this.ChoosingMove = false;
                                this.Piece.Player.Info.Update();
                                this.Piece.Player.Opponent.Info.Update();
                            }
                            this.Piece = null;
                            break;

                        }
                    }
                }
            }
            else if (this.ClickedPanel.Piece != null && this.ClickedPanel.Player.State == "Playing") 
            {
                this.ClickedPanel.Piece.ShowMoves();
                this.ChoosingMove = true;
                this.Piece = this.ClickedPanel.Piece;
                this.Piece.Player.Info.Update();
                this.Piece.Player.Opponent.Info.Update();
            }
            
                  
        }
    }




    @Override
    public void mouseEntered(MouseEvent arg0) {
        
        Object source = arg0.getSource();
        if (!this.ChoosingMove){
            if(source instanceof JChessPanel){
                
                this.EnteredPanel = (JChessPanel) source;
                Piece EnteredPiece = this.EnteredPanel.Piece;
                if (EnteredPiece!= null && this.EnteredPanel.Player.State == "Playing") 
                {   
                    //Special case of castling
                    if (EnteredPiece.Type == "King" && EnteredPiece.Pos.x == 4 && !EnteredPiece.Player.InCheck &&
                      ((EnteredPiece.Pos.y == 0 && EnteredPiece.Color == "black") ||
                      (EnteredPiece.Pos.y == 7 && EnteredPiece.Color == "white"))){
                        
                    
                        int Xpos = EnteredPiece.Pos.x;
                        int Ypos = EnteredPiece.Pos.y;    
                        
                        if (EnteredPiece.NumberOfMoves == 0){
                            
                            //Castling to king's side
                            if (EnteredPiece.Panel[Ypos][Xpos-1].Piece == null && EnteredPiece.Panel[Ypos][Xpos-2].Piece == null &&
                                EnteredPiece.Panel[Ypos][Xpos-3].Piece == null &&  EnteredPiece.Panel[Ypos][Xpos-4].Piece != null &&
                                EnteredPiece.Panel[Ypos][Xpos-4].Piece.Type == "Rook" && EnteredPiece.Panel[Ypos][Xpos-4].Piece.NumberOfMoves == 0){
                                    
                                    if (this.LeftCastlingAllowed(EnteredPiece) && !EnteredPiece.Player.InCheck){
                                        EnteredPiece.SpecialRule = true;
                                        EnteredPiece.SpecialRulePos.add(new Point(Ypos,Xpos-2)); 
                                    }
                            }   
                

                            //Castling to queen's side
                            if (EnteredPiece.Panel[Ypos][Xpos+1].Piece == null && EnteredPiece.Panel[Ypos][Xpos+2].Piece == null &&
                                EnteredPiece.Panel[Ypos][Xpos+3].Piece != null && EnteredPiece.Panel[Ypos][Xpos+3].Piece.Type == "Rook" &&
                                EnteredPiece.Panel[Ypos][Xpos+3].Piece.NumberOfMoves == 0){
                                    
                                    if (this.RightCastlingAllowed(EnteredPiece) && !EnteredPiece.Player.InCheck){
                                        EnteredPiece.SpecialRule = true;
                                        EnteredPiece.SpecialRulePos.add(new Point(Ypos,Xpos+2));
                                    }
                            }   

                        }
                    } 

                    //Special case of pawn first movment
                    
                    if (EnteredPiece.Type == "Pawn"){
                        int Xpos = EnteredPiece.Pos.x;
                        int Ypos = EnteredPiece.Pos.y;
                        
                        if (Ypos == 3 || Ypos == 4){
                            if (Xpos + 1 < 8){
                                if (this.Board.Boxes[Ypos][Xpos+1].Piece != null && 
                                this.Board.Boxes[Ypos][Xpos+1].Piece.Type == "Pawn"){
                                    if (this.Board.Boxes[Ypos][Xpos+1].Piece == this.LastPieceMoved &&
                                        (this.LastPiecePosition.y == 1 || this.LastPiecePosition.y == 6)){
                                        
                                        EnteredPiece.SpecialRule = true;
                                        if (this.LastPiecePosition.y == 1 ){
                                            EnteredPiece.SpecialRulePos.add(new Point(Ypos-1,Xpos+1));
                                        }
                                        if (this.LastPiecePosition.y == 6){
                                            EnteredPiece.SpecialRulePos.add(new Point(Ypos+1,Xpos+1));
                                        }
                        
                                    }
                                    else {
                                        EnteredPiece.SpecialRule = false;
                                    }
                                }
                            }
                        
                    
                    
                            if (Xpos - 1 > 0){
                                if (this.Board.Boxes[Ypos][Xpos-1].Piece != null && 
                                this.Board.Boxes[Ypos][Xpos-1].Piece.Type == "Pawn"){
                                    if (this.Board.Boxes[Ypos][Xpos-1].Piece == this.LastPieceMoved &&
                                    (this.LastPiecePosition.y == 1 || this.LastPiecePosition.y == 6)){
                                        
                                        EnteredPiece.SpecialRule = true;
                                        if (this.LastPiecePosition.y == 1 ){
                                            EnteredPiece.SpecialRulePos.add(new Point(Ypos-1,Xpos-1));
                                        }
                                        if (this.LastPiecePosition.y == 6){
                                            EnteredPiece.SpecialRulePos.add(new Point(Ypos+1,Xpos-1));
                                        }
                                    }
                                    else {
                                        EnteredPiece.SpecialRule = false;
                                    }
                                }
                            }
                        }
                    }

            
                    this.EnteredPanel.Piece.ShowMoves();
                }
                
            }
        }
    }




    @Override
    public void mouseExited(MouseEvent arg0) {

        Object source = arg0.getSource();
    
        if(source instanceof JChessPanel){
            this.ClickedPanel = (JChessPanel) source;
            if (this.ClickedPanel.Piece != null && (this.ClickedPanel.Piece.Type == "Pawn" || this.ClickedPanel.Piece.Type == "King")){
                this.ClickedPanel.Piece.SpecialRulePos = new ArrayList<Point>();
            }
            if (this.ClickedPanel.Piece != null && !this.ChoosingMove && this.ClickedPanel.Player.State == "Playing") 
            {
                this.CleanPaths();
            }
            
        }
    }



    
    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

}

