package fr.mmyumu.vnergame;

import fr.mmyumu.androidgameframework.Image;
import fr.mmyumu.androidgameframework.Music;
import fr.mmyumu.androidgameframework.Sound;
import fr.mmyumu.androidgameframework.impl.AndroidGame;

public class Assets {
    
    public static Image menu, splash, background, character, character2, character3, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, characterJump, characterDown;
    public static Image button;
    public static Sound click;
    public static Music theme;
    
    public static void load(AndroidGame androidGame) {
        // TODO Auto-generated method stub
        theme = androidGame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }
    
}