package com.game.graphics;

import java.io.IOException;

public class Background extends Sprite{
    public Background(float posX, float posY, boolean center) {
        super(posX, posY, center);

        try {
            image=Renderer.loadImage("/com/game/resources/skybox.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
