package edu.cs4730.opengl2ex2;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

/*
 * simple example where the GLSurfaceView is an extended class and the bulk
 * of the code is in there and the render of course.
 *
 * Note, there is no XML layout for this example.  It's all done in onCreate, myGlSurfaceView and the render.
 *
 * The render is from http://www.learnopengles.com/android-lesson-one-getting-started/
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Turn off the window's title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        //the bulk of the code the make sure we are using opengl2 is in the extended glsurfaceview.
        setContentView(new myGlSurfaceView(this));

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return  WindowInsetsCompat.CONSUMED;
//        });
        // 1. Hide the Action Bar if you are using a theme that includes one
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // 2. Configure the window for immersive mode
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

//    /*
//     * turns on immersive mode.  to get the navigation back, the user needs to swipe up
//     * from the bottom of the screen.
//     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
//        }
//    }
}
