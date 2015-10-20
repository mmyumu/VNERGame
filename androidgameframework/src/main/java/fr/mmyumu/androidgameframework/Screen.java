package fr.mmyumu.androidgameframework;

public abstract class Screen {
    protected final Game game;

    public Screen(Game game) {
        this.game = game;
    }

    protected boolean inBounds(Input.TouchEvent event, Rect rect) {
        return inBounds(event, rect.getTopLeft().x, rect.getTopLeft().y, rect.getWidth(), rect.getHeight());
    }

    protected boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        return event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1;
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime, float[] mvpMatrix);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void backButton();

}