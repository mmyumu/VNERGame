package fr.mmyumu.vnergame.overworld;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import fr.mmyumu.vnergame.common.Speed;

public class OverworldCharacter {
    private static final String TAG = "OverworldCharacter";
    private static final int MOVESPEED = 20;
    private Point oldCenter;
    private Point center;
    private Point moveTarget;
    private Rect oldHitBox;
    private Rect hitBox;
    private Speed speed;

    public OverworldCharacter(Point center) {
        this.center = center;

        this.moveTarget = null;
        this.hitBox = initHitBox(center);
    }

    //    private Background bg1 = OverworldScreen.getBg1();
//    private Background bg2 = OverworldScreen.getBg2();

    public Point getCenter() {
        return center;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void computeMovement() {
        oldCenter = center;
        oldHitBox = initHitBox(oldCenter);

        if (moveTarget != null) {
            Log.d(TAG, "##### centerX=" + center.x + " centerY=" + center.y);
            Log.d(TAG, "##### moveTarget x=" + moveTarget.x + " y=" + moveTarget.y);
            Log.d(TAG, "##### (moveTarget.y - centerY)=" + (moveTarget.y - center.x));
            Log.d(TAG, "##### (moveTarget.x - centerX)=" + (moveTarget.x - center.y));

            if (!isTargetCloserThanMaxMovement()) {
                speed = computeSpeed();
            }
        }
    }

    public void applyHorizontalMovement() {
        center.x += speed.getX();
        hitBox = initHitBox(center);
    }

    public void applyVerticalMovement() {
        center.y += speed.getY();
        hitBox = initHitBox(center);
    }

    private Speed computeSpeed() {
        Speed speed = new Speed();
        if (isVerticalMovement()) {
            speed.setX(0);
            speed.setY(MOVESPEED);
        } else if (isHorizontalMovement()) {
            speed.setX(MOVESPEED);
            speed.setY(0);
        } else {
            int xDistance = moveTarget.x - center.x;
            int yDistance = moveTarget.y - center.y;

            double slope = yDistance / (double) xDistance;
            Log.d(TAG, "##### slope=" + slope);
            speed.setX(MOVESPEED / Math.sqrt(slope * slope + 1));
            speed.setY(Math.sqrt(MOVESPEED * MOVESPEED - speed.getX() * speed.getX()));
        }

        if (moveTarget.x < center.x) {
            speed.invertX();
        }

        if (moveTarget.y < center.y) {
            speed.invertY();
        }

        System.out.println("##### speedX=" + speed.getX() + " speedY=" + speed.getY());
        return speed;
    }

    private boolean isVerticalMovement() {
        return moveTarget.x == center.x;
    }

    private boolean isHorizontalMovement() {
        return moveTarget.y == center.y;
    }

    private boolean isTargetCloserThanMaxMovement() {
        int xDistance = moveTarget.x - center.x;
        int yDistance = moveTarget.y - center.y;
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance)) < MOVESPEED;
    }

    private Rect initHitBox(Point p) {
        return new Rect(retrieveLeft(p), retrieveTop(p), retrieveRight(p), retrieveBottom(p));
    }

    private int retrieveLeft(Point p) {
        return p.x - OverworldConstants.TILE_WIDTH / 2;
    }

    private int retrieveTop(Point p) {
        return p.y - OverworldConstants.TILE_HEIGHT / 2;
    }

    private int retrieveRight(Point p) {
        return p.x + OverworldConstants.TILE_WIDTH / 2;
    }

    private int retrieveBottom(Point p) {
        return p.y + OverworldConstants.TILE_HEIGHT / 2;
    }

    public void setMoveTarget(Point moveTarget) {
        this.moveTarget = moveTarget;
    }

    public int computeCollisionFromRight(Rect obstacle) {
        if (oldHitBox.right < obstacle.left && // was not colliding
                hitBox.right >= obstacle.left) {
            return hitBox.right - obstacle.left;
        }
        return 0;
    }

    public int computeCollisionFromLeft(Rect obstacle) {
        if (oldHitBox.left > obstacle.right && // was not colliding
                hitBox.left <= obstacle.right) {
            return obstacle.right - hitBox.left;
        }
        return 0;
    }

    public int computeCollisionFromBottom(Rect obstacle) {
        if (oldHitBox.bottom < obstacle.top && // was not colliding
                hitBox.bottom >= obstacle.top) {
            return hitBox.bottom - obstacle.top;
        }
        return 0;
    }

    public int computeCollisionFromTop(Rect obstacle) {
        if (oldHitBox.top > obstacle.bottom && // was not colliding
                hitBox.top <= obstacle.bottom) {
            return obstacle.bottom - hitBox.top;
        }
        return 0;
    }

    public void bumpFromLeftCollision(Rect obstacle) {
        int distanceIntoTheWall = obstacle.right - hitBox.left;
        Log.d(TAG, "##### center.x=" + center.x + " center.y=" + center.y + " distanceIntoTheWall=" + distanceIntoTheWall);
        center.x += distanceIntoTheWall;
    }

    public void bumpFromRightCollision(Rect obstacle) {
        int distanceIntoTheWall = hitBox.right - obstacle.left;
        Log.d(TAG, "##### center.x=" + center.x + " center.y=" + center.y + " distanceIntoTheWall=" + distanceIntoTheWall);
        center.x -= distanceIntoTheWall;
    }

    public void bumpFromTopCollision(Rect obstacle) {
        int distanceIntoTheWall = obstacle.bottom - hitBox.top;
        Log.d(TAG, "##### center.x=" + center.x + " center.y=" + center.y + " distanceIntoTheWall=" + distanceIntoTheWall);
        center.y += distanceIntoTheWall;
    }

    public void bumpFromBottomCollision(Rect obstacle) {
        int distanceIntoTheWall = hitBox.bottom - obstacle.top;
        Log.d(TAG, "##### center.x=" + center.x + " center.y=" + center.y + " distanceIntoTheWall=" + distanceIntoTheWall);
        center.x -= distanceIntoTheWall;
    }

    public boolean hasCollision(Rect obstacle) {
        return hitBox.intersect(obstacle);
    }
}