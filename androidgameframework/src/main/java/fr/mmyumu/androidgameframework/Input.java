package fr.mmyumu.androidgameframework;

import java.util.List;

public interface Input {

//    enum TouchEvent {
//    TOUCH_DOWN, TOUCH_UP, TOUCH_DRAGGED, TOUCH_HOLD
//    }
    class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;

        public int type;
        public int x, y;
        public int pointer;
    }

    boolean isTouchDown(int pointer);

    int getTouchX(int pointer);

    int getTouchY(int pointer);

    List<TouchEvent> getTouchEvents();
}