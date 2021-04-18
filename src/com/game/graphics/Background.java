package com.game.graphics;

import java.io.IOException;

public class Background extends Sprite{
    public Background(float posX, float posY) {
        super(posX, posY);

        try {
            image=Renderer.loadImage("-");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
