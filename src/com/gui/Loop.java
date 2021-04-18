package com.gui;

public class Loop {
    int FPS;
    MainWindow mainWindow;

    public Loop(int FPS, MainWindow mainWindow){
        this.FPS = FPS;
        this.mainWindow = mainWindow;
    }

    public void init(){
        new Thread(){
            public void run(){
                while(true){
                    mainWindow.revalidate();
                    mainWindow.repaint();



                    try{Thread.sleep(1000/FPS);}catch (Exception e){e.printStackTrace();}
                }
            }
        }.start();
    }
}
