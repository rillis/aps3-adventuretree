package com.game.graphics;

import com.game.Game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.VolatileImage;

public class Renderer {
    private static Frame frame;
    private static Canvas canvas;

    private static Dimension canvasSize = new Dimension(720,720);
    private static Dimension gameSize = new Dimension(200,200);

    public static void init(){
        frame = new Frame();
        canvas = new Canvas();

        canvas.setPreferredSize(canvasSize);

        frame.add(canvas);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Game.quit();
            }
        });

        frame.setVisible(true);

        startRendering();
    }

    private static void startRendering() {
        Thread thread = new Thread(){
            public void run(){

                GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
                VolatileImage vi = gc.createCompatibleVolatileImage(gameSize.width, gameSize.height);

                while(true){
                    if (vi.validate(gc)== VolatileImage.IMAGE_INCOMPATIBLE){
                        vi = gc.createCompatibleVolatileImage(gameSize.width, gameSize.height);
                    }

                    Graphics g = vi.getGraphics();

                    g.setColor(Color.black);
                    g.fillRect(0,0, gameSize.width, gameSize.height);

                    //RENDER

                    g.dispose();

                    g = canvas.getGraphics();
                    g.drawImage(vi, 0, 0, canvasSize.width, canvasSize.height, null);

                    g.dispose();
                }
            }
        };
        thread.setName("Thread de renderizacao");
        thread.start();
    }
}
