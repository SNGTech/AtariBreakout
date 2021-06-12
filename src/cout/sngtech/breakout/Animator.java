package cout.sngtech.breakout;

public class Animator {

    //In Nanoseconds
    protected double startTime = 0;
    private double pausedTime = 0;
    protected double elapsedTime = 0;
    public boolean completed = false;

    protected double getElapsedTime() {
        if(startTime <= 0) {
            startTime = System.nanoTime();
        }
        return System.nanoTime() - startTime;
    }

    protected double getPausedTime() {
        if(Game.sceneManager.gameState == SceneManager.States.PAUSED || Game.sceneManager.gameState == SceneManager.States.RESUMED) {
            pausedTime = System.nanoTime() - startTime;
            return pausedTime;
        }
        return pausedTime;
    }
}
