package fr.mmyumu.vnergame.mainmenu;

import android.util.Log;

import java.util.List;

import fr.mmyumu.androidgameframework.Game;
import fr.mmyumu.androidgameframework.Input.TouchEvent;
import fr.mmyumu.androidgameframework.Screen;
import fr.mmyumu.androidgameframework.impl.opengl.shape.Square;
import fr.mmyumu.vnergame.overworld.OverworldScreen;

public class MainMenuScreen extends Screen {
    private static final String TAG = "MainMenuScreen";

    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        Log.d(TAG, "### update=" + deltaTime);
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                Log.d(TAG, "### touchEventUP");
                if (inBounds(event, Shapes.start)) {
                    Log.d(TAG, "### startInBound");
                    game.setScreen(new OverworldScreen(game));
                }
            }
        }
    }

    @Override
    public void paint(float deltaTime, float[] mvpMatrix) {
        Square square = new Square(Shapes.start);
        square.draw(mvpMatrix);

//        for (int i = 0; i < 18; i++) {
//            Point p1 = new Point(100 * i, 0);
//            Point p2 = new Point(100 * i, 100);
//            Point p3 = new Point(100 * i + 100, 0);
//            Triangle triangle = new Triangle(p1, p2, p3);
//            triangle.draw(mvpMatrix);
//        }
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