package cout.sngtech.breakout;

import cout.sngtech.breakout.Animations.DecrementLivesAnimation;

import java.awt.*;

public class PlayerLives {

    DecrementLivesAnimation animation;

    int lives;
    int maxLives;

    boolean hasLostLife = false;

    public PlayerLives(int maxLives) {
        this.lives = maxLives;
        this.maxLives = maxLives;
        animation = new DecrementLivesAnimation();
    }

    public void loseLife() {
        lives--;
        hasLostLife = true;
        new AePlayWave("lose_life.wav", 1).start();
    }

    public boolean hasLost() {
        return lives < 1;
    }

    public void reset() {
        hasLostLife = false;
        lives = maxLives;
        animation = new DecrementLivesAnimation();
    }

    public void render(Graphics2D g) {
        g.setColor(new Color(0xBFBFBF));
        g.setFont(new Font("Serif", Font.BOLD, 25));
        g.drawString("Lives: " + lives, Game.window.getWidth() - 260, 70);

        //System.out.println(hasLostLife);
        if(hasLostLife) {
            animation.play(g, null);
            if (animation.completed) {
                animation = new DecrementLivesAnimation();
                hasLostLife = false;
            }
        }
    }
}
