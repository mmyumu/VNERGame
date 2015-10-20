package fr.mmyumu.vnergame.overworld;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.mmyumu.androidgameframework.Game;
import fr.mmyumu.androidgameframework.Graphics;
import fr.mmyumu.androidgameframework.Image;
import fr.mmyumu.androidgameframework.Input.TouchEvent;
import fr.mmyumu.androidgameframework.Screen;
import fr.mmyumu.vnergame.Assets;
import fr.mmyumu.vnergame.SampleGame;
import fr.mmyumu.vnergame.mainmenu.MainMenuScreen;

public class OverworldScreen extends Screen {

    private static final String TAG = "OverworldScreen";

    // Variable Setup
    private OverworldCharacter mainCharacter;
    private GameState state = GameState.Ready;
    private Paint paint;
    private Paint paint2;
    private Image character;
    private ArrayList<OverworldTile> tiles = new ArrayList<>();

    public OverworldScreen(Game game) {
        super(game);

        // Initialize game objects here
        Double centerX = OverworldConstants.TILE_WIDTH * 1.5;
        Double centerY = OverworldConstants.TILE_HEIGHT * 1.5;

        mainCharacter = new OverworldCharacter(new Point(centerX.intValue(), centerY.intValue()));

        character = Assets.character;

        loadMap();

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

    }

//    public static Background getBg1() {
//        return bg1;
//    }

//    public static Background getBg2() {
//        return bg2;
//    }

    public OverworldCharacter getMainCharacter() {
        return mainCharacter;
    }

    private void loadMap() {
        List<String> lines = new ArrayList<>();
        int width = 0;

        Scanner scanner = new Scanner(SampleGame.map);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // no more lines to read
            if (line == null) {
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }

        for (int j = 0; j < lines.size(); j++) {
            String line = lines.get(j);
            for (int i = 0; i < width; i++) {
                if (i < line.length()) {
                    char ch = line.charAt(i);
                    OverworldTile t = new OverworldTile(mainCharacter, i, j, ch);
                    tiles.add(t);
                }

            }
        }

    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready) {
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    @Override
    public void paint(float deltaTime, float[] mvpMatrix) {
        Graphics g = game.getGraphics();

//        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
//        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
        paintTiles(g);

        // First draw the game elements.
        g.drawImage(character, mainCharacter.getCenter().x - (OverworldConstants.TILE_WIDTH / 2), mainCharacter.getCenter().y - (OverworldConstants.TILE_HEIGHT / 2));

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }
    }

