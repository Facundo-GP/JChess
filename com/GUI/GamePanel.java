package com.GUI;
import javax.swing.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.src.Game;

public class GamePanel extends JPanel{

    public GridBagLayout gbl = new GridBagLayout();
    public Game game;

    public GamePanel(String b_player_name, String w_player_name){
        
        
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        
        GridBagConstraints gbcnt = new GridBagConstraints();
        
        //Componets
        Title title = new Title(gbcnt,"JChess",46,50,0);
        this.add(title,gbcnt);

        PlayerInfo b_player = new PlayerInfo(gbcnt,b_player_name,"black");
        this.add(b_player,gbcnt);
        
        PlayerInfo w_player = new PlayerInfo(gbcnt,w_player_name,"white");
        this.add(w_player,gbcnt);

        ShowBoard sb = new ShowBoard(gbcnt);
        this.game = new Game(b_player.player,w_player.player);
        this.add(this.game.start(),gbcnt);

    }
    
}
