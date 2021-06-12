package cout.sngtech.breakout;

import java.awt.*;

public enum BrickType {

    LAYER_HIGH(600, new Color(0x222020)),
    LAYER_4(400, new Color(0xE53333)),
    LAYER_3(300, new Color(0xDB770A)),
    LAYER_2(200, new Color(0xDBC01C)),
    LAYER_1(100, new Color(0x71E533)),
    LAYER_LOW(50, new Color(0x1DD6D6));

    public final int points;
    public final Color color;

    BrickType(int points, Color color) {
        this.points = points;
        this.color = color;
    }
}
