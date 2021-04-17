package com.gui.player;


import com.gui.MainWindow;
import com.gui.util.AWTools;

import javax.swing.*;
import java.awt.*;

public class Player extends JLabel {
    Dimension playerSize = new Dimension(50, 100);
    public Player(MainWindow mainWindow){
        Dimension center = AWTools.center(mainWindow.getDisplaySize(), playerSize);
        setBounds(center.width, center.height, playerSize.width, playerSize.height);
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
    }
}
