package com.game.graphics;

import com.game.input.Input;
import com.game.world.Terrain;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Player extends Sprite{

    public static int WALL_LEFT = 0;
    public static int WALL_RIGHT = 1;

    public static int MOVING_LEFT = 0;
    public static int MOVING_RIGHT = 1;
    public static int MOVING_NOWHERE = -1;
    public static int MOVING = MOVING_NOWHERE;

    public static int height = 12;
    public static int width = 8;

    public float movementSpeed = 30;
    private int colLeft = 0;
    private int colRight = 0;

    public Player(float posX, float posY, boolean center, int colLeft, int colRight) {
        super(posX, posY, center);
        this.colLeft = colLeft;
        this.colRight = colRight;


        try {
            image=Renderer.loadImage("/com/game/resources/char.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float delta){
        if(Input.getKey(KeyEvent.VK_RIGHT)){ posX+=checkColision()*delta; MOVING = MOVING_RIGHT;}
        else if(Input.getKey(KeyEvent.VK_LEFT)){ posX-=checkColision()*delta; MOVING = MOVING_LEFT;}
        else{ MOVING = MOVING_NOWHERE;}
    }

    private float checkColision() {

        if (posX<=colLeft && MOVING == MOVING_LEFT){
            colidesWithWall(WALL_LEFT);
            return 0;
        }
        if (posX>=colRight && MOVING == MOVING_RIGHT){
            colidesWithWall(WALL_RIGHT);
            return 0;
        }
        return movementSpeed;
    }

    private void colidesWithWall(int wall) {
        if ((Terrain.currentTerrain==Terrain.sc1 && wall==0) || (Terrain.currentTerrain==Terrain.sc3 && wall==1)){
            return;
        }
        if (Terrain.currentTerrain==Terrain.sc1 || Terrain.currentTerrain==Terrain.sc3){
            Terrain.currentTerrain=Terrain.sc2;
        }else if (Terrain.currentTerrain==Terrain.sc2 && wall == 0){
            Terrain.currentTerrain=Terrain.sc1;
        }else if (Terrain.currentTerrain==Terrain.sc2){
            Terrain.currentTerrain=Terrain.sc3;
        }
    }
}
