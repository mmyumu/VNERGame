package fr.mmyumu.vnergame.mainmenu;

import android.graphics.Color;

import java.util.List;

import fr.mmyumu.androidgameframework.Game;
import fr.mmyumu.androidgameframework.Graphics;
import fr.mmyumu.androidgameframework.Screen;
import fr.mmyumu.androidgameframework.Input.TouchEvent;
import fr.mmyumu.vnergame.Assets;
import fr.mmyumu.vnergame.GameScreen;
import fr.mmyumu.vnergame.overworld.OverworldScreen;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();



        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, Shapes.start)) {
                    game.setScreen(new GameScreen(game));
                }
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu, 0, 0);

        g.drawRect(Shapes.start, Color.CYAN);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}