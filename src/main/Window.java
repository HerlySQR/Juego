package main;

/*
 * Creating a game
 */

import data.GameConstants;
import graphics.Assets;
import input.KeyBoard;
import states.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/** The window of the Space Game */
public class Window extends JFrame implements Runnable {

    private Canvas canvas;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private double delta = 0;
    private int averageFPS = GameConstants.FPS;

    private GameState gameState;
    private KeyBoard keyBoard;

    public static void main(String[] args) {
        new Window().start();
    }

    /** Allocates a new window of the game*/
    public Window() {
        setTitle("Space Game");
        setSize(GameConstants.WIDTH, GameConstants.HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(GameConstants.WIDTH, GameConstants.HEIGHT));
        canvas.setMinimumSize(new Dimension(GameConstants.WIDTH, GameConstants.HEIGHT));
        canvas.setMaximumSize(new Dimension(GameConstants.WIDTH, GameConstants.HEIGHT));
        canvas.setFocusable(true);

        keyBoard = new KeyBoard();
        canvas.addKeyListener(keyBoard);

        add(canvas);
    }

    /** Update the values */
    private void update() {
        keyBoard.update();
        gameState.update();
    }

    /** Edit the canvas */
    private void draw() {
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // Drawing
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);

        gameState.draw(g);

        g.setColor(Color.YELLOW);
        g.drawString("FPS: " + averageFPS, 10, 10);
        // -- --

        g.dispose();
        bs.show();
    }

    private void init() {
        Assets.init();
        gameState = new GameState();
    }

    public void run() {
        final double TARGET_TIME = (double) 1000000000 / GameConstants.FPS;
        long now, diff;
        long last = System.nanoTime(), time = 0;
        int frames = 0;

        init();

        while (running) {
            now = System.nanoTime();
            diff = now - last;
            delta += diff / TARGET_TIME;
            time += diff;
            last = now;

            // Run
            if (delta >= 1) {
                update();
                draw();
                // Edit the values
                delta--;
                frames++;
            }

            // Average FPS
            if (time >= 1000000000) {
                averageFPS = frames;
                frames = 0;
                time = 0;
            }
        }
        stop();
    }

    /** Start the game */
    private void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /** Stop the game */
    private void stop() {
        try {
            thread.join();
            running = false;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
