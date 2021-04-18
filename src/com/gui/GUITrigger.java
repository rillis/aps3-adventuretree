package com.gui;

import java.awt.*;

public class GUITrigger {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(new Dimension(1000,1000));
        mainWindow.setVisible(true);

        Loop loop = new Loop(60, mainWindow);
        loop.init();
    }
}
