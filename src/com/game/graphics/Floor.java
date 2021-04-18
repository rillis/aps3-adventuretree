package com.game.graphics;

import com.game.input.Input;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Floor extends Sprite{
    public Floor(float posX, float posY, boolean center, int i) {
        super(posX, posY, center);

        try {
            image=Renderer.loadImage("/com/game/resources/floor"+i+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
