package cout.sngtech.breakout;

import cout.sngtech.breakout.Listeners.Input;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Deflector implements IEntity {

    int width;
    int height;

    Rectangle boundingBox;

    int x;
    int y;

    int startX;
    int startY;

    float xVel = 7.0f;

    public Deflector(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
        this.x = startX;
        this.y = startY;

        boundingBox = new Rectangle(x, y, this.width - 12, this.height - 10);
    }

    @Override
    public void update() {
        move();
        this.boundingBox.setLocation(x + 6, y + 1);
    }

    public void move() {
        if(Input.getKeyPressed(KeyEvent.VK_A)) {
            x -= xVel;
        }
        if(Input.getKeyPressed(KeyEvent.VK_D)) {
            x += xVel;
        }

        // Keep deflector in game boundary
        if(x <= 0) {
            x += xVel;
        }
        if(x >= Game.window.getWidth() - this.width) {
            x -= xVel;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(0x2B83BC));
        g.fill(new Rectangle.Float(x, y, width, height));

        //Display Collision Box
        /*g.setColor(new Color(0x572DE319, true));
        g.fill(boundingBox);*/
    }

    public void reset() {
        this.x = 370;
        this.y = 520;
    }
}
