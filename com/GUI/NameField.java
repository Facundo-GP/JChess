package com.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NameField extends JPanel{

    public JTextField textfield;

    public NameField(GridBagConstraints gbcnt,String text, int size, int pady, int gridy){

        gbcnt.gridy = gridy;
        gbcnt.gridx = 0;

        this.setLayout(new FlowLayout());
        this.setBorder(new EmptyBorder(0,0,pady ,0));
        
        JLabel playertext = new JLabel(text);
        playertext.setFont(new Font("Sans", Font.BOLD, size));
        playertext.setBorder(new EmptyBorder(0,0,0,3*pady/4));

        this.textfield = new JTextField(15);
        this.textfield.setEditable(true);

        this.add(playertext);
        this.add(this.textfield);


    }
}
