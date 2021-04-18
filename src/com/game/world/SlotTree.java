package com.game.world;

import com.game.config.Config;
import com.game.graphics.Sprite;
import com.game.graphics.Tree;

import java.awt.*;

public class SlotTree {

    private static final int STATUS_IDLE = 0;
    private static final int STATUS_GROWING = 1;
    private static final int STATUS_ADULT = 2;
    private static final int STATUS_BREAKING = 3;

    public int x;
    public int y;
    public int height;
    public int width;

    private final Rectangle rectangle;

    private int status;
    private int tree;
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

        Tree t = new Tree((float) x-2, (float) y-38, false, tree, "sapling");
        Terrain terrain = Terrain.currentTerrain;
        new Thread(){
            @Override
            public void run() {
                try{
                    terrain.sprites.add(t);
                    Thread.sleep(Config.TREE_GROW_TIME);
                    Sprite t2 = new Tree((float) x-2, (float) y-38, false, tree, "");
                    terrain.sprites.add(t2);
                    terrain.sprites.remove(t);
                    treeSprite = t2;
                    status = STATUS_ADULT;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
