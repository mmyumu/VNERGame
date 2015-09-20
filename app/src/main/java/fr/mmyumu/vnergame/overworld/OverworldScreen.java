package fr.mmyumu.vnergame.overworld;

import java.util.List;

import fr.mmyumu.androidgameframework.Game;
import fr.mmyumu.androidgameframework.Input.TouchEvent;
import fr.mmyumu.androidgameframework.Screen;

/**
 * Created by mmyumuE on 20/09/2015.
 */
public class OverworldScreen extends Screen {
    GameState state = GameState.Ready;

    public OverworldScreen(Game game) {
        super(game);
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

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

        // This is identical to the update() method from our Unit 2/3 game.

        // 1. All touch input is handled here:
//        int len = touchEvents.size();
//        for (int i = 0; i < len; i++) {
//            TouchEvent event = touchEvents.get(i);
//            if (event.type == TouchEvent.TOUCH_DOWN) {
//
//                if (inBounds(event, 0, 285, 65, 65)) {
//                    robot.jump();
//                    currentSprite = anim.getImage();
//                    robot.setDucked(false);
//                }
//
//                else if (inBounds(event, 0, 350, 65, 65)) {
//
//                    if (robot.isDucked() == false && robot.isJumped() == false
//                            && robot.isReadyToFire()) {
//                        robot.shoot();
//                    }
//                }
//
//                else if (inBounds(event, 0, 415, 65, 65)
//                        && robot.isJumped() == false) {
//                    currentSprite = Assets.characterDown;
//                    robot.setDucked(true);
//                    robot.setSpeedX(0);
//
//                }
//
//                if (event.x > 400) {
//                    // Move right.
//                    robot.moveRight();
//                    robot.setMovingRight(true);
//
//                }
//
//            }
//
//            if (event.type == TouchEvent.TOUCH_UP) {
//
//                if (inBounds(event, 0, 415, 65, 65)) {
//                    currentSprite = anim.getImage();
//                    robot.setDucked(false);
//
//                }
//
//                if (inBounds(event, 0, 0, 35, 35)) {
//                    pause();
//
//                }
//
//                if (event.x > 400) {
//                    // Move right.
//                    robot.stopRight();
//                }
//            }
//
//        }
//
//        // 2. Check miscellaneous events like death:
//
//        if (livesLeft == 0) {
//            state = GameState.GameOver;
//        }
//
//        // 3. Call individual update() methods here.
//        // This is where all the game updates happen.
//        // For example, robot.update();
//        robot.update();
//        if (robot.isJumped()) {
//            currentSprite = Assets.characterJump;
//        } else if (robot.isJumped() == false && robot.isDucked() == false) {
//            currentSprite = anim.getImage();
//        }
//
//        ArrayList projectiles = robot.getProjectiles();
//        for (int i = 0; i < projectiles.size(); i++) {
//            Projectile p = (Projectile) projectiles.get(i);
//            if (p.isVisible() == true) {
//                p.update();
//            } else {
//                projectiles.remove(i);
//            }
//        }
//
//        updateTiles();
//        hb.update();
//        hb2.update();
//        bg1.update();
//        bg2.update();
//        animate();
//
//        if (robot.getCenterY() > 500) {
//            state = GameState.GameOver;
//        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
//        int len = touchEvents.size();
//        for (int i = 0; i < len; i++) {
//            TouchEvent event = touchEvents.get(i);
//            if (event.type == TouchEvent.TOUCH_UP) {
//                if (inBounds(event, 0, 0, 800, 240)) {
//
//                    if (!inBounds(event, 0, 0, 35, 35)) {
//                        resume();
//                    }
//                }
//
//                if (inBounds(event, 0, 240, 800, 240)) {
//                    nullify();
//                    goToMenu();
//                }
//            }
//        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
//        int len = touchEvents.size();
//        for (int i = 0; i < len; i++) {
//            TouchEvent event = touchEvents.get(i);
//            if (event.type == TouchEvent.TOUCH_DOWN) {
//                if (inBounds(event, 0, 0, 800, 480)) {
//                    nullify();
//                    game.setScreen(new MainMenuScreen(game));
//                    return;
//                }
//            }
//        }
    }

    @Override
    public void paint(float deltaTime) {

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

    }

    enum GameState {
        Ready, Running, Paused, GameOver
    }
}
