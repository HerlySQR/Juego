package gameObjects;

import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Representation of the objects in the game*/
public abstract class GameObject {

    protected BufferedImage texture;
    protected Vector2D position;

    public GameObject(Vector2D position, BufferedImage texture) {
        this.position = position;
        this.texture = texture;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

}
