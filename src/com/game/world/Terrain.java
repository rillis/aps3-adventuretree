package com.game.world;

import com.game.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class Terrain{
    public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    private static long lastTime = 0;

    public static void update(){
        float deltaTime = (System.nanoTime() - lastTime)/ 1000000000.0f;

        for (Sprite sprite : sprites){
            sprite.update(deltaTime);
        }
    }

    public static void render (Graphics g){
        for (Sprite sprite : sprites){
            sprite.render(g);
        }
    }

}
