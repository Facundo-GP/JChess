package com.GUI;
import java.awt.*;
import javax.swing.*;
import com.src.Player;

public class PlayerInfo extends JLabel{
    
    public Player player;
    
    PlayerInfo(GridBagConstraints gbcnt,String name, String color){
        this.player = new Player(name,color);
        
        if (color == "black"){
            gbcnt.gridy = 1;
            gbcnt.ipady = 20;
        }
        else {
            gbcnt.gridy = 3;
            gbcnt.ipady = 40;
        }

        this.setText(name + "                   Score: "+this.player.score);
        this.setFont(new Font("Sans", Font.BOLD, 24));

    }

}
