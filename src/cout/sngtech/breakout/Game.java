package cout.sngtech.breakout;

import cout.sngtech.breakout.Graphics.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Game {

    public static GameWindow window;
    //Board board;
    public static Deflector deflector;
    public static BrickManager brickManager;
    public static Ball ball;

    public static ScoreManager scoreManager;
    public static SceneManager sceneManager;

    public static PlayerLives livesCount;

    public static Timer fps;

    public static void main(String[] args) {
        window = new GameWindow();
        deflector = new Deflector(370, 520, 250, 17);
        brickManager = new BrickManager(4, 8);
        brickManager.generateBricks();
        ball = new Ball(480, 490, 30);

        scoreManager = new ScoreManager();
        sceneManager = new SceneManager();

        livesCount = new PlayerLives(3);

        //Initialise audio so that audio will play correctly when game starts
        new AePlayWave("brick_break.wav", 0).start();
        new AePlayWave("ball_bounce.wav", 0).start();
        new AePlayWave("lose_life.wav", 0).start();
        new AePlayWave("deflector_hit.wav", 0).start();
        new AePlayWave("select.wav", 0).start();

        fps = new Timer(10, Game::update);
        fps.start();
    }

    private static void update(ActionEvent e) {
        sceneManager.update();

        if(sceneManager.gameState == SceneManager.States.GAME) {
            deflector.update();
            ball.update();
            brickManager.update();
        }
        window.render();
    }
}
