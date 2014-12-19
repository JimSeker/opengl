package edu.cs4730.OpenGlDemo1;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.WindowManager;

public class OpenGlDemo1Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GLSurfaceView view = new GLSurfaceView(this);
        view.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);  //fix config error, but no idea what this does.
        view.setRenderer(new SquareRenderer(true));
        setContentView(view);
    }
}