package gameObjects;

import main.Window;

import graphics.Assets;
import input.KeyBoard;
import math.Vector2D;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/** Representation o a player (ship)*/
public class Player extends MovingObject {

    private final Vector2D heading;
    private final double panSpeed;
    private final double ACCELERATION = 0.08;
    private Vector2D acceleration;
    private double brakeCoef = 1;
    private boolean accelarating = false;

    public Player(Vector2D position, double panSpeed, double maxVelocity, BufferedImage texture, double scale) {
        super(position, new Vector2D(), maxVelocity, texture, scale);
        this.panSpeed = panSpeed;
        heading = new Vector2D(0d, 1d);
        acceleration = new Vector2D();
    }

    @Override
    public void update() {
        if (KeyBoard.RIGHT)
            direction += panSpeed;
        if (KeyBoard.LEFT)
            direction -= panSpeed;
        if (direction > Math.PI)
            direction -= 2 * Math.PI;
        if (direction < -Math.PI)
            direction += 2 * Math.PI;
        if (KeyBoard.UP) {
            acceleration = heading.scale(ACCELERATION);
            accelarating = true;
        }
        else {
            if (velocity.size() != 0) {
                acceleration = velocity.normalize().scale(-ACCELERATION * brakeCoef);
            }
            accelarating = false;
        }
        if (KeyBoard.DOWN)
            brakeCoef = Math.min(brakeCoef + 0.1, 2);
        else
            brakeCoef = Math.max(brakeCoef - 0.1, 1);
        velocity = velocity.plus(acceleration);
        velocity.limit(maxVelocity);
        heading.setDirection(direction - Math.PI/2);
        position = position.plus(velocity);

        if (position.x > Window.WIDTH)
            position.x = 0;
        if (position.y > Window.HEIGHT)
            position.y = 0;
        if (position.x < 0)
            position.x = Window.WIDTH;
        if (position.y < 0)
            position.y = Window.HEIGHT;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (accelarating) {
            AffineTransform at1 = AffineTransform.getTranslateInstance(position.x + width / 2d + 5d, position.y + height / 2d + 10d);
            AffineTransform at2 = AffineTransform.getTranslateInstance(position.x + 5d, position.y + height / 2f + 10d);
            at1.rotate(direction, -5d, -10d);
            at2.rotate(direction, (double) width / 2d - 5d, -10d);
            //at1.scale(scale, scale);
            //at2.scale(scale, scale);
            g2d.drawImage(Assets.fire, at1, null);
            g2d.drawImage(Assets.fire, at2, null);
        }

        at = AffineTransform.getTranslateInstance(position.x, position.y);
        at.rotate(direction, width / 2f, height / 2f);
        at.scale(scale, scale);

        g2d.drawImage(Assets.player, at, null);
    }

}
