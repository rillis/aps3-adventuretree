package com.game.world;

import com.game.graphics.Player;
import com.game.graphics.Renderer;
import com.game.graphics.Sprite;
import com.game.scenes.Scene;

import java.awt.*;
import java.util.ArrayList;

public class Terrain{
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();



    public static Terrain sc1 = new Scene(1, -Player.width+1, Renderer.gameSize.width-1);
    public static Terrain sc2 = new Scene(2, -Player.width+1, Renderer.gameSize.width-1);
    public static Terrain sc3 = new Scene(3, -Player.width+1, Renderer.gameSize.width-24);

    public static Terrain currentTerrain = sc1;

    private static long lastTime = System.nanoTime();

    public static void update(){
        if (currentTerrain==null){return;}
        float deltaTime = (System.nanoTime() - lastTime)/ 1000000000.0f;
        lastTime=System.nanoTime();

        for (Sprite sprite : currentTerrain.sprites){
            sprite.update(deltaTime);
        }
    }

    public static void render (Graphics g){
        if (currentTerrain==null){return;}
        for (Sprite sprite : currentTerrain.sprites){
            sprite.render(g);
        }
    }

}

