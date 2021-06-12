package cout.sngtech.breakout.Animations;

import cout.sngtech.breakout.Animator;
import cout.sngtech.breakout.Brick;
import cout.sngtech.breakout.Game;
import cout.sngtech.breakout.SceneManager;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ScoringAnimation extends Animator implements IAnimation<Brick> {

    int y = 0;
    int alpha = 0;

    double[] keyFrames = new double[3];

    @Override
    public void play(Graphics2D g, Brick brick) {
        initKeyFrames();
        elapsedTime = getElapsedTime() - getPausedTime();
        AffineTransform reset = g.getTransform();
        g.setFont(new Font("Serif", Font.BOLD, 25));
        g.setColor(new Color(230, 230, 230, alpha));
        g.drawString(String.valueOf(brick.type.points), (int) (brick.startX + brick.width / 2) - 20, (int) (brick.startY + brick.height / 2) + 15 + y);
        //DURATION 1: INCREASE TRANSPARENCY
        if(Game.sceneManager.gameState == SceneManager.States.GAME || Game.sceneManager.gameState == SceneManager.States.GAME_OVER) {
            if (elapsedTime < keyFrames[0]) {
                alpha += 30;
                alpha = Math.max(0, Math.min(alpha, 255));
            }
            //DURATION 2: DECREASE TRANSPARENCY
            if (elapsedTime < keyFrames[2] && elapsedTime > keyFrames[1]) {
                alpha -= 20;
                alpha = Math.max(0, Math.min(alpha, 255));
            }
            //CONSTANT DURATION: DECREASE Y VALUE
            if (elapsedTime < keyFrames[2]) {
                y -= 1.5f;
            }
            //Complete animation
            else {
                completed = true;
                startTime = 0;
                y = 0;
                alpha = 0;
            }
        }
        g.setTransform(reset);
    }

    @Override
    public void initKeyFrames() {
        keyFrames[0] = 100 * 1_000_000D;
        keyFrames[1] = 200 * 1_000_000D;
        keyFrames[2] = 500 * 1_000_000D;
    }
}
