package fr.mmyumu.vnergame;

import fr.mmyumu.androidgameframework.Image;
import fr.mmyumu.androidgameframework.Music;
import fr.mmyumu.androidgameframework.Sound;
import fr.mmyumu.androidgameframework.impl.AndroidGame;

public class Assets {
    
    public static Image menu;
    public static Image splash;
    public static Image background;
    public static Image character;
    public static Image tiledirt;
    public static Image tilegrass;
    public static Image tilewall;
    public static Image button;
    public static Sound click;
    public static Music theme;
    
    public static void load(AndroidGame androidGame) {
        theme = androidGame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }
    
}