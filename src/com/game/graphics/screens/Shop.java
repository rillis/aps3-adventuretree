package com.game.graphics.screens;

import com.game.graphics.Renderer;
import com.game.graphics.Sprite;
import com.game.graphics.StaticSprite;
import com.game.graphics.TextSprite;
import com.game.graphics.mob.Player;
import com.game.world.Tree;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

public class Shop {
    private static boolean spritesLocked= false;
    private final ArrayList<Sprite> sprites = new ArrayList<>();
    private final ArrayList<TextSprite> spritesText = new ArrayList<>();

    public static Rectangle[] slots = new Rectangle[]{new Rectangle(4,14,45,25),new Rectangle(4,41,45,25),new Rectangle(4,68,45,25),
            new Rectangle(51,14,45,25),new Rectangle(51,41,45,25),new Rectangle(51,68,45,25)};

    public static Shop shop = new Shop();
    public Shop(){
        sprites.add(new StaticSprite(0,0,false, "shop_inv"));

        for (int i = 1; i < Tree.trees.size()+1; i++) {
            sprites.add(new StaticSprite(slots[i-1].x-6, slots[i-1].y-18, false, "tree"+i+"sapling"));
            spritesText.add(new TextSprite(slots[i-1].x+16, slots[i-1].y+17, "-"+Renderer.formatCoins(Tree.trees.get(i).getCost()), new Color(128,0,0)));
            spritesText.add(new TextSprite(slots[i-1].x+16, slots[i-1].y+23, "+"+Renderer.formatCoins(Tree.trees.get(i).getCoins()), new Color(0,128,0)));
            sprites.add(new StaticSprite(slots[i-1].x+26, slots[i-1].y+2, false, "coin"));
        }
    }

    public static void render (Graphics g){

        lockSprites("RENDER every Sprite");
        boolean ran = false;
        while(!ran){
            try{
                for (Sprite sprite : shop.sprites){ sprite.render(g); }
                for (TextSprite sprite : shop.spritesText){ sprite.render(g); }
                ran=true;
            }catch (ConcurrentModificationException e){System.out.println("Concurrent!");}
        }
        unlockSprites("RENDER every Sprite");

    }

    public void removeSprite(Sprite sprite){ while(true){if(!spritesLocked){sprites.remove(sprite); break;}} }
    public void addSprite(Sprite sprite){ while(true){if(!spritesLocked){sprites.add(sprite); break;}} }
    public static void lockSprites(String e){spritesLocked=true; }
    public static void unlockSprites(String e){spritesLocked=false; }


}
