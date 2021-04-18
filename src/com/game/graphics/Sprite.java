package com.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    public float posX = 0;
    public float posY = 0;
    public boolean center = true;

    public BufferedImage image = null;

    public Sprite(float posX, float posY, boolean center){
        this.posX = posX;
        this.posY = posY;
        this.center = center;
    }

    public void update (float delta){

    }

    public void render (Graphics g){
        if(image==null){
            return;
        }

        int realX = (int) posX;
        int realY = (int) posY;
        if (center){
            realX= (int) posX - image.getWidth()/2;
            realY = (int) posY - image.getHeight()/2;
        }


        g.drawImage(image, realX, realY, image.getWidth(), image.getHeight(), null);
    }

}
