package fr.mmyumu.androidgameframework;

import fr.mmyumu.androidgameframework.Graphics.ImageFormat;

public interface Image {
    int getWidth();

    int getHeight();

    ImageFormat getFormat();

    void dispose();
}