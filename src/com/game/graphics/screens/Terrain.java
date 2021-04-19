package com.game.graphics.screens;

import com.game.config.Config;
import com.game.graphics.sprite.Player;
import com.game.graphics.Renderer;
import com.game.graphics.Sprite;
import com.game.graphics.StaticSprite;
import com.game.world.SlotTree;

import java.awt.*;
import java.util.ArrayList;

public class Terrain{

    private static long lastTime = System.nanoTime();
    public SlotTree[] treeSlots;
    public ArrayList<Sprite> sprites = new ArrayList<>();

    public static Terrain sc1 = new Terrain(1, 1, Renderer.gameSize.width-1, new SlotTree[]{new SlotTree(16,62,22,11),new SlotTree(44,62,22,10) ,new SlotTree(72,62,22,10)});
    public static Terrain sc2 = new Terrain(2, -Player.size.width+1, Renderer.gameSize.width-1,new SlotTree[]{new SlotTree(10,62,22,11),new SlotTree(38,62,22,10),new SlotTree(66,62,22,10)});
    public static Terrain sc3 = new Terrain(3, -Player.size.width+1, Renderer.gameSize.width-24, new SlotTree[]{new SlotTree(3,62,22,11),new SlotTree(31,62,22,10) ,new SlotTree(59,62,22,10)});

    public static Terrain currentTerrain = sc1;

    public Terrain(int i, int colLeft, int colRight, SlotTree[] treeSlots){
        this.treeSlots = treeSlots;
        sprites.add(new StaticSprite(0,0,false, "skybox"));
        sprites.add(new StaticSprite(0,49,false, "floor"+i));
        if (i == 1){sprites.add(new Player(50,50,false, colLeft, colRight));}
        else{sprites.add(new Player(1,50,false, colLeft, colRight));}
        sprites.add(new StaticSprite(10,80,false, "keys"));
        sprites.add(new StaticSprite(75,78,false, "chest"));
        sprites.add(new StaticSprite(75,87,false, "shop"));
    }

    public static void update(){
        if (currentTerrain==null){return;}
        float deltaTime = (System.nanoTime() - lastTime)/ 1000000000.0f;
        lastTime=System.nanoTime();

        for (Sprite sprite : currentTerrain.sprites){ sprite.update(deltaTime); }
    }

    public static void render (Graphics g){
        if (currentTerrain==null){ return; }
        for (Sprite sprite : currentTerrain.sprites){ sprite.render(g); }

        if (Config.DEBUG){
            for (SlotTree slotTree : currentTerrain.treeSlots){
                g.setColor(Color.red);
                g.drawRect(slotTree.x, slotTree.y, slotTree.width, slotTree.height);
            }
        }

    }

}

