package com.game;

import com.game.config.Config;
import com.game.graphics.Renderer;

public class Game {
    public static void main(String[] args) {
        Renderer.init();
        Config.init(args);
    }

    public static void quit() { System.exit(0); }
}
