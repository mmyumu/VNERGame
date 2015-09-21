package fr.mmyumu.androidgameframework;

public abstract class Screen {
    protected final Game game;

    public Screen(Game game) {
        this.game = game;
    }

    protected boolean inBounds(Input.TouchEvent event, Rect rect) {
        return inBounds(event, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    protected boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1) {
            return true;
        } else {
            return false;
        }
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void backButton();

}