package com.game.graphics;

import com.game.graphics.Sprite;

public class StaticSprite extends Sprite {
    public StaticSprite(int x, int y, boolean center, String png) {
        super(x,y,center);

        Animation animation = new Animation();
        animation.images.add(Renderer.loadImage("/com/game/resources/"+png+".png"));
        animations = new Animation[]{  animation };
    }
}