    private void updateReady(List touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        Log.d(TAG, "##### OverworldScreen.updateRunning deltaTime=" + deltaTime);
        // This is identical to the update() method from our Unit 2/3 game.

        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED) {
                mainCharacter.setMoveTarget(new Point(event.x, event.y));
            } else if (event.type == TouchEvent.TOUCH_UP) {
                mainCharacter.setMoveTarget(null);
            }
        }
        moveMainCharacter();
        animate();
    }

    private void moveMainCharacter() {
        mainCharacter.computeMovement();
        mainCharacter.applyHorizontalMovement();
        checkHorizontalCollision();
        mainCharacter.applyVerticalMovement();
        checkVerticalCollision();
    }

    private void checkHorizontalCollision() {
        int distance = computeHorizontalCollision();
        Log.d(TAG, "##### distance=" + distance);
        mainCharacter.getCenter().x += distance;
    }

    private void checkVerticalCollision() {
        int distance = computeVerticalCollision();
        Log.d(TAG, "##### distance=" + distance);
        mainCharacter.getCenter().y += distance;
    }

    private int computeHorizontalCollision() {
        if (mainCharacter.getSpeed().getX() < 0) {
            return computeLeftCollision();
        } else if (mainCharacter.getSpeed().getX() > 0) {
            return -computeRightCollision();
        }

        return 0;
    }

    private int computeVerticalCollision() {
        if (mainCharacter.getSpeed().getY() < 0) {
            return computeTopCollision();
        } else if (mainCharacter.getSpeed().getY() > 0) {
            return -computeBottomCollision();
        }
        return 0;
    }

    private int computeLeftCollision() {
        int maxCollisionDistance = 0;
        for (OverworldTile tile : tiles) {
            if (tile.getType() == OverworldTile.Type.WALL && Rect.intersects(tile.getRect(), mainCharacter.getLeftHitBox())) {
                Log.d(TAG, "##### LEFT COLLISION with tile =" + tile.getRect().toShortString());
                int collisionDistance = mainCharacter.computeCollisionFromLeft(tile.getRect());
                if (collisionDistance > maxCollisionDistance) {
                    maxCollisionDistance = collisionDistance + 1;
                }
            }
        }
        return maxCollisionDistance;
    }

    private int computeRightCollision() {
        int maxCollisionDistance = 0;
        for (OverworldTile tile : tiles) {
            if (tile.getType() == OverworldTile.Type.WALL && Rect.intersects(tile.getRect(), mainCharacter.getRightHitBox())) {
                Log.d(TAG, "##### RIGHT COLLISION with tile =" + tile.getRect().toShortString());
                int collisionDistance = mainCharacter.computeCollisionFromRight(tile.getRect());
                if (collisionDistance > maxCollisionDistance) {
                    maxCollisionDistance = collisionDistance + 1;
                }
            }
        }
        return maxCollisionDistance;
    }

    private int computeTopCollision() {
        int maxCollisionDistance = 0;
        for (OverworldTile tile : tiles) {
            if (tile.getType() == OverworldTile.Type.WALL && Rect.intersects(tile.getRect(), mainCharacter.getTopHitBox())) {
                Log.d(TAG, "##### TOP COLLISION with tile =" + tile.getRect().toShortString());
                int collisionDistance = mainCharacter.computeCollisionFromTop(tile.getRect());
                if (collisionDistance > maxCollisionDistance) {
                    maxCollisionDistance = collisionDistance + 1;
                }
            }
        }
        return maxCollisionDistance;
    }

    private int computeBottomCollision() {
        int maxCollisionDistance = 0;
        for (OverworldTile tile : tiles) {
            if (tile.getType() == OverworldTile.Type.WALL && Rect.intersects(tile.getRect(), mainCharacter.getBottomHitBox())) {
                Log.d(TAG, "##### BOTTOM COLLISION with tile =" + tile.getRect().toShortString());
                int collisionDistance = mainCharacter.computeCollisionFromBottom(tile.getRect());
                if (collisionDistance > maxCollisionDistance) {
                    maxCollisionDistance = collisionDistance + 1;
                }
            }
        }
        return maxCollisionDistance;
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 800, 240)) {
                    if (!inBounds(event, 0, 0, 35, 35)) {
                        resume();
                    }
                }

                if (inBounds(event, 0, 240, 800, 240)) {
                    nullify();
                    goToMenu();
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    private void updateTiles() {
        for (OverworldTile tile : tiles) {
            tile.update();
        }
    }

    private void paintTiles(Graphics g) {
        for (OverworldTile tile : tiles) {
            if (tile.getType() != OverworldTile.Type.NONE) {
                g.drawImage(tile.getType().getImage(), tile.getTileX(), tile.getTileY());
            }
        }
    }

    public void animate() {
    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
//        bg1 = null;
//        bg2 = null;
        mainCharacter = null;
        character = null;

        // Call garbage collector to clean up memory.
        System.gc();

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start.", 400, 240, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
//        g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
//        g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
//        g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
//        g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 400, 240, paint2);
        g.drawString("Tap to return.", 400, 290, paint);
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
        }
    }

    @Override
    public void resume() {
        if (state == GameState.Paused) {
            state = GameState.Running;
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        game.setScreen(new MainMenuScreen(game));

    }

    enum GameState {
        Ready, Running, Paused, GameOver
    }
}