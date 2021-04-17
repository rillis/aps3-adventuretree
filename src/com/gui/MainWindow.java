package com.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    Dimension displaySize;
    JPanel contentPane;
    public MainWindow(Dimension displaySize) {
        this.displaySize = displaySize;
        setSize(1000,1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBounds(0,0, displaySize.width, displaySize.height);
        setContentPane(contentPane);
    }
}
