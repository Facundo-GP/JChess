package com.GUI;

import java.awt.*;
import javax.swing.*;


public class Title extends JLabel{

    public Title(GridBagConstraints gbcnt, String title){
        gbcnt.gridy = 0;
        gbcnt.gridx = 0;
        gbcnt.ipady = 50;
        this.setText(title);
        this.setFont(new Font("Sans", Font.BOLD, 46));
    }
}
