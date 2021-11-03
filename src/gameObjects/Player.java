package gameObjects;

import data.GameConstants;

import graphics.Assets;
import input.KeyBoard;
import math.Vector2D;
import states.GameState;

import javax.swing.Timer;
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
    private boolean accelarating1 = false;
    private boolean accelarating2 = false;

    private int maxCooldown; // Milliseconds
    private Timer fireRate;

    public Player(Vector2D position, double panSpeed, double maxVelocity, BufferedImage texture, double scale, GameState gameState) {
        super(position, new Vector2D(), maxVelocity, 0d,texture, scale, gameState);
        this.panSpeed = panSpeed;
        heading = new Vector2D(0d, 1d);
        acceleration = new Vector2D();

        maxCooldown = 100;
        fireRate = new Timer(maxCooldown, e -> {
            fireRate.stop();
        });
    }

    @Override
    public void update() {

        // Shoot
        if (KeyBoard.SHOOT && !fireRate.isRunning()) {
            gameState.getMovingObjects().add(0, new Laser(
                    getCenter().plus(heading.scale(width)),
                    heading,
                    10,
                    direction,
                    Assets.redLaser,
                    1,
                    gameState)
            );
            fireRate.start();
        }

        // Move
        if (KeyBoard.LEFT) {
            direction -= panSpeed;
            accelarating1 = true;
        }
        if (KeyBoard.RIGHT) {
            direction += panSpeed;
            accelarating2 = true;
        }
        if (direction > Math.PI)
            direction -= 2 * Math.PI;
        if (direction < -Math.PI)
            direction += 2 * Math.PI;
        if (KeyBoard.UP) {
            acceleration = heading.scale(ACCELERATION);
            accelarating1 = true;
            accelarating2 = true;
        }
        else {
            if (velocity.size() != 0) {
                acceleration = velocity.normalize().scale(-ACCELERATION * brakeCoef);
            }
        }
        if (KeyBoard.DOWN)
            brakeCoef = Math.min(brakeCoef + 0.1, 2);
        else
            brakeCoef = Math.max(brakeCoef - 0.1, 1);
        velocity = velocity.plus(acceleration);
        velocity.limit(maxVelocity);
        heading.setDirection(direction - Math.PI/2);
        position = position.plus(velocity);

        if (position.x > GameConstants.WIDTH)
            position.x = 0;
        if (position.y > GameConstants.HEIGHT)
            position.y = 0;
        if (position.x < 0)
            position.x = GameConstants.WIDTH;
        if (position.y < 0)
            position.y = GameConstants.HEIGHT;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (accelarating1) {
            AffineTransform at1 = AffineTransform.getTranslateInstance(position.x + width / 2d + 5d, position.y + height / 2d + 10d);
            at1.rotate(direction, -5d, -10d);
            //at1.scale(scale, scale);
            g2d.drawImage(Assets.fire, at1, null);
            accelarating1 = false;
        }
        if (accelarating2) {
            AffineTransform at2 = AffineTransform.getTranslateInstance(position.x + 5d, position.y + height / 2f + 10d);
            at2.rotate(direction, (double) width / 2d - 5d, -10d);
            //at2.scale(scale, scale);
            g2d.drawImage(Assets.fire, at2, null);
            accelarating2 = false;
        }

        at = AffineTransform.getTranslateInstance(position.x, position.y);
        at.rotate(direction, width / 2f, height / 2f);
        at.scale(scale, scale);

        g2d.drawImage(texture, at, null);
    }

    public Vector2D getCenter() {
        return new Vector2D(position.x + width/2d, position.y + height/2d);
    }
}
