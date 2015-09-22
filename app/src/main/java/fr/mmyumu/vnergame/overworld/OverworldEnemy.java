package fr.mmyumu.vnergame.overworld;

import android.graphics.Rect;

import fr.mmyumu.vnergame.Background;


public class OverworldEnemy {

    private int power, centerX, speedX, centerY;
    private Background bg = OverworldScreen.getBg1();
    private OverworldCharacter mainCharacter = OverworldScreen.getMainCharacter();

    public Rect r = new Rect(0, 0, 0, 0);
    public int health = 5;

    private int movementSpeed;

    // Behavioral Methods
    public void update() {
        follow();
        centerX += speedX;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

        if (Rect.intersects(r, OverworldCharacter.yellowRed)) {
            checkCollision();
        }
        

    }

    private void checkCollision() {
        if (Rect.intersects(r, OverworldCharacter.rect)|| Rect.intersects(r, OverworldCharacter.rect2)
                || Rect.intersects(r, OverworldCharacter.rect3) || Rect.intersects(r, OverworldCharacter.rect4)) {

        }
    }

    public void follow() {
        
        if (centerX < -95 || centerX > 810){
            movementSpeed = 0;
        }

        else if (Math.abs(mainCharacter.getCenterX() - centerX) < 5) {
            movementSpeed = 0;
        }

        else {

            if (mainCharacter.getCenterX() >= centerX) {
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

    public int getSpeedX() {
        return speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

}