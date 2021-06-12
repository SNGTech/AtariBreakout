package cout.sngtech.breakout;

import cout.sngtech.breakout.Animations.BrickHitAnimation;
import cout.sngtech.breakout.Animations.ScoringAnimation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Brick implements IEntity {

    public int width, height;

    public float x, y;
    public float startX, startY;

    public BrickType type;

    Rectangle boundingBox;

    public boolean isHit = false;

    ScoringAnimation scoringAnimation;
    BrickHitAnimation brickHitAnimation;
    public boolean canRemove = false;

    public Brick(int x, int y, int width, int height, BrickType type) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.width = width;
        this.height = height;
        boundingBox = new Rectangle(x, y, this.width, this.height);
        this.type = type;

        scoringAnimation = new ScoringAnimation();
        brickHitAnimation = new BrickHitAnimation();
    }

    @Override
    public void update() {
        // To ensure that no collision occurs if it has been hit once
        isHit = false;
        if(this.boundingBox.intersects(Game.ball.boundingBox)) {
            isHit = true;
            Game.scoreManager.newScore += type.points;
            new AePlayWave("brick_break.wav", 1).start();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(type.color);
        if(isHit && !brickHitAnimation.completed) {
            brickHitAnimation.play(g,this);
        }
        else if(!brickHitAnimation.completed) {
            g.fill(new Rectangle2D.Float(x, y, width, height));
        }

        //Display Collision Box
        /*g.setColor(new Color(0x571934E3, true));
        g.fill(boundingBox);*/

        if(isHit && !scoringAnimation.completed) {
            scoringAnimation.play(g,this);
            if(scoringAnimation.completed) {
                canRemove = true;
            }
        }
    }

    public void reset() {
        this.x = startX;
        this.y = startY;
        canRemove = false;
        isHit = false;

        scoringAnimation = new ScoringAnimation();
        brickHitAnimation = new BrickHitAnimation();
    }
}
