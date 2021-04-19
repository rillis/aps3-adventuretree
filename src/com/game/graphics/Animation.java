package com.game.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    public ArrayList<BufferedImage> images = new ArrayList<>();
    public int currentImage = 0;
    public int fps = 10; // FPS PADRÃO
    private long lastTime = System.nanoTime();

    public void playAnimation(){
        if(System.nanoTime() > lastTime + (1000000000 / fps)){
            currentImage++;
            if (currentImage >= images.size()){ currentImage = 0; }
            lastTime = System.nanoTime();
        }
    }

    public BufferedImage getImage(){
        if(images.size()>currentImage){ return images.get(currentImage); }
        return null;
    }

}
