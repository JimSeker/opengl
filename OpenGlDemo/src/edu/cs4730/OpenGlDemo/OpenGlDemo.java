package edu.cs4730.OpenGlDemo;

import android.app.Activity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;


public class OpenGlDemo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		GLSurfaceView view = new GLSurfaceView(this);
   		view.setRenderer(new OpenGLRenderer());
   		setContentView(view);
    }
}
