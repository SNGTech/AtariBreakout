package cout.sngtech.breakout;

import com.sun.tools.javac.Main;

import java.awt.*;

public class Ball implements IEntity {

    int x;
    int y;

    int startX;
    int startY;

    int radius;

    float startXVel = 5.2f;
    float startYVel = 5.2f;

    double xVel;
    double yVel;

    double startAngleOfRotation = (float) (Math.PI / 4);
    double angleOfRotation;
    boolean hasCollided = false;
    Point prev;
    Point next;
    boolean flag = false;

    //int timesCollided = 1;

    Rectangle boundingBox;

    public Ball(int x, int y, int radius) {
        this.startX = x;
        this.startY = y;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xVel = -startXVel;
        this.yVel = -startYVel / 2;
        this.boundingBox = new Rectangle(x, y, radius - 8, radius - 8);
        angleOfRotation = startAngleOfRotation;
    }

    @Override
    public void update() {
        move();
        changeAngle();
        boundingBox.setLocation(x + 4, y + 4);
    }

    void move() {
        //IF COLLIDE

        if(x <= 0) {
            //timesCollided++;
            hasCollided = true;
            this.xVel = startXVel;
            new AePlayWave("ball_bounce.wav", 1).start();
        }
        if(x >= Game.window.getWidth() - radius) {
            //timesCollided++;
            hasCollided = true;
            this.xVel = -startXVel;
            new AePlayWave("ball_bounce.wav", 1).start();
        }
        if(y <= radius) {
            //timesCollided++;
            hasCollided = true;
            this.yVel = startYVel;
            new AePlayWave("ball_bounce.wav", 1).start();
        }
        if(y >= Game.window.getHeight() - radius) {
            resetPosition();
        }
        if(this.boundingBox.intersects(Game.deflector.boundingBox)) {
            //timesCollided++;
            hasCollided = true;
            this.yVel = -startYVel;
            new AePlayWave("deflector_hit.wav", 1).start();
        }

        // Get collisions based on which side (AABB)
        if(Game.brickManager.hasCollidedWithBrick()) {
            //timesCollided++;
            hasCollided = true;
            Rectangle brick = Game.brickManager.brickCollided.boundingBox;
            // NORTH
            if(this.boundingBox.y + this.boundingBox.height > brick.y && this.boundingBox.y + this.boundingBox.height - brick.y <= startYVel) {
                this.yVel = -startYVel;
            }
            // EAST
            if(this.boundingBox.x < brick.x + brick.width && brick.x + brick.width - this.boundingBox.x <= startXVel) {
                this.xVel = startXVel;
            }
            // SOUTH
            if(this.boundingBox.y < brick.y + brick.height && brick.y + brick.height - this.boundingBox.y <= startYVel) {
                this.yVel = startYVel;
            }
            // WEST
            if(this.boundingBox.x + this.boundingBox.width > brick.x && this.boundingBox.x + this.boundingBox.width - brick.x <= startXVel) {
                this.xVel = -startXVel;
            }
        }

        double extraSpeed = (angleOfRotation / startAngleOfRotation) * 0.91D;
        extraSpeed = Math.max(1, Math.min(extraSpeed, 1.4));
        x += xVel * Math.cos(angleOfRotation) * extraSpeed;
        y += yVel * Math.sin(angleOfRotation) * extraSpeed;
    }

    void changeAngle() {
        if(!hasCollided && !flag) {
            prev = new Point(x, y);
            flag = true;
        }
        if(hasCollided) {
            hasCollided = false;
            flag = false;
            next = new Point(x, y);
            int dx = Math.abs(next.x - prev.x);
            int dy = Math.abs(next.y - prev.y);
            int dist = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            int maxDist = 100;
            if(dist > maxDist) {
                dist = maxDist;
            }
            double maxAngle = Math.toRadians(65);
            angleOfRotation = maxAngle - (maxAngle - startAngleOfRotation) / maxDist * dist;
            angleOfRotation = Math.max(startAngleOfRotation, Math.min(angleOfRotation, maxAngle));
            //System.out.println(Math.toDegrees(angleOfRotation));
        }
    }

    void resetPosition() {
        Game.livesCount.loseLife();
        x = Game.deflector.x + Game.deflector.width / 2 - radius / 2;
        y = startY - 40;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(0xADB0BA));
        g.fillArc(x, y, radius, radius, 0, 360);

        //Display Collision Box
        /*g.setColor(new Color(0x57E31919, true));
        g.fill(boundingBox);*/
    }

    public void reset() {
        this.x = startX;
        this.y = startY;
        this.xVel = -startXVel;
        this.yVel = -startYVel / 2;
        angleOfRotation = startAngleOfRotation;
    }
}
