package states;

import data.GameConstants;
import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;

public class GameState {

    private Player player;

    public GameState() {
        player = new Player(new Vector2D(100d, 400d),
                GameConstants.PLAYER_TURN_SPEED, GameConstants.PLAYER_MAX_SPEED, Assets.player,
                GameConstants.PLAYER_IMAGE_SCALE);
    }

    public void update() {
        player.update();
    }

    public void draw(Graphics g) {
        player.draw(g);
    }
}
