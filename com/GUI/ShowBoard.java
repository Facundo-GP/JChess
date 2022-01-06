package com.GUI;

import java.awt.*;
import com.src.Board;

public class ShowBoard extends Board{
    
    public ShowBoard(GridBagConstraints gbcnt){
        gbcnt.gridy = 2;
        gbcnt.ipady = 0;
        gbcnt.ipady = 10;
    }
}
