package edu.cs4730.opengl2ex1;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;

/*
 * This example use openGL v2.0  
 *   It creates a render, instead of extending the GLSurfaceView 
 *         see ex2 for and extended GLsurfaceView
 *         
 *   This code is based off of http://www.learnopengles.com/android-lesson-one-getting-started/
 *   
 *   Note, there is no xml layout for this example.  It's all done in onCreate and the render.
 *         
 */

public class MainActivity extends Activity {

	/** Hold a reference to our GLSurfaceView */
	private GLSurfaceView mGLSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Turn off the window's title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		mGLSurfaceView = new GLSurfaceView(this);
		// Check if the system supports OpenGL ES 2.0.
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
		if (supportsEs2)
		{
			// Request an OpenGL ES 2.0 compatible context.
			mGLSurfaceView.setEGLContextClientVersion(2);

			// Set the renderer to our demo renderer, defined below.
			mGLSurfaceView.setRenderer(new LessonOneRenderer());
		}
		else
		{
			// This is where you could create an OpenGL ES 1.x compatible
			// renderer if you wanted to support both ES 1 and ES 2.
			return;
		}
		setContentView(mGLSurfaceView);
	}


	@Override
	protected void onResume(){
		// The activity must call the GL surface view's onResume() on activity onResume().
		super.onResume();
		  mGLSurfaceView.onResume();
	}
	@Override
	protected void onPause()
	{
		// The activity must call the GL surface view's onPause() on activity onPause().
		super.onPause();
		//mGLSurfaceView.onPause();
	} 

}
