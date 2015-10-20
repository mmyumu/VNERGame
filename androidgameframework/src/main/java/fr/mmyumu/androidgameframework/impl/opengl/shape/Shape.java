package fr.mmyumu.androidgameframework.impl.opengl.shape;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Abstract class to define a Shape in OpenGL ES
 * Created by mmyumu on 18/10/2015.
 */
public abstract class Shape {
    protected final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";
    protected final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    // Set color with red, green, blue and alpha (opacity) values
    protected int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    // Use to access and set the view transformation
    private int mMVPMatrixHandle;

    protected abstract float[] getCoords();

    protected abstract int getCoordsPerVertex();

    protected abstract float[] getColor();
}
