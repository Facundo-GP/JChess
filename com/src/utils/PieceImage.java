package com.src.utils;
import java.awt.*;
import javax.swing.*;

//Class that handles pieces images drawing and erase
public class PieceImage extends JChessPanel{
    
    //Patch to pieces images
    private String Path;

    //Label used to put image in
    private JLabel Label;


    //Set up path
    public PieceImage(String path) {
        this.Path = path;
    }

    //Draws image in board
    public JChessPanel[][] DrawIn(JChessPanel[][] panel, int y, int x){
 
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(this.Path));
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        imageIcon = new ImageIcon(newimg);
        this.Label = new JLabel(imageIcon);
        panel[y][x].add(this.Label);

        return panel;
    }

    //Erase image from board
    public JChessPanel[][] EraseFrom(JChessPanel[][] panel, int y, int x){
        this.Label.setIcon(null);
        this.remove(this.Label);
        panel[y][x].removeAll();
        panel[y][x].revalidate();
        panel[y][x].repaint();
        return panel;
    }
    
}
