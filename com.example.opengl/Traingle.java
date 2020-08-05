package com.example.opengl;

import android.opengl.GLES20;
import android.util.Log;

import java.io.Console;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL;

public class Triangle {

    FloatBuffer vertexBuffer;
    float[] vertices = {
        0.0f,0.5f,0.0f,
        -0.5f,-0.5f,0.0f,
        0.5f,-0.5f,0.0f,
    };

    float[] color = {
      1.0f,1.0f,1.0f,1.0f
    };

    final String vertexShaderCode =
        "attribute vec4 vPosition;" +
        "void main() {" +
        "   gl_Position = vPosition;" +
        "}";

    final String fragmentShaderCode =
        "precision mediump float;" +
        "uniform vec4 vColor;" +
        "void main() {" +
        "   gl_FragColor = vColor;" +
        "}";

    int shaderProgram;

    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length*4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);

        shaderProgram  = GLES20.glCreateProgram();
        GLES20.glAttachShader(shaderProgram,vertexShader);
        GLES20.glAttachShader(shaderProgram,fragmentShader);
        GLES20.glLinkProgram(shaderProgram);
    }

    public void draw() {
        Log.e("TRIANGLE","rendering");
        GLES20.glUseProgram(shaderProgram);

        int positionAttr = GLES20.glGetAttribLocation(shaderProgram,"vPosition");
        GLES20.glEnableVertexAttribArray(positionAttr);
        GLES20.glVertexAttribPointer(positionAttr,3,GLES20.GL_FLOAT,false,0,vertexBuffer);

        int colorUniform = GLES20.glGetUniformLocation(shaderProgram,"vColor");
        GLES20.glUniform4fv(colorUniform,1,color,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertices.length/3);
        GLES20.glDisableVertexAttribArray(positionAttr);
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader,shaderCode);

        GLES20.glCompileShader(shader);
        return  shader;
    }
}
