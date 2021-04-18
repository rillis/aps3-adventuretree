package com.game.input;

import com.game.world.SlotTree;
import com.game.world.Terrain;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        for(SlotTree slotTree : Terrain.currentTerrain.treeSlots){
            if(slotTree.getRectangle().contains(new Point( e.getX()/8, e.getY()/8))){
                slotTree.plant(1);
            }
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
