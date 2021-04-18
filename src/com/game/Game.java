package com.game;

import com.game.graphics.Renderer;

public class Game {
    public static void main(String[] args) {
        Renderer.init();
    }

    public static void quit() {
        System.exit(0);
    }
}
