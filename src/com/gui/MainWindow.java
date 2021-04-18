package com.gui;

import com.gui.background.Background;
import com.gui.background.Stars;
import com.gui.player.Player;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    Dimension displaySize;
    Player player;
    Background background;
    Stars stars;
    JPanel contentPane;
    public MainWindow(Dimension displaySize) {
        this.displaySize = displaySize;
        setSize(1000,1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBounds(0,0, displaySize.width, displaySize.height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        player = new Player(this);
        contentPane.add(player);


        background = new Background(this, new Dimension(2000, 500));

        stars = new Stars(background.getBackgroundSize());
        stars.add(this);
        stars.startBlinking(this);

        contentPane.add(background);

    }

    public Dimension getDisplaySize(){
        return displaySize;
    }

    public Player getPlayer(){
        return player;
    }

    public JPanel getContentPane(){
        return contentPane;
    }

    public Stars getStars(){
        return stars;
    }

    public Background getBackgroundLabel(){
        return background;
    }
}
