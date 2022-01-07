package com.GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.src.Game;


public class StartMenu extends JPanel{
    
    public Button start;

    public GridBagLayout gbl = new GridBagLayout();

    public StartMenu(){
        
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        GridBagConstraints gbcnt = new GridBagConstraints();

        
        Title title = new Title(gbcnt,"JChess",64,0,0);
        this.add(title,gbcnt);

        Title Instructions = new Title(gbcnt,"Instructions: r-reset match,  q-return to start menu,  ESC-exit game",16,0,1);
        this.add(Instructions,gbcnt);

        this.start = new Button(gbcnt,"   Start   ",20,40,2);
        this.add(this.start,gbcnt);

    }
    
}
