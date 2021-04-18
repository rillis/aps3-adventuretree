package com.game.scenes;

import com.game.graphics.*;
import com.game.world.SlotTree;
import com.game.world.Terrain;

import java.awt.*;

public class Scene extends Terrain {
    public SlotTree[] treeSlots;
    public Scene(int i, int colLeft, int colRight, SlotTree[] treeSlots){
        this.treeSlots = treeSlots;
        sprites.add(new Background(0,0,false));
        if (i == 1){
            sprites.add(new Player(50,50,false, colLeft, colRight));
        }else if(i == 2 || i == 3) {
            sprites.add(new Player(-Player.width+1,50,false, colLeft, colRight));
        }

        sprites.add(new Floor(0,49,false, i));
        sprites.add(new StaticSprite(10,80,false, "keys"));
        sprites.add(new StaticSprite(75,78,false, "chest"));
        sprites.add(new StaticSprite(75,87,false, "shop"));
    }
}
