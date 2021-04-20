package com.game.graphics.mob;

import com.game.graphics.Animation;
import com.game.graphics.Renderer;
import com.game.graphics.Sprite;
import com.game.input.Input;
import com.game.state.GameState;
import com.game.graphics.screens.Terrain;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    public static int WALL_LEFT = 0;
    public static int MOVING_LEFT = 0;
    public static int WALL_RIGHT = 1;
    public static int MOVING_RIGHT = 1;
    public static int MOVING_NOWHERE = -1;
    public static int MOVING = MOVING_NOWHERE;

    public static Animation walking_right, walking_left, idle;
    public static Dimension size = new Dimension(9,13);

    public final float movementSpeed = 30;
    private final int colRight, colLeft;

    private long teleportCooldown = 0;

    public Player(float posX, float posY, boolean center, int colLeft, int colRight) {
        super(posX, posY, center);

        walking_right = new Animation();
        for (int i = 1; i < 4; i++) {walking_right.images.add(Renderer.loadImage("/com/game/resources/charright"+i+".png"));}
        walking_left = new Animation();
        for (int i = 1; i < 4; i++) {walking_left.images.add(Renderer.loadImage("/com/game/resources/charleft"+i+".png"));}

        idle = new Animation();
        idle.images.add(Renderer.loadImage("/com/game/resources/char.png"));

        changeAnimation(idle);

        this.colLeft = colLeft;
        this.colRight = colRight;
    }

    @Override
    public void update(float delta){
        if(Input.getKey(KeyEvent.VK_ESCAPE)){
            GameState.state= GameState.MENU;
            return;
        }

        if(Input.getKey(KeyEvent.VK_LEFT)){ posX-=checkColision()*delta; MOVING = MOVING_LEFT; }
        else if(Input.getKey(KeyEvent.VK_RIGHT)){ posX+=checkColision()*delta; MOVING = MOVING_RIGHT; }
        else{ MOVING = MOVING_NOWHERE; }

        if(MOVING==MOVING_LEFT && animations[0]!=walking_left){ changeAnimation(walking_left); }
        else if(MOVING==MOVING_RIGHT && animations[0]!=walking_right){ changeAnimation(walking_right); }
        else if(MOVING==MOVING_NOWHERE){ changeAnimation(idle); }

    }

    private float checkColision() {
        if ((posX<=colLeft && MOVING == MOVING_LEFT) || (posX>=colRight && MOVING == MOVING_RIGHT)){
            colidesWithWall(MOVING);
            return 0;
        }
        return movementSpeed;
    }

    private void colidesWithWall(int wall) {
        if ((Terrain.currentTerrain==Terrain.sc1 && wall==0) || (Terrain.currentTerrain==Terrain.sc3 && wall==1) || System.nanoTime() < teleportCooldown){ return; }
        if (Terrain.currentTerrain==Terrain.sc1 || Terrain.currentTerrain==Terrain.sc3) { Terrain.currentTerrain = Terrain.sc2; }
        else if (Terrain.currentTerrain==Terrain.sc2 && wall == 0){ Terrain.currentTerrain=Terrain.sc1; }
        else if (Terrain.currentTerrain==Terrain.sc2){ Terrain.currentTerrain=Terrain.sc3; }
        teleportCooldown = System.nanoTime()+1000000000;
    }

    private void changeAnimation(Animation a){ animations = new Animation[]{a}; }
}
