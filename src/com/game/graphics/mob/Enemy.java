package com.game.graphics.mob;

import com.game.config.Config;
import com.game.graphics.Animation;
import com.game.graphics.Renderer;
import com.game.graphics.Sprite;
import com.game.graphics.screens.Terrain;
import com.game.world.SlotTree;
import sun.java2d.pipe.AAShapePipe;

public class Enemy extends Sprite {
    public final static int IDLE = 0;
    public final static int GOING = 1;
    public final static int BREAKING = 2;
    public final static int RETURNING = 3;

    Animation idle = new Animation();
    Animation going = new Animation();
    Animation returning = new Animation();
    Animation breaking = new Animation();

    private static long enemyCooldown = 0;
    public static int status = IDLE;

    public Enemy(float posX, float posY, boolean center) {
        super(posX, posY, center);

        idle.images.add(Renderer.loadImage("/com/game/resources/enemy.png"));

        going.images.add(Renderer.loadImage("/com/game/resources/enemygoing1.png"));
        going.images.add(Renderer.loadImage("/com/game/resources/enemygoing2.png"));
        going.images.add(Renderer.loadImage("/com/game/resources/enemygoing3.png"));

        returning.images.add(Renderer.loadImage("/com/game/resources/enemyreturning1.png"));
        returning.images.add(Renderer.loadImage("/com/game/resources/enemyreturning2.png"));
        returning.images.add(Renderer.loadImage("/com/game/resources/enemyreturning3.png"));

        breaking.images.add(Renderer.loadImage("/com/game/resources/enemybreaking1.png"));
        breaking.images.add(Renderer.loadImage("/com/game/resources/enemybreaking2.png"));

        changeAnimation(idle);
    }


    @Override
    public void update(float delta) {
        if(status==GOING && animations[0]!=going){ changeAnimation(going); }
        else if(status==BREAKING && animations[0]!=breaking){ changeAnimation(breaking); }
        else if(status==RETURNING && animations[0]!=returning){ changeAnimation(returning); }
        else if(status==IDLE){ changeAnimation(idle); }

        Terrain[] terrains = new Terrain[]{Terrain.sc1, Terrain.sc2, Terrain.sc3};
        if(status==IDLE){
            Terrain breakTerrain=null;
            SlotTree breakSlotTree=null;
            for(Terrain terrain : terrains){
                for(SlotTree slotTree : terrain.treeSlots){
                    if (slotTree.getStatus() == SlotTree.STATUS_ADULT && Math.random()<=0.05){
                        breakTerrain = terrain;
                        breakSlotTree = slotTree;
                        break;
                    }
                }
            }
            if (breakSlotTree==null){return;}
            if(System.nanoTime() > enemyCooldown){
                breakSlotTree.remove(breakTerrain);
                System.out.println("Arvore cortada");
                enemyCooldown = System.nanoTime()+ 1000000000L * Config.EnemyCooldown;
            }
        }
    }

    private void changeAnimation(Animation a){ animations = new Animation[]{a}; }
}
