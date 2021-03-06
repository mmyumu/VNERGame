package fr.mmyumu.vnergame.overworld;

import android.graphics.Rect;
import android.util.Log;

import fr.mmyumu.androidgameframework.Image;
import fr.mmyumu.vnergame.Assets;

public class OverworldTile {
    private static final String TAG = "OverworldTile";
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

        rect = new Rect(tileX, tileY, tileX + OverworldConstants.TILE_WIDTH, tileY + OverworldConstants.TILE_HEIGHT);
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public void update() {
        if(type == Type.WALL) {
//            checkCollision();
        }
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public Type getType() {
        return type;
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