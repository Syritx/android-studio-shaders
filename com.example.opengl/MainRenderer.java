package com.example.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MainRenderer implements Renderer {

    Triangle triangle;

    public void onDrawFrame(GL10 arg) {
        GLES20.glClearColor(0.0f,0,0.0f,1);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        triangle.draw();
    }

    public void onSurfaceChanged(GL10 arg, int a, int b) {

    }

    public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
        triangle = new Triangle();
    }
}
