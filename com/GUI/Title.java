package com.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Title extends JPanel{

    public JLabel label;

    public Title(GridBagConstraints gbcnt, String title, int size, int pady, int gridy){
        gbcnt.gridy = gridy;
        gbcnt.gridx = 0;
        gbcnt.anchor= GridBagConstraints.PAGE_START;

        this.label = new JLabel();
        this.label.setText(title);
        this.label.setFont(new Font("Sans", Font.BOLD, size));
        this.add(this.label);

        this.setBorder(new EmptyBorder(0,0,pady,0));
    }
}
