/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.cs4730.helloopengles31;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES31;

/**
 * A two-dimensional square for use as a drawn object in OpenGL ES 3.1
 */
public class Square {

    private final String vertexShaderCode =
        // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        "#version 310 es 			  \n"
            + "uniform mat4 uMVPMatrix;     \n"
            + "in vec4 vPosition;           \n"
            + "void main()                  \n"
            + "{                            \n"
            + "   gl_Position = uMVPMatrix * vPosition;  \n"
            + "}                            \n";

    private final String fragmentShaderCode =
        "#version 310 es		 			          	\n"
            + "precision mediump float;					  	\n"
            + "uniform vec4 vColor;	 			 		  	\n"
            + "out vec4 fragColor;	 			 		  	\n"
            + "void main()                                  \n"
            + "{                                            \n"
            + "  fragColor = vColor;                    	\n"
            + "}                                            \n";

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
        -0.5f, 0.5f, 0.0f,   // top left
        -0.5f, -0.5f, 0.0f,   // bottom left
        0.5f, -0.5f, 0.0f,   // bottom right
        0.5f, 0.5f, 0.0f}; // top right

    private final short drawOrder[] = {0, 1, 2, 0, 2, 3}; // order to draw vertices

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    float color[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};

    /**
     * Sets up the drawing object data for use in an OpenGL ES context.
     */
    public Square() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 4 bytes per float)
            squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 2 bytes per short)
            drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        // prepare shaders and OpenGL program
        int vertexShader = MyGLRenderer.loadShader(
            GLES31.GL_VERTEX_SHADER,
            vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(
            GLES31.GL_FRAGMENT_SHADER,
            fragmentShaderCode);

        mProgram = GLES31.glCreateProgram();             // create empty OpenGL Program
        GLES31.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
        GLES31.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
        GLES31.glLinkProgram(mProgram);                  // create OpenGL program executables
    }

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * @param mvpMatrix - The Model View Project matrix in which to draw
     *                  this shape.
     */
    public void draw(float[] mvpMatrix) {
        // Add program to OpenGL environment
        GLES31.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES31.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES31.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES31.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES31.GL_FLOAT, false,
            vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES31.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES31.glUniform4fv(mColorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES31.glGetUniformLocation(mProgram, "uMVPMatrix");
        MyGLRenderer.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES31.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        MyGLRenderer.checkGlError("glUniformMatrix4fv");

        // Draw the square
        GLES31.glDrawElements(
            GLES31.GL_TRIANGLES, drawOrder.length,
            GLES31.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES31.glDisableVertexAttribArray(mPositionHandle);
    }

}