package cout.sngtech.breakout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrickManager {

    int[][] numBricks;
    int spacing = 10;
    int offsetX = 33;
    int offsetY = 100;

    private static final int WIDTH = 108;
    private static final int HEIGHT = 50;

    public static List<Brick> originalBricks = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Brick> hitBricks = new ArrayList<>();

    public Brick brickCollided;

    public BrickManager(int row, int col) {
        this.numBricks = new int[row][col];
    }

    public void generateBricks() {
        for(int i = 0; i < numBricks.length; i++) {
            for(int j = 0; j < numBricks[0].length; j++) {
                Brick brick = new Brick((WIDTH + spacing) * j + offsetX, (HEIGHT + spacing) * i + offsetY, WIDTH, HEIGHT, getBrickType(i));
                originalBricks.add(brick);
                bricks.add(brick);
            }
        }
    }

    public void update() {
        brickCollided = null;
        for (int i = 0; i < bricks.size(); i++) {
            bricks.get(i).update();
            if(bricks.get(i).isHit) {
                brickCollided = bricks.get(i);
                hitBricks.add(bricks.get(i));
                bricks.remove(bricks.get(i));
            }
        }
        for (int i = 0; i < hitBricks.size(); i++) {
            if(hitBricks.get(i).canRemove) {
                hitBricks.remove(hitBricks.get(i));
            }
        }
    }

    public void renderBricks(Graphics2D g) {
        for (Brick brick : bricks) {
            brick.render(g);
        }
        for(Brick brick : hitBricks) {
            brick.render(g);
        }
    }

    public boolean hasCollidedWithBrick() {
        if(brickCollided != null && brickCollided.isHit) {
            return true;
        }
        return false;
    }

    BrickType getBrickType(int row) {
        switch(row) {
            case 0:
                return BrickType.LAYER_4;
            case 1:
                return BrickType.LAYER_3;
            case 2:
                return BrickType.LAYER_2;
            case 3:
                return BrickType.LAYER_1;
            default:
                return BrickType.LAYER_LOW;
        }
    }

    public void reset() {
        bricks.clear();
        for(Brick brick : originalBricks) {
            bricks.add(brick);
            brick.reset();
        }
    }
}
