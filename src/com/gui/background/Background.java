package com.gui.background;

import com.gui.MainWindow;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Background extends JLabel {
    public static int NIGHT = 1;
    public static int NIGHT_TO_DAY = 2;
    public static int DAY = 3;
    public static int DAY_TO_NIGHT = 4;

    Random random = new Random();

    public int time = NIGHT;

    public int daytime = 10000;

    MainWindow mainWindow;
    Dimension backgroundSize;


    public Background(MainWindow mainWindow, Dimension backgroundSize){
        this.mainWindow = mainWindow;
        this.backgroundSize = backgroundSize;

        setBounds(0, 0, backgroundSize.width, backgroundSize.height+mainWindow.getPlayer().getHeight()/2);
        setVisible(true);
        setOpaque(true);

        //setBackground(new Color(0, 229, 229));
        setBackground(new Color(0, 0, 0));

        startBackgroundChange();
    }

    private void startBackgroundChange() {
        new Thread(){
            public void run(){
                while (true){
                    try{
                        Thread.sleep(daytime);
                        if(time==NIGHT){
                            mainWindow.getStars().blinking=false;
                            for(int i = 0; i < 229; i++) {
                                setBackground(new Color(0, i, i));
                                mainWindow.repaint();
                                Thread.sleep(100);
                            }
                            time = DAY;
                        }else{

                            for(int i = 229; i > 0; i--) {
                                setBackground(new Color(0, i, i));
                                mainWindow.repaint();
                                Thread.sleep(100);
                            }
                            mainWindow.getStars().blinking=true;
                            time = NIGHT;

                        }
                    }catch (Exception e){e.printStackTrace();}
                }
            }
        }.start();
    }

    private int rng(int min, int max){
        return random.nextInt(max + 1 - min) + min;
    }

    public Dimension getBackgroundSize(){
        return backgroundSize;
    }

    public void cycle(){
        System.out.println(time);

        time += 1;
        if (time == 5) {
            time = 1;
        }

        System.out.println(">" + time);

    }
}
