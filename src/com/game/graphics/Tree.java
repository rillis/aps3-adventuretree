package com.game.graphics;

import java.io.IOException;

public class Tree extends Sprite {
    public Tree(float posX, float posY, boolean center, int i, String sappling) {
        super(posX, posY, center);

        try {
            image=Renderer.loadImage("/com/game/resources/tree"+i+sappling+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
