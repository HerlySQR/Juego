package states;

import data.GameConstants;
import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class GameState {

    private Player player;

    private ArrayList<MovingObject> movingObjects = new ArrayList<>();

    public GameState() {
        player = new Player(new Vector2D(100d, 400d),
                GameConstants.PLAYER_TURN_SPEED, GameConstants.PLAYER_MAX_SPEED, Assets.player,
                GameConstants.PLAYER_IMAGE_SCALE, this);
        movingObjects.add(player);
    }

    public void update() {
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }
    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    public void setMovingObjects(ArrayList<MovingObject> movingObjects) {
        this.movingObjects = movingObjects;
    }
}
