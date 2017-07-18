package edu.cs4730.opengl30cubetextureview;

import android.support.v7.app.AppCompatActivity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.util.Log;

/*
 *  This is a example of a cube that rotates and moves.
 *
 *  The code here, simply makes sure that opengl 3.0 is available and starts up
 *  the TextureView class I extended.
 */

public class MainActivity extends AppCompatActivity {

    myGLTextureView mGLTextureView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (detectOpenGLES30()) {
            //so we know it a opengl 3.0 and use our extended GLTextureView
            mGLTextureView = new myGLTextureView(this);
            mGLTextureView.setRenderer(new myRenderer(this));
            setContentView(mGLTextureView);

        } else {
            // This is where you could create an OpenGL ES 2.0 and/or 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.
            Log.e("openglcube", "OpenGL ES 3.0 not supported on device.  Exiting...");
            finish();

        }

    }

    private boolean detectOpenGLES30() {
        ActivityManager am =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x30000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //stop the animation.
        mGLTextureView.setPaused(true);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        if (mGLTextureView.isRunning == false) {
            //everything is closed down and we have problem.  Restart
            mGLTextureView = new myGLTextureView(this);
            mGLTextureView.setRenderer(new myRenderer(this));
            setContentView(mGLTextureView);
        }
        //start up the animation.
        mGLTextureView.setPaused(false);

    }
}
