package cout.sngtech.breakout;

import java.awt.*;

public class ScoreManager {

    int currentScore = 0;
    int newScore = 0;

    public void renderScore(Graphics2D g) {
        //INCREMENT SCORE TO NEW SCORE
        currentScore += 24;
        currentScore = Math.min(currentScore, newScore);

        g.setColor(new Color(0xBFBFBF));
        g.setFont(new Font("Serif", Font.BOLD, 25));
        String scoreWithPadding = String.format("%04d", currentScore);
        g.drawString("Score: " + scoreWithPadding, Game.window.getWidth() - 150, 70);
    }
}
