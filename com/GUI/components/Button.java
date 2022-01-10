package com.GUI.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


//Start button component
public class Button extends JPanel {
    
    //Button
    public JButton Button;

    //Set up all layout constraints to display the button
    public Button(GridBagConstraints LayoutConstraints,String Text, int Size, int Pady, int Gridy){
        
        //Layout constraints
        LayoutConstraints.gridy = Gridy;
        LayoutConstraints.gridx = 0;
        
        //Button set up
        this.Button = new JButton();
        this.Button.setText(Text);
        this.Button.setFont(new Font("Sans", Font.BOLD, Size));
        this.Button.setPreferredSize(new Dimension(200,60));
        this.Button.setBackground(Color.LIGHT_GRAY);
        this.Button.setForeground(Color.BLACK);
        this.Button.setBorder(BorderFactory.createLineBorder(Color.BLACK,10));
        this.Button.setFocusPainted(false);
        this.add(this.Button);

        //Padding
        this.setBorder(new EmptyBorder(Pady , 0,Pady,0));

    }
}
