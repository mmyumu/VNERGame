package fr.mmyumu.vnergame.overworld;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class OverworldCharacter {
    private static final int MOVESPEED = 20;

    private int centerX;
    private int centerY;
    private int speedX;
    private int speedY;

    private Point moveTarget;
    private Rect hitBox;
    private List<Direction> directions;


    public OverworldCharacter(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;

        this.speedX = 0;
        this.speedY = 0;

        this.moveTarget = null;
        this.hitBox = initHitBox();
        this.directions = new ArrayList<>();
    }
//    private Background bg1 = OverworldScreen.getBg1();
//    private Background bg2 = OverworldScreen.getBg2();

    public void update() {
        // Moves Character or Scrolls Background accordingly.

        if(moveTarget != null) {
            if (isRightClick(moveTarget.x)) {
                moveRight();
            } else if (isLeftClick(moveTarget.x)) {
                moveLeft();
            }

            if (isUpClick(moveTarget.y)) {
                moveUp();
            } else if (isDownClick(moveTarget.y)) {
                moveDown();
            }
        }


//        if (speedX < 0) {
//            centerX += speedX;
//        }
//        if (speedX == 0 || speedX < 0) {
//            bg1.setSpeedX(0);
//            bg2.setSpeedX(0);
//
//        }
//        if (centerX <= 200 && speedX > 0) {
//            centerX += speedX;
//        }
//        if (speedX > 0 && centerX > 200) {
//            bg1.setSpeedX(-MOVESPEED / 5);
//            bg2.setSpeedX(-MOVESPEED / 5);
//        }

        // Updates Y Position
        centerX += speedX;
        centerY += speedY;


        // Prevents going beyond X coordinate of 0
//        if (centerX + speedX <= 60) {
//            centerX = 61;
//        }

        hitBox = initHitBox();

//        rect.set(centerX - 34, centerY - 63, centerX + 34, centerY);
//        rect2.set(rect.left, rect.top + 63, rect.left + 68, rect.top + 128);
//        rect3.set(rect.left - 26, rect.top + 32, rect.left, rect.top + 52);
//        rect4.set(rect.left + 68, rect.top + 32, rect.left + 94, rect.top + 52);
//        yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);
//        footleft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
//        footright.set(centerX, centerY + 20, centerX + 50, centerY + 35);


    }

    private Rect initHitBox() {
        return new Rect(centerX - (OverworldConstants.TILE_WIDTH / 2), centerY - (OverworldConstants.TILE_HEIGHT / 2), centerX + (OverworldConstants.TILE_WIDTH / 2), centerY + (OverworldConstants.TILE_HEIGHT / 2));
    }

    public void moveRight() {
        speedX = MOVESPEED;
        directions.add(Direction.EAST);
        directions.remove(Direction.WEST);
    }

    public void moveLeft() {
        speedX = -MOVESPEED;
        directions.add(Direction.WEST);
        directions.remove(Direction.EAST);
    }

    public void moveUp() {
        speedY = -MOVESPEED;
        directions.add(Direction.NORTH);
        directions.remove(Direction.SOUTH);
    }

    public void moveDown() {
        speedY = MOVESPEED;
        directions.add(Direction.SOUTH);
        directions.remove(Direction.NORTH);
    }

    private void stop() {
        if (!isMovingRight() && !isMovingLeft()) {
            speedX = 0;
        }

        if (!isMovingRight() && isMovingLeft()) {
            moveLeft();
        }

        if (isMovingRight() && !isMovingLeft()) {
            moveRight();
        }
    }

    private boolean isMovingLeft() {
        return directions.contains(Direction.WEST);
    }

    private boolean isMovingRight() {
        return directions.contains(Direction.EAST);
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

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void collide(OverworldTile tile) {
        moveStop();
    }

    public Rect getHitBox() {
        return hitBox;
    }

    public void collideLeft(Rect rect) {
        speedX = 0;
        centerX += hitBox.left - rect.right;

    }

    public void collideRight(Rect rect) {
        speedX = 0;
        centerX -= rect.left - hitBox.right;
//        centerX -= OverworldConstants.TILE_WIDTH / 2;
    }

    public void collideUp(Rect rect) {
        speedY = 0;
        centerY += rect.bottom - hitBox.top;
//        centerY += OverworldConstants.TILE_HEIGHT / 2;
    }

    public void collideDown(Rect rect) {
        speedY = 0;
        centerY -= hitBox.bottom - rect.top;
//        centerY -= OverworldConstants.TILE_HEIGHT / 2;
    }

    public void moveStop() {
        speedX = 0;
        speedY = 0;
        directions.clear();
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
        return x > centerX + MOVESPEED;// + OverworldConstants.TILE_WIDTH / 2;
    }

    private boolean isLeftClick(int x) {
        return x < centerX - MOVESPEED;// - OverworldConstants.TILE_WIDTH / 2;
    }

    private boolean isUpClick(int y) {
        return y < centerY - MOVESPEED;// - OverworldConstants.TILE_HEIGHT / 2;
    }

    private boolean isDownClick(int y) {
        return y > centerY + MOVESPEED;// + OverworldConstants.TILE_HEIGHT / 2;
    }

    public void setMoveTarget(Point moveTarget) {
        this.moveTarget = moveTarget;
    }

    enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }

}