package edu.cs4730.OpenGlDemo1;
import javax.microedition.khronos.egl.EGL10; //1
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView; //2
import java.lang.Math;

public class SquareRenderer implements GLSurfaceView.Renderer {
	private boolean mTranslucentBackground;
	private Square mSquare;
	private float mTransY;
	private float mAngle;

	public SquareRenderer(boolean useTranslucentBackground)	{
		mTranslucentBackground = useTranslucentBackground;
		mSquare = new Square(); //3
	}
	
	public void onDrawFrame(GL10 gl) {//4
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);//5
		gl.glMatrixMode(GL10.GL_MODELVIEW); //6
		gl.glLoadIdentity(); //7
		gl.glTranslatef(0.0f,(float)Math.sin(mTransY), -3.0f); //8
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); //9
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		mSquare.draw(gl); //10
		mTransY += .075f;
	}
	
	public void onSurfaceChanged(GL10 gl, int width, int height) {//11
		gl.glViewport(0, 0, width, height); //12
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION); //13
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10); //14
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {//15
		gl.glDisable(GL10.GL_DITHER); //16
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, //17
				GL10.GL_FASTEST);
		if (mTranslucentBackground) {//18
			gl.glClearColor(0,0,0,0);
		} else {
			gl.glClearColor(1,1,1,1);
		}
		gl.glEnable(GL10.GL_CULL_FACE); //19
		gl.glShadeModel(GL10.GL_SMOOTH); //20
		gl.glEnable(GL10.GL_DEPTH_TEST); //21
	}

}
