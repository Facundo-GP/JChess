package com.GUI.menus;
import javax.swing.*;

import java.awt.*;

import com.GUI.components.PlayerInfo;
import com.GUI.components.ShowBoard;
import com.GUI.components.Title;
import com.src.Match;
import com.GUI.Game;

//Match menu, contains a board, a title and player's names and score
public class MatchMenu extends JPanel{

    //Layouts and Match class which takes two players names
    private GridBagLayout Layout = new GridBagLayout();
    public Match Match;
    public Game Game;

    public MatchMenu(Game Game, String BlackPlayerName, String WhitePlayerName){
        
        this.Game = Game;
        //Adding layout and constraints
        this.setLayout(this.Layout);
        GridBagConstraints LayoutConstraints = new GridBagConstraints();
        
        //Title
        Title Title= new Title(LayoutConstraints,"JChess",46,50,0);
        this.add(Title,LayoutConstraints);

        //Black player info (name and score)
        PlayerInfo BlackPlayer = new PlayerInfo(LayoutConstraints,BlackPlayerName,"black");
        this.add(BlackPlayer,LayoutConstraints);
        
        //White player info (name and score)
        PlayerInfo WhitePlayer = new PlayerInfo(LayoutConstraints,WhitePlayerName,"white");
        this.add(WhitePlayer,LayoutConstraints);

        //Board GUI and match creation
        ShowBoard sb = new ShowBoard(LayoutConstraints);
        this.Match = new Match(BlackPlayer.Player,WhitePlayer.Player);
        this.Match.Menu = this;
        this.add(this.Match.Start(),LayoutConstraints);

    }
    
}
