package com.gui;

import com.gui.player.Player;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    Dimension displaySize;
    Player player;
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
    }

    public Dimension getDisplaySize(){
        return displaySize;
    }

    public Player getPlayer(){
        return player;
    }
}
