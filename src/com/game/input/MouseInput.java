package com.game.input;

import com.game.Game;
import com.game.graphics.Renderer;
import com.game.state.GameState;
import com.game.world.SlotTree;
import com.game.graphics.screens.Terrain;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if(GameState.state==GameState.MENU){
            if (byScreenFactor(27,60,50,18).contains(e.getPoint())){ GameState.state=GameState.IN_GAME; return; }
            if (byScreenFactor(29,79,42,15).contains(e.getPoint())){ Game.quit(); }
        }
        for(SlotTree slotTree : Terrain.currentTerrain.treeSlots){
            if(byScreenFactor(slotTree.x,slotTree.y,slotTree.width,slotTree.height).contains(e.getPoint())){ slotTree.plant(1); }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private Rectangle byScreenFactor(int x, int y, int w, int h){ return new Rectangle(Math.round((float)x * Renderer.screenFactor), Math.round((float)y * Renderer.screenFactor), Math.round((float)w * Renderer.screenFactor), Math.round((float)h * Renderer.screenFactor)); }
}
