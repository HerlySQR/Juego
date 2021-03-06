package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Controls the pressed keys*/
public class KeyBoard implements KeyListener {

    public static boolean UP, DOWN, LEFT, RIGHT, SHOOT;

    private boolean[] keys = new boolean[256];

    public KeyBoard() {
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
        SHOOT = false;
    }

    public void update() {
        // Arrow keys
        UP = keys[KeyEvent.VK_UP];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        DOWN = keys[KeyEvent.VK_DOWN];
        // Letters
        SHOOT = keys[KeyEvent.VK_P];
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        //System.out.println("si");
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        //System.out.println("no");
    }
}
