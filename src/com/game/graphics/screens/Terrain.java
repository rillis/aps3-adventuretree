package com.game.graphics.screens;

import com.game.config.Config;
import com.game.graphics.mob.Enemy;
import com.game.graphics.mob.Player;
import com.game.graphics.Renderer;
import com.game.graphics.Sprite;
import com.game.graphics.StaticSprite;
import com.game.world.SlotTree;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Terrain{

    private static long lastTime = System.nanoTime();
    private static boolean spritesLocked= false;
    public SlotTree[] treeSlots;
    private final ArrayList<Sprite> sprites = new ArrayList<>();

    public static Terrain sc1 = new Terrain(1, 1, Renderer.gameSize.width-1, new SlotTree[]{new SlotTree(16,62,22,11),new SlotTree(44,62,22,10) ,new SlotTree(72,62,22,10)});
    public static Terrain sc2 = new Terrain(2, -Player.size.width+1, Renderer.gameSize.width-1,new SlotTree[]{new SlotTree(10,62,22,11),new SlotTree(38,62,22,10),new SlotTree(66,62,22,10)});
    public static Terrain sc3 = new Terrain(3, -Player.size.width+1, Renderer.gameSize.width-24, new SlotTree[]{new SlotTree(3,62,22,11),new SlotTree(31,62,22,10) ,new SlotTree(59,62,22,10)});

    public static Terrain currentTerrain = sc1;

    private final Enemy enemy = new Enemy(-10,50,false);

    public Terrain(int i, int colLeft, int colRight, SlotTree[] treeSlots){
        this.treeSlots = treeSlots;
        sprites.add(new StaticSprite(0,0,false, "skybox"));
        sprites.add(new StaticSprite(0,49,false, "floor"+i));
        if (i == 1){sprites.add(new Player(50,50,false, colLeft, colRight));}
        else{sprites.add(new Player(1,50,false, colLeft, colRight));}
        sprites.add(enemy);
        sprites.add(new StaticSprite(10,80,false, "keys"));
        sprites.add(new StaticSprite(75,78,false, "chest"));
        sprites.add(new StaticSprite(75,87,false, "shop"));
    }

    public static void update(){
        if (currentTerrain==null){return;}
        float deltaTime = (System.nanoTime() - lastTime)/ 1000000000.0f;
        lastTime=System.nanoTime();

        boolean ran = false;
        while(!ran){
            try{
                for (Sprite sprite : currentTerrain.sprites){ sprite.update(deltaTime); }
                ran=true;
            }catch (ConcurrentModificationException e){System.out.println("Concurrent!");}
        }
    }

    public static void render (Graphics g){
        if (currentTerrain==null){ return; }

        lockSprites("RENDER every Sprite & debug slot tree");
        boolean ran = false;
        while(!ran){
            try{
                for (Sprite sprite : currentTerrain.sprites){ sprite.render(g); }
                ran=true;
            }catch (ConcurrentModificationException e){System.out.println("Concurrent!");}
        }
        if (Config.DEBUG){
            spritesLocked = true;
            for (SlotTree slotTree : currentTerrain.treeSlots){
                g.setColor(Color.red);
                g.drawRect(slotTree.x, slotTree.y, slotTree.width, slotTree.height);
            }
            spritesLocked = false;
        }
        unlockSprites("RENDER every Sprite & debug slot tree");

    }
    public Enemy getEnemy(){ return enemy; }

    public void removeSprite(Sprite sprite){ while(true){if(!spritesLocked){sprites.remove(sprite); break;}} }

    public void addSprite(Sprite sprite){ while(true){if(!spritesLocked){sprites.add(sprite); break;}} }

    public static void lockSprites(String e){spritesLocked=true; }
    public static void unlockSprites(String e){spritesLocked=false; }
    public static boolean isSpritesLocked(){return spritesLocked;}

}

