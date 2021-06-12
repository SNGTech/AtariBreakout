package cout.sngtech.breakout.Animations;

import cout.sngtech.breakout.AePlayWave;
import cout.sngtech.breakout.Animator;
import cout.sngtech.breakout.Game;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class ResumeAnimation extends Animator implements IAnimation {

    int size = 0;
    int startSize = 41;
    int maxSize = 60;

    int countdown = 0;

    double[] keyFrames = new double[3];

    @Override
    public void play(Graphics2D g, Object obj) {
        initKeyFrames();
        elapsedTime = getElapsedTime();
        AffineTransform reset = g.getTransform();
        g.setFont(new Font("Serif", Font.BOLD, size));
        TextLayout layout = new TextLayout(String.valueOf(countdown), g.getFont(), g.getFontRenderContext());
        float textWidth = (float) layout.getBounds().getWidth();
        float textHeight = (float) layout.getBounds().getHeight();
        g.setColor(new Color(235, 235, 235));
        g.drawString(String.valueOf(countdown), (int) (Game.window.getWidth() / 2 + textWidth / 2 - size / 2), (int) (Game.window.getHeight() / 2 + textHeight / 2));
        if(elapsedTime < keyFrames[0]) {
            if(countdown == 0) {
                size = startSize;
                new AePlayWave("ball_bounce.wav", 1).start();
            }
            countdown = 3;
            size += 2;
            size = Math.min(size, maxSize);
        }
        if(elapsedTime < keyFrames[1] && elapsedTime > keyFrames[0]) {
            if(countdown == 3) {
                size = startSize;
                new AePlayWave("ball_bounce.wav", 1).start();
            }
            countdown = 2;
            size += 2;
            size = Math.min(size, maxSize);
        }
        if(elapsedTime < keyFrames[2] && elapsedTime > keyFrames[1]) {
            if(countdown == 2) {
                size = startSize;
                new AePlayWave("ball_bounce.wav", 1).start();
            }
            countdown = 1;
            size += 2;
            size = Math.min(size, maxSize);
        }
        //Complete animation
        if(elapsedTime > keyFrames[2]) {
            completed = true;
            startTime = 0;
            size = 0;
        }
        g.setTransform(reset);
    }

    @Override
    public void initKeyFrames() {
        keyFrames[0] = 1000 * 1_000_000D;
        keyFrames[1] = 2000 * 1_000_000D;
        keyFrames[2] = 3000 * 1_000_000D;
    }
}
