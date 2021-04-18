package com.game.graphics;

import java.io.IOException;

public class Tree extends Sprite {
    public Tree(float posX, float posY, boolean center, int i) {
        super(posX, posY, center);

        try {
            image=Renderer.loadImage("/com/game/resources/tree"+i+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
