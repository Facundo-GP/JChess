package com.GUI.components;
import java.awt.*;
import javax.swing.*;
import com.src.Player;


//Player's info component (name and score)
public class PlayerInfo extends JLabel{
    
    //Player object and his name
    public Player Player;
    public String Name;
    
    //Set up all layout constraints, creates a new player and display his info
    public PlayerInfo(GridBagConstraints LayoutConstraints,String Name, String Color){
        
        //Player creation
        this.Player = new Player(Color);
        this.Player.Info = this;
        
        //Select position in function of color (black above)
        if (Color == "black"){
            LayoutConstraints.gridy = 1;
            LayoutConstraints.ipady = 20;
        }
        else {
            LayoutConstraints.gridy = 3;
            LayoutConstraints.ipady = 40;
        }

        //Player info set up
        this.Name = Name;
        this.setText(this.Name + "                   Score: "+this.Player.Score);
        this.setFont(new Font("Sans", Font.BOLD, 24));

    }

    
    //Updates displayed player info
    public void Update(){
        
        //Adds "is in check!" if the player is in check
        if (this.Player.InCheck){
            this.setText(this.Name + "   is in check!    Score: "+this.Player.Score);
        }
        //Updates score
        else {
            this.setText(this.Name + "                   Score: "+this.Player.Score);
        }
       
    }

}
