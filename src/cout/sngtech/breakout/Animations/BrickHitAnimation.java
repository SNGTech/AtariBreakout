package cout.sngtech.breakout.Animations;

import cout.sngtech.breakout.Animator;
import cout.sngtech.breakout.Brick;
import cout.sngtech.breakout.Game;
import cout.sngtech.breakout.SceneManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class BrickHitAnimation extends Animator implements IAnimation<Brick> {

    int y = 0;
    int alpha = 255;
    double angle = 0;
    double maxAngle = 20;

    double[] keyFrames = new double[1];

    @Override
    public void play(Graphics2D g, Brick brick) {
        initKeyFrames();
        elapsedTime = getElapsedTime() - getPausedTime();
        AffineTransform reset = g.getTransform();
        g.setColor(new Color(brick.type.color.getRed(), brick.type.color.getGreen(), brick.type.color.getBlue(), alpha));
        g.rotate(angle, brick.x, brick.y);
        g.fill(new Rectangle2D.Float(brick.x, brick.y, brick.width, brick.height));
        if(Game.sceneManager.gameState == SceneManager.States.GAME || Game.sceneManager.gameState == SceneManager.States.GAME_OVER) {
            if (elapsedTime < keyFrames[0]) {
                angle += Math.toRadians(2);
                angle = Math.min(angle, Math.toRadians(maxAngle));
                alpha -= 35;
                alpha = Math.max(0, Math.min(alpha, 255));
                y += 2.5f;
                brick.y += y;
            } else {
                completed = true;
                startTime = 0;
                angle = 0;
                y = 0;
                alpha = 0;
            }
        }
        g.setTransform(reset);
    }

    @Override
    public void initKeyFrames() {
        keyFrames[0] = 100 * 1_000_000D;
    }
}
