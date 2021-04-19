package com.game.graphics;

import com.game.Game;
import com.game.config.Config;
import com.game.input.Input;
import com.game.input.MouseInput;
import com.game.state.GameState;
import com.game.graphics.screens.Terrain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

public class Renderer {
    public static Thread threadRender;
    public static boolean rendering = true;

    private static Canvas canvas;

    private static Dimension canvasSize;
    public static Dimension gameSize = new Dimension(100,100);

    private static long lastFPSCheck = 0;
    private static int actualFPS, totalFrames = 0;

    private static final int targetTime = 1000000000 / Config.targetFPS;

    public static void init(){
        Frame frame = new Frame();

        canvas = new Canvas();
        canvasSize = optimizeScreen();
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
        frame.toFront();
        frame.requestFocus();

        canvas.addKeyListener(new Input());
        canvas.addMouseListener(new MouseInput());

        startRendering();
    }

    private static Dimension optimizeScreen() {
        int sH = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        return new Dimension(Math.min(sH, 800), Math.min(sH, 800));
    }

    private static void startRendering() {
        threadRender = new Thread(() -> {
            GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
            VolatileImage vi = gc.createCompatibleVolatileImage(gameSize.width, gameSize.height);

            while(rendering){
                long startTime = System.nanoTime();

                totalFrames++;
                if (System.nanoTime() > lastFPSCheck+ 1000000000){
                    lastFPSCheck = System.nanoTime();
                    actualFPS = totalFrames;
                    totalFrames = 0;
                }

                if (vi.validate(gc)== VolatileImage.IMAGE_INCOMPATIBLE){
                    vi = gc.createCompatibleVolatileImage(gameSize.width, gameSize.height);
                }

                Graphics g = vi.getGraphics();
                g.setColor(Color.black);
                g.fillRect(0,0, gameSize.width, gameSize.height); // COMEÇO RENDER

                if(GameState.state == GameState.IN_GAME) {
                    Terrain.update();
                    Terrain.render(g);
                }else{
                    new StaticSprite(0,0,false,"menu").render(g);
                }

                if(Config.SHOW_FPS){
                    g.setColor(Color.BLACK);
                    g.fillRect(1,2, 55, 10);
                    g.setColor(Color.YELLOW);
                    g.setFont(new Font("Consolas", Font.PLAIN, 10));
                    g.drawString("FPS: "+actualFPS, 1, 10);
                }

                g.dispose(); //FINAL RENDER

                g = canvas.getGraphics();
                g.drawImage(vi, 0, 0, canvasSize.width, canvasSize.height, null);
                g.dispose();

                long totalTime = System.nanoTime() - startTime;

                if(totalTime < targetTime){
                    try {
                        Thread.sleep((targetTime-totalTime)/1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadRender.setName("Thread de renderizacao");
        threadRender.start();
    }

    public static BufferedImage loadImage(String path){
        BufferedImage rawImage = null;
        try {
            rawImage = ImageIO.read(Renderer.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage finalImage = canvas.getGraphicsConfiguration().createCompatibleImage(rawImage.getWidth(), rawImage.getHeight(), rawImage.getTransparency());
        finalImage.getGraphics().drawImage(rawImage,0,0, rawImage.getWidth(), rawImage.getHeight(), null);
        return finalImage;
    }
}
