package fr.mmyumu.vnergame.overworld;

import android.graphics.Point;
import android.graphics.Rect;

public class OverworldCharacter {
    private static final int MOVESPEED = 20;

    private int centerX;
    private int centerY;
    private double speedX;
    private double speedY;

    private Point moveTarget;
    private Rect hitBox;

    public OverworldCharacter(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;

        this.speedX = 0;
        this.speedY = 0;

        this.moveTarget = null;
        this.hitBox = initHitBox();
    }
//    private Background bg1 = OverworldScreen.getBg1();
//    private Background bg2 = OverworldScreen.getBg2();

    public void update() {
        // Moves Character or Scrolls Background accordingly.

        if (moveTarget != null) {
//            System.out.println("##### moveTarget x=" + moveTarget.x + " y=" + moveTarget.y);
//            if (isRightClick(moveTarget.x)) {
//                System.out.println("##### RIGHT !");
//                moveRight();
//            } else if (isLeftClick(moveTarget.x)) {
//                System.out.println("##### LEFT !");
//                moveLeft();
//            }
//
//            if (isUpClick(moveTarget.y)) {
//                System.out.println("##### UP !");
//                moveUp();
//            } else if (isDownClick(moveTarget.y)) {
//                System.out.println("##### DOWN !");
//                moveDown();
//            }

            System.out.println("##### centerX=" + centerX + " centerY=" + centerY);
            System.out.println("##### moveTarget x=" + moveTarget.x + " y=" + moveTarget.y);
            System.out.println("##### (moveTarget.y - centerY)=" + (moveTarget.y - centerY));
            System.out.println("##### (moveTarget.x - centerX)=" + (moveTarget.x - centerX));

            int xDistance = moveTarget.x - centerX;
            int yDistance = moveTarget.y - centerY;

            if (!isCloserThanMaxMovement(xDistance, yDistance)) {
                if (moveTarget.x == centerX) {
                    speedX = 0;
                    speedY = MOVESPEED;
                } else if (moveTarget.y == centerY) {
                    speedX = MOVESPEED;
                    speedY = 0;
                } else {

                    double slope = yDistance / (double) xDistance;
                    System.out.println("##### slope=" + slope);
                    speedX = MOVESPEED / Math.sqrt(slope * slope + 1);
                    speedY = Math.sqrt(MOVESPEED * MOVESPEED - speedX * speedX);
                }

                if (moveTarget.x < centerX) {
                    speedX = -speedX;
                }

                if (moveTarget.y < centerY) {
                    speedY = -speedY;
                }

                System.out.println("##### speedX=" + speedX + " speedY=" + speedY);
                centerX += speedX;
                centerY += speedY;
            }
        }

        hitBox = initHitBox();
    }

    private boolean isCloserThanMaxMovement(int xDistance, int yDistance) {
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance)) < MOVESPEED;
    }

    private Rect initHitBox() {
        return new Rect(centerX - (OverworldConstants.TILE_WIDTH / 2), centerY - (OverworldConstants.TILE_HEIGHT / 2), centerX + (OverworldConstants.TILE_WIDTH / 2), centerY + (OverworldConstants.TILE_HEIGHT / 2));
    }

    public void moveRight() {
        speedX = MOVESPEED;
    }

    public void moveLeft() {
        speedX = -MOVESPEED;
    }

    public void moveUp() {
        speedY = -MOVESPEED;
    }

    public void moveDown() {
        speedY = MOVESPEED;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void collide(OverworldTile tile) {
        moveStop();
    }

    public Rect getHitBox() {
        return hitBox;
    }

    public void collideLeft(Rect rect) {
        speedX = 0;
//        centerX += hitBox.left - rect.right;
    }

    public void collideRight(Rect rect) {
        speedX = 0;
//        centerX -= rect.left - hitBox.right;
//        centerX -= OverworldConstants.TILE_WIDTH / 2;
    }

    public void collideUp(Rect rect) {
        speedY = 0;
//        centerY += rect.bottom - hitBox.top;
//        centerY += OverworldConstants.TILE_HEIGHT / 2;
    }

    public void collideDown(Rect rect) {
        speedY = 0;
//        centerY -= hitBox.bottom - rect.top;
//        centerY -= OverworldConstants.TILE_HEIGHT / 2;
    }

    public void moveStop() {
        speedX = 0;
        speedY = 0;
    }

//    public void move() {
//        if (isRightClick(event.x)) {
//            mainCharacter.moveRight();
//        } else if (isLeftClick(event.x)) {
//            mainCharacter.moveLeft();
//        }
//
//        if (isUpClick(event.y)) {
//            mainCharacter.moveUp();
//        } else if (isDownClick(event.y)) {
//            mainCharacter.moveDown();
//        }
//    }

    private boolean isRightClick(int x) {
        System.out.println("##### OverworldCharacter.isRightClick x=" + x + " centerX=" + centerX + " MOVESPEED=" + MOVESPEED + " centerX+MOVESPEED=" + (centerX + MOVESPEED));
        return x > centerX + MOVESPEED;// + OverworldConstants.TILE_WIDTH / 2;
    }

    private boolean isLeftClick(int x) {
        System.out.println("##### OverworldCharacter.isLeftClick x=" + x + " centerX=" + centerX + " MOVESPEED=" + MOVESPEED + " centerX-MOVESPEED=" + (centerX - MOVESPEED));
        return x < centerX - MOVESPEED;// - OverworldConstants.TILE_WIDTH / 2;
    }

    private boolean isUpClick(int y) {
        System.out.println("##### OverworldCharacter.isUpClick y=" + y + " centerY=" + centerY + " MOVESPEED=" + MOVESPEED + " centerY-MOVESPEED=" + (centerY - MOVESPEED));
        return y < centerY - MOVESPEED;// - OverworldConstants.TILE_HEIGHT / 2;
    }

    private boolean isDownClick(int y) {
        System.out.println("##### OverworldCharacter.isDownClick y=" + y + " centerY=" + centerY + " MOVESPEED=" + MOVESPEED + " centerY+MOVESPEED=" + (centerY + MOVESPEED));
        return y > centerY + MOVESPEED;// + OverworldConstants.TILE_HEIGHT / 2;
    }

    public void setMoveTarget(Point moveTarget) {
        this.moveTarget = moveTarget;
    }
}