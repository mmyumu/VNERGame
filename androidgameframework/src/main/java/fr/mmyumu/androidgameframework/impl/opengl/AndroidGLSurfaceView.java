package fr.mmyumu.androidgameframework.impl.opengl;

import android.opengl.GLSurfaceView;

import fr.mmyumu.androidgameframework.impl.AndroidGame;

public class AndroidGLSurfaceView extends GLSurfaceView {

    private final AndroidGLRenderer renderer;

    public AndroidGLSurfaceView(AndroidGame game) {
        super(game);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        renderer = new AndroidGLRenderer(game);
        setRenderer(renderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        super.onPause();
        renderer.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        renderer.onResume();
    }

}