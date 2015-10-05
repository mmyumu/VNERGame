package fr.mmyumu.vnergame.overworld;

import android.graphics.Rect;


public class OverworldEnemy {

    public Rect r = new Rect(0, 0, 0, 0);
    public int health = 5;
    private int power;
    private int centerX;
    private int speedX;
    private int centerY;
    //    private Background bg = OverworldScreen.getBg1();
    private OverworldCharacter mainCharacter;
    private int movementSpeed;

    public OverworldEnemy(OverworldCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    // Behavioral Methods
    public void update() {
        follow();
        centerX += speedX;
//        speedX = bg.getSpeedX() * 5 + movementSpeed;
        speedX = 5 + movementSpeed;
        r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

//        if (Rect.intersects(r, OverworldCharacter.hitBox)) {
//            checkCollision();
//        }
    }

    private void checkCollision() {
//        if (Rect.intersects(r, OverworldCharacter.hitBox)) {
//
//        }
    }

    public void follow() {

        if (centerX < -95 || centerX > 810) {
            movementSpeed = 0;
        } else if (Math.abs(mainCharacter.getCenter().x - centerX) < 5) {
            movementSpeed = 0;
        } else {

            if (mainCharacter.getCenter().x >= centerX) {
                movementSpeed = 1;
            } else {
                movementSpeed = -1;
            }
        }

    }

    public void die() {

    }

    public void attack() {

    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
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

//    public Background getBg() {
//        return bg;
//    }
//
//    public void setBg(Background bg) {
//        this.bg = bg;
//    }

}