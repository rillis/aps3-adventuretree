package com.game;

import com.game.graphics.*;
import com.game.graphics.Renderer;
import com.game.scenes.Scene;
import com.game.world.Terrain;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Renderer.init();
    }

    public static void quit() {
        System.exit(0);
    }
}
