package com.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Button extends JPanel {
    
    public JButton button;

    public Button(GridBagConstraints gbcnt,String text, int size, int pady, int gridy){
        
        gbcnt.gridy = gridy;
        gbcnt.gridx = 0;
        
        this.setBorder(new EmptyBorder(pady , 0,pady,0));
        
        this.button = new JButton();
        this.button.setText(text);
        this.button.setFont(new Font("Sans", Font.BOLD, size));
        this.button.setPreferredSize(new Dimension(200,60));
        this.button.setBackground(Color.LIGHT_GRAY);
        this.button.setForeground(Color.BLACK);
        this.button.setBorder(BorderFactory.createLineBorder(Color.BLACK,10));
        this.button.setFocusPainted(false);

        this.add(this.button);
    }
}
