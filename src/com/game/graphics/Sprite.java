package com.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    public float posX, posY;
    public boolean center;

    public Rectangle rect;

    public Animation[] animations;
    public int currentAnimation = 0;


    public Sprite(float posX, float posY, boolean center){
        this.posX = posX;
        this.posY = posY;
        this.center = center;
    }

    public Sprite(float posX, float posY, boolean center, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.center = center;
        this.rect = new Rectangle((int) posX, (int) posY, width, height);
    }

    public void update (float delta){}

    public void render (Graphics g){
        if(animations == null || currentAnimation >= animations.length){ return; }
        animations[currentAnimation].playAnimation();
        BufferedImage image = animations[currentAnimation].getImage();
        if(image==null){ return; }

        int realX = center ? (int) posX - image.getWidth()/2 : (int) posX;
        int realY = center ? (int) posY - image.getHeight()/2 : (int) posY;

        g.drawImage(image, realX, realY, image.getWidth(), image.getHeight(), null);
    }

}
