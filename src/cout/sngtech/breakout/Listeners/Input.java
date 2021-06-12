package cout.sngtech.breakout.Listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {

    private static final boolean[] keys = new boolean[KeyEvent.CHAR_UNDEFINED];

    public static boolean getKeyPressed(int key) {
        return keys[key];
    }

    public static boolean getAnyKeyPressed() {
        for(boolean key : keys) {
            if(key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
