package fr.mmyumu.vnergame.overworld;

import android.graphics.Rect;

import fr.mmyumu.androidgameframework.Image;
import fr.mmyumu.vnergame.Assets;

public class OverworldTile {

    private Type type;
    private int tileX;
    private int tileY;
    private int speedX;
    private OverworldCharacter mainCharacter;
    //    private Background bg = OverworldScreen.getBg1();
    private Rect rect;

    public OverworldTile(OverworldCharacter mainCharacter, int x, int y, char typeChar) {
        this.mainCharacter = mainCharacter;
        this.tileX = x * OverworldConstants.TILE_WIDTH;
        this.tileY = y * OverworldConstants.TILE_HEIGHT;
        this.type = Type.getType(typeChar);

        if (this.type == null) {
            throw new OverworldException("The type " + typeChar + " is not a registered Overworld tile.");
        }

        rect = new Rect();
    }

    public void update() {
//        speedX = 5;
//        tileX += speedX;
        rect.set(tileX, tileY, tileX + OverworldConstants.TILE_WIDTH, tileY + OverworldConstants.TILE_HEIGHT);

        checkCollision();
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Type getType() {
        return type;
    }

    public void checkCollision() {
        if (type == Type.WALL) {

            if (mainCharacter.getHitBox().intersect(rect)) {
//                checkLeftCollision();
//                checkRightCollision();
//                checkUpCollision();
//                checkDownCollision();
                mainCharacter.collide(this);
            }
//            if (Rect.intersects(hitBox, rect)) {
//                mainCharacter.collide();
//
//                mainCharacter.getDirections().contains(OverworldCharacter.Direction.NORTH);
//                mainCharacter.setCenterX(tileX + OverworldConstants.TILE_HEIGHT / 2);
//                mainCharacter.setSpeedX(0);
//            }
        }
    }

    private void checkLeftCollision() {
        if (mainCharacter.getHitBox().left < rect.right) {
            mainCharacter.collideLeft(rect);
        }
    }

    private void checkRightCollision() {
        if (mainCharacter.getHitBox().right < rect.left) {
            mainCharacter.collideRight(rect);
        }
    }

    private void checkUpCollision() {
        if (mainCharacter.getHitBox().top < rect.bottom) {
            mainCharacter.collideUp(rect);
        }
    }

    private void checkDownCollision() {
        if (mainCharacter.getHitBox().bottom < rect.top) {
            mainCharacter.collideDown(rect);
        }
    }


    public enum Type {
        // TODO: add a collision attribute to the tiles?
        NONE(' ', null), DIRT('D', Assets.tiledirt), GRASS('G', Assets.tilegrass), WALL('W', Assets.tilewall);

        private char typeChar;
        private Image image;

        Type(char typeChar, Image image) {
            this.typeChar = typeChar;
            this.image = image;
        }

        public static Type getType(char typeChar) {
            for (Type type : Type.values()) {
                if (type.typeChar == typeChar) {
                    return type;
                }
            }
            return null;
        }

        public Image getImage() {
            return image;
        }
    }

}