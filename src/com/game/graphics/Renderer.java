package com.game.graphics;

import com.game.Game;
import com.game.config.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

public class Renderer {
    private static Frame frame;
    private static Canvas canvas;

    private static Dimension canvasSize = new Dimension(750,750);
    private static Dimension gameSize = new Dimension(300,300);

    private static long lastFPSCheck = 0;
    private static int atualFPS = 0;
    private static int totalFrames = 0;

    public static void init(){
        frame = new Frame();
        canvas = new Canvas();

        canvas.setPreferredSize(canvasSize);

        frame.add(canvas);
        frame.setUndecorated(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Game.quit();
            }

            @Override
            public void windowClosed(WindowEvent e){
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
                    totalFrames++;
                    if (System.nanoTime() > lastFPSCheck+ 1000000000){
                        lastFPSCheck = System.nanoTime();
                        atualFPS = totalFrames;
                        totalFrames = 0;
                    }

                    if (vi.validate(gc)== VolatileImage.IMAGE_INCOMPATIBLE){
                        vi = gc.createCompatibleVolatileImage(gameSize.width, gameSize.height);
                    }

                    Graphics g = vi.getGraphics();

                    g.setColor(Color.black);
                    g.fillRect(0,0, gameSize.width, gameSize.height);

                    //RENDER
                    if(Config.SHOW_FPS){
                        g.setColor(Color.YELLOW);
                        g.setFont(new Font("Consolas", Font.PLAIN, 10));
                        g.drawString("FPS: "+atualFPS, 10, 10);
                    }



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

    public static BufferedImage loadImage(String path) throws IOException {
        BufferedImage rawImage = ImageIO.read(Renderer.class.getResource(path));
        return canvas.getGraphicsConfiguration().createCompatibleImage(rawImage.getWidth(), rawImage.getHeight(), rawImage.getTransparency());
    }
}
