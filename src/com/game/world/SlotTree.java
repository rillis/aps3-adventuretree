package com.game.world;

import com.game.config.Config;
import com.game.graphics.Sprite;
import com.game.graphics.StaticSprite;
import com.game.graphics.mob.Enemy;
import com.game.graphics.screens.Terrain;

import java.awt.*;

public class SlotTree {

    public static final int STATUS_IDLE = 0;
    public static final int STATUS_GROWING = 1;
    public static final int STATUS_ADULT = 2;
    public static final int STATUS_BREAKING = 3;

    public int x,y,height,width;

    private final Rectangle rectangle;

    private int status,tree;
    private Sprite treeSprite;

    public SlotTree(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(x,y,width,height);
        this.status = STATUS_IDLE;
        this.tree = 0;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public int getStatus(){
        return status;
    }

    public int getTree(){
        return tree;
    }

    public void plant(int tree){
        if (status != STATUS_IDLE){ return; }
        this.status = STATUS_GROWING;
        this.tree = tree;

        Sprite t = new StaticSprite(x-2, y-38, false, "tree"+tree+"sapling");
        Terrain terrain = Terrain.currentTerrain;
        new Thread(() -> {
            try{
                terrain.addSprite(t);
                Thread.sleep(Config.TREE_GROW_TIME);
                treeSprite = new StaticSprite(x-2, y-38, false, "tree"+tree);
                terrain.addSprite(treeSprite);
                terrain.removeSprite(t);
                status = STATUS_ADULT;
            }catch (Exception e){ e.printStackTrace(); }
        }).start();

    }

    public void remove(Terrain t){
        int speed = 20;

        if (t != Terrain.currentTerrain){removeTree(t);return;}
        new Thread(() -> {
            try {
                t.getEnemy().posX=0;
                Enemy.status = Enemy.GOING;
                for (int i = -10; i < x; i++) {
                    t.getEnemy().posX=i;
                    Thread.sleep(1000/speed);
                }
                Enemy.status = Enemy.BREAKING;
                Thread.sleep(1000);
                removeTree(t);
                Enemy.status = Enemy.RETURNING;
                for (int i = x-1; i > -10; i--) {
                    t.getEnemy().posX=i;
                    Thread.sleep(1000/speed);
                }
                Enemy.status = Enemy.IDLE;

            } catch (InterruptedException e) { e.printStackTrace(); }
        }).start();
    }

    private void removeTree(Terrain t){
        this.status = STATUS_BREAKING;
        this.tree=0;
        t.removeSprite(treeSprite);
        this.status = STATUS_IDLE;
    }
}
