package cout.sngtech.breakout.Animations;

import cout.sngtech.breakout.Animator;
import cout.sngtech.breakout.Game;
import cout.sngtech.breakout.SceneManager;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class DecrementLivesAnimation extends Animator implements IAnimation {

    int y = 0;
    int alpha = 0;

    double[] keyFrames = new double[3];

    @Override
    public void play(Graphics2D g, Object obj) {
        initKeyFrames();
        elapsedTime = getElapsedTime() - getPausedTime();
        AffineTransform reset = g.getTransform();
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.setColor(new Color(152, 5, 5, alpha));
        g.drawString(String.valueOf(-1), Game.window.getWidth() - 170, 70 + y);
        //DURATION 1: INCREASE TRANSPARENCY
        if(Game.sceneManager.gameState == SceneManager.States.GAME || Game.sceneManager.gameState == SceneManager.States.GAME_OVER) {
            if (elapsedTime < keyFrames[0]) {
                alpha += 50;
                alpha = Math.max(0, Math.min(alpha, 255));
            }
            if (elapsedTime < keyFrames[2] && elapsedTime > keyFrames[1]) {
                alpha -= 50;
                alpha = Math.max(0, Math.min(alpha, 255));
            }
            if (elapsedTime < keyFrames[2]) {
                y += 1.0f;
            }
            //Complete animation
            if(elapsedTime > keyFrames[2]) {
                completed = true;
                startTime = 0;
                alpha = 255;
                y = 0;
            }
        }
        g.setTransform(reset);
    }

    @Override
    public void initKeyFrames() {
        keyFrames[0] = 100 * 1_000_000D;
        keyFrames[1] = 200 * 1_000_000D;
        keyFrames[2] = 300 * 1_000_000D;
    }
}
