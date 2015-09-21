package fr.mmyumu.androidgameframework;

/**
 * Rect that can be drew or used for in bound touch event
 * Created by mmyumu on 21/09/2015.
 */
public class Rect {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rect(int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
