package com.GUI.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


//player name and text field pair
public class NameField extends JPanel{

    //Text field that will take player's names and player text left to it
    public JTextField TextField;
    private JLabel PlayerText;

    //set up all layout constraints to display NameField
    public NameField(GridBagConstraints LayoutConstraints,String Text, int Size, int Padding, int Gridy){

        //Parent layout constraints
        LayoutConstraints.gridy = Gridy;
        LayoutConstraints.gridx = 0;

        //NameField component intern layout
        this.setLayout(new FlowLayout());
        
        
        //Text left to TextField set up
        this.PlayerText = new JLabel(Text);
        this.PlayerText.setFont(new Font("Sans", Font.BOLD, Size));
        this.PlayerText.setBorder(new EmptyBorder(0,0,0,3*Padding/4));
        this.add(this.PlayerText);

        //TextFiedl set up
        this.TextField = new JTextField(15);
        this.TextField.setEditable(true);
        this.add(this.TextField);

        //Padding
        this.setBorder(new EmptyBorder(0,0,Padding ,0));

    }
}
