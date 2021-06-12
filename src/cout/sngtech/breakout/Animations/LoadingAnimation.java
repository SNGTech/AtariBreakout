package cout.sngtech.breakout.Animations;

import cout.sngtech.breakout.AePlayWave;
import cout.sngtech.breakout.Animator;
import cout.sngtech.breakout.Game;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class LoadingAnimation extends Animator implements IAnimation {

    int width = 100;
    int height = 100;

    int angle = 0;
    int endAngle = 55;

    double[] keyFrames = new double[2];

    @Override
    public void play(Graphics2D g, Object obj) {
        initKeyFrames();
        elapsedTime = getElapsedTime();
        AffineTransform reset = g.getTransform();
        g.setColor(new Color(0x1F1D1D));
        g.fillRect(0, 0, Game.window.getWidth(), Game.window.getHeight());
        g.setColor(new Color(0x3F479B));
        g.setStroke(new BasicStroke(5));
        g.drawArc(Game.window.getWidth() / 2 - width / 2, Game.window.getHeight() / 2 - height / 2, width, height, angle, endAngle);
        if(elapsedTime < keyFrames[0]) {
            angle += 7;
            endAngle += 8;
            if(angle > 360) {
                angle = 0;
            }
            if(endAngle > 360) {
                endAngle = 55;
            }
        }
        //Complete animation
        if(elapsedTime > keyFrames[0]) {
            completed = true;
            startTime = 0;
            angle = 0;
            endAngle = 55;
        }
        g.setTransform(reset);
    }

    @Override
    public void initKeyFrames() {
        keyFrames[0] = 2500 * 1_000_000D;
    }
}
