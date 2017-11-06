package edu.cs4730.opengl3ex2;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/*
 * simple example where the GLSurfaceView is an extended class and the bulk
 * of the code is in there and the render of course.
 *
 * Note, there is no xml layout for this example.  It's all done in onCreate, myGlSurfaceView and the render.
 *
 * The render is from http://www.learnopengles.com/android-lesson-one-getting-started/ with GLES 3.0 mods.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if ( detectOpenGLES30() )   {
            //so we know it a opengl 3.0 and use our extended GLsurfaceview.
            setContentView(new myGlSurfaceView(this));
        } else {
            // This is where you could create an OpenGL ES 2.0 and/or 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.
            Log.e("HelloTriangle", "OpenGL ES 3.0 not supported on device. Exiting...");
            finish();
        }

    }

    private boolean detectOpenGLES30()
    {
        ActivityManager am =
                ( ActivityManager ) getSystemService ( Context.ACTIVITY_SERVICE );
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return ( info.reqGlEsVersion >= 0x30000 );
    }

    /*
 * turns on immersive mode.  to get the navigation back, the user needs to swipe up
 * from the bottom of the screen.
 */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);  //this line is api 19+
            } else {
                getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
        }
    }
}
