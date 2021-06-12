package cout.sngtech.breakout;

import cout.sngtech.breakout.Animations.LoadingAnimation;
import cout.sngtech.breakout.Animations.ResumeAnimation;
import cout.sngtech.breakout.Listeners.Input;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SceneManager {

    public enum States {
        LOADING, GAME, PAUSED, RESUMED, GAME_OVER, EXIT
    }

    LoadingAnimation loadingAnimation;
    ResumeAnimation resumeAnimation;

    public States gameState;

    public SceneManager() {
        loadingAnimation = new LoadingAnimation();
        resumeAnimation = new ResumeAnimation();
        gameState = States.LOADING;
    }

    void update() {
        if(Input.getKeyPressed(KeyEvent.VK_ESCAPE) && gameState == States.GAME) {
            gameState = States.PAUSED;
            new AePlayWave("select.wav", 1).start();
        }
        if(Input.getKeyPressed(KeyEvent.VK_ENTER) && gameState == States.PAUSED) {
            gameState = States.RESUMED;
            new AePlayWave("select.wav", 1).start();
        }
        if(BrickManager.bricks.isEmpty() || Game.livesCount.hasLost() && gameState != States.EXIT) {
            gameState = States.GAME_OVER;
            if(Input.getKeyPressed(KeyEvent.VK_ESCAPE)) {
                gameState = States.EXIT;
                new AePlayWave("select.wav", 1).start();
            }
            if(Input.getAnyKeyPressed() && gameState != States.EXIT) {
                restart();
                new AePlayWave("select.wav", 1).start();
            }
        }
        if(Input.getKeyPressed(KeyEvent.VK_R)) {
            restart();
            new AePlayWave("select.wav", 1).start();
        }
    }

    public void render(Graphics2D g) {
        if(gameState == States.PAUSED) {
            renderFocusedBackground(g);

            String text = "";
            g.setFont(new Font("Serif", Font.BOLD, 50));
            text = "Paused";
            FontMetrics metrics = g.getFontMetrics();
            g.setColor(new Color(0xDCEBEBEB, true));
            g.drawString(text, Game.window.getWidth() / 2 - metrics.stringWidth(text) / 2, Game.window.getHeight() / 2 + metrics.getHeight() / 2 - 30);

            g.setFont(new Font("Serif", Font.BOLD, 30));
            text = "Press ENTER key to resume";
            metrics = g.getFontMetrics();
            g.setColor(new Color(0xC7EBEBEB, true));
            g.drawString(text, Game.window.getWidth() / 2 - metrics.stringWidth(text) / 2, Game.window.getHeight() / 2 + metrics.getHeight() / 2 + 30);
        }
        if(gameState == States.GAME_OVER) {
            renderFocusedBackground(g);

            String text = "";
            g.setFont(new Font("Serif", Font.BOLD, 50));
            text = "Game Over";
            FontMetrics metrics = g.getFontMetrics();
            g.setColor(new Color(0xDC980505, true));
            g.drawString(text, Game.window.getWidth() / 2 - metrics.stringWidth(text) / 2, Game.window.getHeight() / 2 + metrics.getHeight() / 2 - 55);

            g.setFont(new Font("Serif", Font.BOLD, 40));
            text = "Score: ";
            String score = String.valueOf(Game.scoreManager.newScore);
            metrics = g.getFontMetrics();
            g.setColor(new Color(0xDCEBEBEB, true));
            g.drawString(text, Game.window.getWidth() / 2 - metrics.stringWidth(text) / 2 - metrics.stringWidth(score) / 2, Game.window.getHeight() / 2 + metrics.getHeight() / 2);
            g.setColor(new Color(0xDC1D6D07, true));
            g.drawString(score, Game.window.getWidth() / 2 - metrics.stringWidth(score) / 2 + metrics.stringWidth(text) / 2, Game.window.getHeight() / 2 + metrics.getHeight() / 2);

            g.setFont(new Font("Serif", Font.BOLD, 30));
            text = "Press any key to restart";
            metrics = g.getFontMetrics();
            g.setColor(new Color(0xC7EBEBEB, true));
            g.drawString(text, Game.window.getWidth() / 2 - metrics.stringWidth(text) / 2, Game.window.getHeight() / 2 + metrics.getHeight() / 2 + 50);
        }
        if(gameState == States.RESUMED) {
            renderFocusedBackground(g);
            resumeAnimation.play(g, null);
            if(resumeAnimation.completed) {
                gameState = States.GAME;
                resumeAnimation = new ResumeAnimation();
                new AePlayWave("deflector_hit.wav", 1).start();
            }
        }
        if(gameState == States.LOADING) {
            loadingAnimation.play(g, null);
            if(loadingAnimation.completed) {
                gameState = States.RESUMED;
                loadingAnimation = new LoadingAnimation();
            }
        }
        if(gameState == States.EXIT) {
            loadingAnimation.play(g, null);
            if(loadingAnimation.completed) {
                System.exit(0);
            }
        }
    }

    void renderFocusedBackground(Graphics2D g) {
        g.setColor(new Color(0xDA151515, true));
        g.fillRect(0, 0, Game.window.getWidth(), Game.window.getHeight());
    }

    void restart() {
        Game.brickManager.reset();
        Game.scoreManager.currentScore = 0;
        Game.scoreManager.newScore = 0;
        Game.livesCount.reset();
        Game.deflector.reset();
        Game.ball.reset();
        gameState = States.RESUMED;
    }
}
