package edu.cs4730.opengl2ex2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/*
 * simple example where the GLSurfaceView is an extended class and the bulk
 * of the code is in there and the render of course.
 * 
 * Note, there is no xml layout for this example.  It's all done in onCreate, myGlSurfaceView and the render.
 * 
 * The render is from http://www.learnopengles.com/android-lesson-one-getting-started/
 */

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

        // Turn off the window's title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
		super.onCreate(savedInstanceState);
		//the bulk of the code the make sure we are using opengl2 is in the extended glsurfaceview.
		setContentView(new myGlSurfaceView(this));
	}

}
