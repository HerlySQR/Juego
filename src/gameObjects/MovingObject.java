package gameObjects;

import math.Vector2D;

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

    public MovingObject(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture, double scale) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        direction = 0;
        this.scale = scale;
        width = (int) (texture.getWidth() * scale);
        height = (int) (texture.getHeight() * scale);
    }

    public void update() {

    }

    public void draw(Graphics g) {

    }
}
