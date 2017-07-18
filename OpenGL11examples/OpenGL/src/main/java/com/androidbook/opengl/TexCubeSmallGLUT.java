package com.androidbook.opengl;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class TexCubeSmallGLUT extends CubeSmallGLUT {

    TexCubeSmallGLUT(float size) throws IllegalArgumentException {
	super(size);
   }
    
    FloatBuffer mCoordBuffer;
    float texCoords[] = {
	    1,0, 1,1, 0,1, 0,0,
	    1,0, 1,1, 0,1, 0,0,
	    1,0, 1,1, 0,1, 0,0,
	    1,0, 1,1, 0,1, 0,0,
	    1,0, 1,1, 0,1, 0,0,
	    1,0, 1,1, 0,1, 0,0,
    };
    
    int mTextureID;
    boolean texEnabled = false;
    void setTex (GL10 gl, Context c, int textureID, int drawableID) {
	mCoordBuffer = getFloatBufferFromFloatArray(texCoords);
	
	mTextureID = textureID;
	
	gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID);
	
	Bitmap bitmap = BitmapFactory.decodeResource(c.getResources(), drawableID);
	Bitmap bitmap256 = Bitmap.createScaledBitmap(bitmap, 256, 256, false);
	GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap256, 0);
	bitmap.recycle();
	bitmap256.recycle();
	
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);
        texEnabled = true;
        
    }
    
    void draw(GL10 gl) {
	if (texEnabled) {
	    gl.glEnable(GL10.GL_TEXTURE_2D);
	    gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID);
	    gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	    gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mCoordBuffer);
	}
	
	super.draw(gl);
	
	if (texEnabled) {
	    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	    gl.glDisable(GL10.GL_TEXTURE_2D);
	}
    }

}
