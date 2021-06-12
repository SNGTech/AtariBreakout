package cout.sngtech.breakout.Animations;

import java.awt.*;

public interface IAnimation<T> {

    void play(Graphics2D g, T obj);
    void initKeyFrames();
}
