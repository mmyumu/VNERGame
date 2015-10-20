package fr.mmyumu.androidgameframework;

import android.graphics.Point;

/**
 * Rect that can be drew or used for in bound touch event
 * Created by mmyumu on 21/09/2015.
 */
public class Rect {
    private Point topLeft;
    private Point bottomLeft;
    private Point bottomRight;
    private Point topRight;

    public Rect(Point topLeft, Point bottomLeft, Point bottomRight, Point topRight) {
        this.topLeft = topLeft;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.topRight = topRight;
    }

    public Rect(int x, int y, int width, int height) {
        topLeft = new Point(x, y);
        bottomLeft = new Point(x, y + height);
        bottomRight = new Point(x + width, y + height);
        topRight = new Point(x + width, y);
    }


    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public Point getTopRight() {
        return topRight;
    }

    public int getWidth() {
        return Math.abs(topLeft.x - topRight.x);
    }

    public int getHeight() {
        return Math.abs(bottomLeft.y - topLeft.y);
    }
}
