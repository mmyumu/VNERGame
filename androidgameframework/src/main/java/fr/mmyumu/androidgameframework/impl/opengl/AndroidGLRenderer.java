package fr.mmyumu.androidgameframework.impl.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import fr.mmyumu.androidgameframework.impl.AndroidGame;

public class AndroidGLRenderer implements Renderer {

    private static final String TAG = "AndroidGLRenderer";

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private long mLastTime;
    private AndroidGame game;

    public AndroidGLRenderer(AndroidGame game) {
        this.game = game;
        mLastTime = System.currentTimeMillis() + 100;
    }

    public void onPause() {
        /* Do stuff to pause the renderer */
    }

    public void onResume() {
        /* Do stuff to resume the renderer */
        mLastTime = System.currentTimeMillis();
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // Get the current time
        long now = System.currentTimeMillis();

        // We should make sure we are valid and sane
        if (mLastTime > now) return;

        // Get the amount of time the last frame took.
        long elapsed = now - mLastTime;

        // Set the camera position (View matrix)
//        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.setLookAtM(mViewMatrix, 0,
                0, 0, 6,   //eye
                0, 0, 0,   //center
                0, 1, 0);  //UP *remove your -1

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // Update our example
        game.getCurrentScreen().update(elapsed);
        game.getCurrentScreen().paint(elapsed, mMVPMatrix);

        // Render our example
//        render(mtrxProjectionAndView);

        // Save the current time to see how long it took <img src="http://androidblog.reindustries.com/wp-includes/images/smilies/icon_smile.gif" alt=":)" class="wp-smiley"> .
        mLastTime = now;

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float screenRatio = (float) width / height;

        Log.d(TAG, "##### ratio=" + screenRatio + " (width/height = " + width + "/" + height + ")");

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
//        Matrix.frustumM(mProjectionMatrix, 0, -1, 1, -1, 1, 3, 7);
//        Matrix.orthoM(mProjectionMatrix, 0, 0, 1000, 0, 1000, 3, 7);

        Matrix.orthoM(mProjectionMatrix, 0,
                0, width, // left, right
                height, 0,           // bottom, top        <----Solution is invert bottom with top
                -1, 10);        // near, far
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }
}