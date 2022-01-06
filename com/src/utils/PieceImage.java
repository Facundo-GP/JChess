package com.src.utils;
import java.awt.*;
import javax.swing.*;
import com.src.*;

;
public class PieceImage extends JChessPanel{
    
    String path;
    public JLabel label;



    public PieceImage(String path) {
        this.path = path;
    }

    public JChessPanel[][] draw_in(JChessPanel[][] panel, int y, int x){
 
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(this.path));
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        imageIcon = new ImageIcon(newimg);
        this.label = new JLabel(imageIcon);
        panel[y][x].add(this.label);

        return panel;
    }

    public JChessPanel[][] erase_from(JChessPanel[][] panel, int y, int x){
        this.label.setIcon(null);
        this.remove(this.label);
        panel[y][x].removeAll();
        panel[y][x].revalidate();
        panel[y][x].repaint();
        return panel;
    }
    
}
