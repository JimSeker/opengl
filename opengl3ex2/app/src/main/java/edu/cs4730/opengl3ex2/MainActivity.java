package edu.cs4730.opengl3ex2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

/*
 * simple example where the GLSurfaceView is an extended class and the bulk
 * of the code is in there and the render of course.
 *
 * Note, there is no XML layout for this example.  It's all done in onCreate, myGlSurfaceView and the render.
 *
 * The render is from http://www.learnopengles.com/android-lesson-one-getting-started/ with GLES 3.0 mods.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (detectOpenGLES30()) {
            //so we know it a opengl 3.0 and use our extended GLsurfaceview.
            setContentView(new myGlSurfaceView(this));
        } else {
            // This is where you could create an OpenGL ES 2.0 and/or 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.
            Log.e("HelloTriangle", "OpenGL ES 3.0 not supported on device. Exiting...");
            finish();
        }

        // 1. Hide the Action Bar if you are using a theme that includes one
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //2. Configure the window for immersive mode
        Window window = getWindow();
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());

        if (controller != null) {
            // Hide both the status bar and the navigation bar
            controller.hide(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.navigationBars());

            // Set the behavior to "behavior system bars transient hopes"
            // This allows the user to swipe from the edge to temporarily reveal the bars
            controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }


    }

    private boolean detectOpenGLES30() {
        ActivityManager am =
            (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x30000);
    }
}
