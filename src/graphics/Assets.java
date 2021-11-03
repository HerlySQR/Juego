package graphics;

import java.awt.image.BufferedImage;

/** The used assets*/
public class Assets {

    // Ships
    public static BufferedImage player;

    // Effects
    public static BufferedImage fire;

    // Lasers
    public static BufferedImage blueLaser, greenLaser, redLaser;

    // Meteors
    public static BufferedImage[] meteorGrayBig = new BufferedImage[4];
    public static BufferedImage[] meteorGrayMed = new BufferedImage[2];
    public static BufferedImage[] meteorGraySmall = new BufferedImage[2];
    public static BufferedImage[] meteorGrayTiny = new BufferedImage[2];

    public static void init() {

        // Ships
        player = Loader.ImageLoader("/PNG/playerShip1_blue.png");

        // Effects
        fire = Loader.ImageLoader("/PNG/Effects/fire08.png");

        // Lasers
        blueLaser = Loader.ImageLoader("/PNG/Lasers/laserBlue01.png");
        greenLaser = Loader.ImageLoader("/PNG/Lasers/laserGreen11.png");
        redLaser = Loader.ImageLoader("/PNG/Lasers/laserRed01.png");

        // Meteors
        for (int i = 0; i < meteorGrayBig.length; i++)
            meteorGrayBig[i] = Loader.ImageLoader("/PNG/Meteors/meteorGrey_big" + (i+1) + ".png");
        for (int i = 0; i < meteorGrayMed.length; i++)
            meteorGrayMed[i] = Loader.ImageLoader("/PNG/Meteors/meteorGrey_med" + (i+1) + ".png");
        for (int i = 0; i < meteorGraySmall.length; i++)
            meteorGraySmall[i] = Loader.ImageLoader("/PNG/Meteors/meteorGrey_small" + (i+1) + ".png");
        for (int i = 0; i < meteorGrayTiny.length; i++)
            meteorGrayTiny[i] = Loader.ImageLoader("/PNG/Meteors/meteorGrey_tiny" + (i+1) + ".png");
    }
}
