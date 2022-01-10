package com.GUI.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//Title component
public class Title extends JPanel{

    //Title's text
    private JLabel Label;

    //Set up all constraints of layout
    public Title(GridBagConstraints LayoutConstaints, String Title, int Size, int Pady, int Gridy){
        
        //Layout constraints
        LayoutConstaints.gridy = Gridy;
        LayoutConstaints.gridx = 0;
        LayoutConstaints.anchor= GridBagConstraints.PAGE_START;

        //Label text set up
        this.Label = new JLabel();
        this.Label.setText(Title);
        this.Label.setFont(new Font("Sans", Font.BOLD, Size));
        this.add(this.Label);
        
        //Padding
        this.setBorder(new EmptyBorder(0,0,Pady,0));
    }
}
