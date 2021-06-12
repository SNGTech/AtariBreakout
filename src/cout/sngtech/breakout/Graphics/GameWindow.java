package cout.sngtech.breakout.Graphics;

import cout.sngtech.breakout.Listeners.Input;
import cout.sngtech.breakout.Game;
import cout.sngtech.breakout.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;

public class GameWindow extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private BufferStrategy bs;

    public GameWindow() {
        Input input = new Input();
        this.setSize(new Dimension(WIDTH + this.getInsets().left + this.getInsets().right, HEIGHT + this.getInsets().top + this.getInsets().bottom));
        this.setTitle("Atari Breakout Clone");
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x0E0E0E));
        URL iconLocation = Game.class.getResource("/res/icon.png");
        ImageIcon icon = new ImageIcon(iconLocation);
        this.setIconImage(icon.getImage());
        this.setIgnoreRepaint(true);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addKeyListener(input);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(bs == null) {
            this.createBufferStrategy(3);
        }

        bs = this.getBufferStrategy();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void render() {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        // Clear Screen
        g.setColor(this.getBackground());
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Begin Rendering
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Game.deflector.render(g);
        Game.brickManager.renderBricks(g);
        Game.ball.render(g);
        if(Game.sceneManager.gameState != SceneManager.States.GAME_OVER) {
            Game.scoreManager.renderScore(g);
            Game.livesCount.render(g);
        }
        Game.sceneManager.render(g);

        g.dispose();
        bs.show();
    }
}
