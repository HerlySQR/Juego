package gameObjects;

import data.GameConstants;
import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class MovingObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double direction;
    protected double maxVelocity;
    protected double scale;
    protected int width;
    protected int height;
    protected GameState gameState;

    public MovingObject(Vector2D position, Vector2D velocity, double maxVelocity, double angle, BufferedImage texture, double scale, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        direction = angle;
        this.scale = scale;
        width = (int) (texture.getWidth() * scale);
        height = (int) (texture.getHeight() * scale);
        this.gameState = gameState;
    }

    public void update() {
    }

    public void draw(Graphics g) {

    }
}
