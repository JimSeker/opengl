package book.SolarSystem;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import java.lang.Math;

class SolarSystemRenderer implements GLSurfaceView.Renderer 
{
    public SolarSystemRenderer() 
    {
    	mPlanet=new Planet(10,10,1.0f, 1.0f);
    }
    
    public void onDrawFrame(GL10 gl) 
    {
         gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
         gl.glClearColor(0.0f,0.0f,0.0f,1.0f);
         gl.glMatrixMode(GL10.GL_MODELVIEW);
         gl.glLoadIdentity();
    	
         gl.glTranslatef(0.0f,(float)Math.sin(mTransY), -4.0f);
    	
         gl.glRotatef(mAngle, 1, 0, 0);
         gl.glRotatef(mAngle, 0, 1, 0);
    		
         mPlanet.draw(gl);
    	     
         mTransY+=.075f; 
         mAngle+=.4;
    }

	
	public void onSurfaceChanged(GL10 gl, int width, int height) {
	     gl.glViewport(0, 0, width, height);
	
	     /*
	      * Set our projection matrix. This doesn't have to be done
	      * each time we draw, but usually a new projection needs to
	      * be set when the viewport is resized.
	      */
	     
	 	float aspectRatio;
		float zNear =.1f;
		float zFar =1000f;
		float fieldOfView = 30.0f/57.3f;
		float	size;
		
		gl.glEnable(GL10.GL_NORMALIZE);
		
		aspectRatio=(float)width/(float)height;				//h/w clamps the fov to the height, flipping it would make it relative to the width
		
		//Set the OpenGL projection matrix
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		size = zNear * (float)(Math.tan((double)(fieldOfView/2.0f)));
		gl.glFrustumf(-size, size, -size/aspectRatio, size /aspectRatio, zNear, zFar);
		
		//Make the OpenGL modelview matrix the default
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	    /*
	     * By default, OpenGL enables features that improve quality
	     * but reduce performance. One might want to tweak that
	     * especially on software renderer.
	     */
	    gl.glDisable(GL10.GL_DITHER);
	
	    /*
	     * Some one-time OpenGL initialization can be made here
	     * probably based on features of this particular context
	     */
	     gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
	             GL10.GL_FASTEST);
	
	     gl.glEnable(GL10.GL_CULL_FACE);
	     gl.glCullFace(GL10.GL_BACK);
	     gl.glShadeModel(GL10.GL_SMOOTH);
	     gl.glEnable(GL10.GL_DEPTH_TEST);
	}

	private Planet mPlanet;
	private float mTransY;
	private float mAngle;
}
