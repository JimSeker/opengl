package edu.cs4730.opengl3ex1;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.util.Log;
import android.opengl.GLSurfaceView;
import android.view.Window;

/*
 * This example use openGL v3.0
 *    this is based off of opengl2ex1 since 3.0 is backward compatible.
 *
 *  The render code is based off
 *   This code is based off of http://www.learnopengles.com/android-lesson-one-getting-started/
 *   but has been updated to use gl30 methods.
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
        if ( detectOpenGLES30() )
        {
// Tell the surface view we want to create an OpenGL ES 3.0-compatible
// context, and set an OpenGL ES 3.0-compatible renderer.
            mGLSurfaceView.setEGLContextClientVersion ( 3 );
            // Set the renderer to our demo renderer, defined below.
            mGLSurfaceView.setRenderer(new LessonOneRenderer());
        }
        else
        {
            // This is where you could create an OpenGL ES 2.0 and/or 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.
            Log.e("HelloTriangle", "OpenGL ES 3.0 not supported on device. Exiting...");
            finish();
        }
        setContentView ( mGLSurfaceView );

    }



    private boolean detectOpenGLES30()
    {
        ActivityManager am =
                ( ActivityManager ) getSystemService ( Context.ACTIVITY_SERVICE );
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return ( info.reqGlEsVersion >= 0x30000 );
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
        mGLSurfaceView.onPause();
    }

}
