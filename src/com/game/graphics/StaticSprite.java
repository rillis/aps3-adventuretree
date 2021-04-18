package com.game.graphics;

import java.io.IOException;

public class StaticSprite extends Sprite {
    public StaticSprite(float posX, float posY, boolean center, String name) {
        super(posX, posY, center);

        try {
            image=Renderer.loadImage("/com/game/resources/"+name+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
