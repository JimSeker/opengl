package com.androidbook.opengl;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TriangleSmallGLUT extends SmallGLUT {

    
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;

    
    TriangleSmallGLUT(float size) {
        if (size != 1.0f) {
            for (int vertex = 0; vertex < vertices.length; vertex++ ) {
                vertices[vertex] *= size;
            }
        }
        
        mVertexBuffer = getFloatBufferFromFloatArray(vertices);
        mColorBuffer = getFloatBufferFromFloatArray(colors);
    }
    
    void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl.glNormal3f(0f, 0f, 1f);
        
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
        
    }
    
    void drawColorful(GL10 gl) {
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4,GL10.GL_FLOAT, 0, mColorBuffer);
        draw(gl);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
    
    float[] colors = {
            1f, 0, 0, 1f,
            0, 1f,0, 1f,
            0,0,1f,1f
    };
    
    float[] vertices = {
            -0.559016994f, 0, 0,
            0.25f, 0.5f, 0f,
            0.25f, -0.5f, 0f
    };

}
