package com.game;

import com.game.graphics.Background;
import com.game.graphics.Renderer;
import com.game.world.Terrain;

import java.io.IOException;

public class Game {
    public static void main(String[] args) {
        Renderer.init();

        Terrain.sprites.add(new Background(0,0));

    }

    public static void quit() {
        System.exit(0);
    }
}
