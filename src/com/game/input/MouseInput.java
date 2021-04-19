package com.game.input;

import com.game.Game;
import com.game.state.GameState;
import com.game.world.SlotTree;
import com.game.graphics.screens.Terrain;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(GameState.state==GameState.MENU){
            if (new Rectangle(27,60,50,18).contains(new Point( e.getX()/8, e.getY()/8))){ GameState.state=GameState.IN_GAME; return; }
            if (new Rectangle(29,79,42,15).contains(new Point( e.getX()/8, e.getY()/8))){ Game.quit(); }
        }
        for(SlotTree slotTree : Terrain.currentTerrain.treeSlots){
            if(slotTree.getRectangle().contains(new Point( e.getX()/8, e.getY()/8))){ slotTree.plant(1); }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
