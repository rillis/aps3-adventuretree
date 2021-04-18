package com.gui.background;

import com.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Stars {
    private final Random random = new Random();
    public JLabel[] stars;
    public static int CFG_NSTARS = 150;
    public boolean blinking = true;
    public Color base = new Color(0, 229, 229);

    public Stars(Dimension backgroundSize) {
        stars = new JLabel[CFG_NSTARS];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new JLabel();
            stars[i].setBounds(rng(50, backgroundSize.width-50), rng(50, backgroundSize.height-50), 3, 3);
            stars[i].setOpaque(true);
            stars[i].setBackground(new Color(base.getRGB()));
        }
    }

    public void add(MainWindow mainWindow){
        for(JLabel star : stars){
            mainWindow.getContentPane().add(star);
        }
    }
    private int rng(int min, int max){
        return random.nextInt(max + 1 - min) + min;
    }

    public void startBlinking(MainWindow mainWindow) {
        new Thread(){
            public void run(){
                while(true){

                    try {
                        if (blinking) {
                            new Thread() {
                                public void run() {
                                    try {
                                        int n = rng(0, CFG_NSTARS - 1);
                                        for (int i = 255; i > 2; i -= 10) {
                                            stars[n].setBackground(new Color(base.getRed(), base.getGreen(), base.getBlue(), i));
                                            mainWindow.repaint();
                                            Thread.sleep(100);
                                        }
                                        for (int i = 1; i < 254; i += 10) {
                                            stars[n].setBackground(new Color(base.getRed(), base.getGreen(), base.getBlue(), i));
                                            mainWindow.repaint();
                                            Thread.sleep(100);
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }.start();
                        }
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }

    public void setColor(Color color){
        for(JLabel star : stars){
            star.setBackground(color);
        }
    }
}
