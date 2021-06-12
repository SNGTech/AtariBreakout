package cout.sngtech.breakout;

import java.awt.*;

public class BoundingBox extends Rectangle {

    int x, y;

    public BoundingBox(int width, int height) {
        this.width = width;
        this.height = height;;
        this.x = (int) this.getX();
        this.y = (int) this.getY();
    }

    @Override
    public boolean intersects(Rectangle r) {
        return super.intersects(r);
    }
}
