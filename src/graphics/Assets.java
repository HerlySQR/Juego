package graphics;

import java.awt.image.BufferedImage;

/** The used assets*/
public class Assets {

    // Ships
    public static BufferedImage player;

    // Effects
    public static BufferedImage fire;

    public static void init() {
        player = Loader.ImageLoader("/PNG/playerShip1_blue.png");
        fire = Loader.ImageLoader("/PNG/Effects/fire08.png");
    }
}
