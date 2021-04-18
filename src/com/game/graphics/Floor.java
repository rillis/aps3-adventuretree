package com.game.graphics;

import com.game.input.Input;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Floor extends Sprite{
    public Floor(float posX, float posY, boolean center) {
        super(posX, posY, center);

        try {
            image=Renderer.loadImage("/com/game/resources/floor.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float delta){
        if(Input.getKey(KeyEvent.VK_RIGHT)){  }
        if(Input.getKey(KeyEvent.VK_LEFT)){  }
    }
}
