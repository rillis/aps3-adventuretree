package com.game.world;

import com.game.config.Config;
import com.game.graphics.Player;
import com.game.graphics.Renderer;
import com.game.graphics.Sprite;
import com.game.scenes.Scene;

import java.awt.*;
import java.util.ArrayList;

public class Terrain{
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();



    public static Scene sc1 = new Scene(1, 1, Renderer.gameSize.width-1,
            new SlotTree[]{new SlotTree(16,62,22,11),new SlotTree(44,62,22,10)
                    ,new SlotTree(72,62,22,10)});

    public static Scene sc2 = new Scene(2, -Player.width+1, Renderer.gameSize.width-1,
            new SlotTree[]{new SlotTree(10,62,22,11),new SlotTree(38,62,22,10)
                    ,new SlotTree(66,62,22,10)});

    public static Scene sc3 = new Scene(3, -Player.width+1, Renderer.gameSize.width-24,
            new SlotTree[]{new SlotTree(3,62,22,11),new SlotTree(31,62,22,10)
                    ,new SlotTree(59,62,22,10)});

    public static Scene currentTerrain = sc1;

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

        if (Config.DEBUG){
            for (SlotTree slotTree : currentTerrain.treeSlots){
                g.setColor(Color.red);
                g.drawRect(slotTree.x, slotTree.y, slotTree.width, slotTree.height);
            }
        }

    }

}

