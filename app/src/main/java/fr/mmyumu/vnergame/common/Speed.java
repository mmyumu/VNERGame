package fr.mmyumu.vnergame.common;

/**
 * Represent the speed (x and y components) in a plan
 * Created by mmyumu on 03/10/2015.
 */
public class Speed {
    double x;
    double y;

    public Speed() {
        super();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void invertX() {
        x = -x;
    }

    public void invertY() {
        y = -y;
    }
}
