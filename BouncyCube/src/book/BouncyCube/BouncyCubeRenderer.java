
package book.BouncyCube;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.opengl.GLSurfaceView;
import java.lang.Math;


class BouncyCubeRenderer implements GLSurfaceView.Renderer 
{
    public BouncyCubeRenderer(boolean useTranslucentBackground) 
    {
        mTranslucentBackground = useTranslucentBackground;	
        mCube = new Cube();
    }

    public void onDrawFrame(GL10 gl) 
    {
    	gl.glClearColor(0.0f,0.5f,0.5f,1.0f);
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,(float)Math.sin(mTransY), -7.0f);

        gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(mAngle, 1.0f, 0.0f, 0.0f);

        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL11.GL_COLOR_ARRAY);

        mCube.draw(gl);

        mTransY += .075f;
        mAngle+=.4;
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) 
    {
        gl.glViewport(0, 0, width, height);

        float aspectRatio;
        float zNear =.1f;
        float zFar =1000;
        float fieldOfView = 30.0f/57.3f;                                                                       
        float size;
        	
        gl.glEnable(GL10.GL_NORMALIZE);
        	
        aspectRatio=(float)width/(float)height;                       
    	    	
        gl.glMatrixMode(GL10.GL_PROJECTION);                                  
        	
        size = zNear * (float)(Math.tan((double)(fieldOfView/2.0f)));   

        gl.glFrustumf(-size, size, -size /aspectRatio,                    
                               size /aspectRatio, zNear, zFar);
        	
        gl.glMatrixMode(GL10.GL_MODELVIEW);                              
    }


    public void onSurfaceCreated(GL10 gl, EGLConfig config) 
    {

        gl.glDisable(GL11.GL_DITHER);

        gl.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT,
                 GL11.GL_FASTEST);

        if (mTranslucentBackground) 
        {
             gl.glClearColor(1,0,0,0);
        } 
        else 
        {
             gl.glClearColor(1,1,1,1);
        }
        
        gl.glEnable(GL11.GL_CULL_FACE);
        gl.glShadeModel(GL11.GL_SMOOTH);
        gl.glEnable(GL11.GL_DEPTH_TEST);
    }
    private boolean mTranslucentBackground;
    private Cube mCube;
    private float mTransY;
    private float mAngle;
}

