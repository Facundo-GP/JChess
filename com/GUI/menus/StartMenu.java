package com.GUI.menus;

import java.awt.*;
import javax.swing.*;
import com.GUI.components.Button;
import com.GUI.components.Title;

//First menu in game, contains the title,instructions and start button
public class StartMenu extends JPanel{
    
    //Start button and Layout
    private GridBagLayout Layout = new GridBagLayout();
    public Button Start;

    public StartMenu(){
        
        //Adding layout and constraints
        this.setLayout(this.Layout);
        GridBagConstraints LayoutConstraints = new GridBagConstraints();

        //Title
        Title title = new Title(LayoutConstraints,"JChess",64,0,0);
        this.add(title,LayoutConstraints);

        //Instructions text
        Title Instructions = new Title(LayoutConstraints,"Instructions: r-reset match,  q-return to Start menu,  ESC-exit game",16,0,1);
        this.add(Instructions,LayoutConstraints);

        //Start button
        this.Start = new Button(LayoutConstraints,"   Start   ",20,40,2);
        this.add(this.Start,LayoutConstraints);

    }
    
}
