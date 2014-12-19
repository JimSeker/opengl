package com.androidbook.opengl;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLDebugHelper;
import android.opengl.GLU;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BasicGLCube extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAndroidSurface = new BasicGLSurfaceView(this);

        setContentView(mAndroidSurface);
    }

    private class BasicGLSurfaceView extends SurfaceView implements
            SurfaceHolder.Callback {
        SurfaceHolder mAndroidHolder;

         BasicGLSurfaceView(Context context) {
            super(context);
            mAndroidHolder = getHolder();
            mAndroidHolder.addCallback(this);
            mAndroidHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);

        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                int height) {
        }

        public void surfaceCreated(SurfaceHolder holder) {
            mGLThread = new BasicGLThread(this);
            
            mGLThread.start();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mGLThread != null) {
                mGLThread.requestStop();
            }
        }
    }
    
    BasicGLThread mGLThread;
    private class BasicGLThread extends Thread {
        SurfaceView sv;
        BasicGLThread(SurfaceView view) {
            sv = view;
        }
        
        private boolean mDone = false;
        public void run() {
            initEGL();
            initGL();
            
            CubeSmallGLUT cube = new CubeSmallGLUT(3);
            mGL.glMatrixMode(GL10.GL_MODELVIEW);
            mGL.glLoadIdentity();
            GLU.gluLookAt(mGL, 0, 0, 6f, 0, 0, 0, 0, 1, 0f);
            while (!mDone) {
                mGL.glClear(GL10.GL_COLOR_BUFFER_BIT| GL10.GL_DEPTH_BUFFER_BIT);
                mGL.glRotatef(1f, 1f, 1f, 1f);
                
                mGL.glColor4f(1f, 0f, 0f, 1f);

                cube.drawSimpleCube(mGL);
                                
                mEGL.eglSwapBuffers(mGLDisplay, mGLSurface);
            }
        }
        
        public void requestStop() {
            mDone = true;
            try {
                join();
            } catch (InterruptedException e) {
                Log.e("GL", "failed to stop gl thread", e);
            }
            
            cleanupGL();
        }
        
        private void cleanupGL() {
            mEGL.eglMakeCurrent(mGLDisplay, EGL10.EGL_NO_SURFACE,
                    EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            mEGL.eglDestroySurface(mGLDisplay, mGLSurface);
            mEGL.eglDestroyContext(mGLDisplay, mGLContext);
            mEGL.eglTerminate(mGLDisplay);

            Log.i("GL", "GL Cleaned up");
        }
        
        public void initGL( ) {
            int width = sv.getWidth();
            int height = sv.getHeight();
            mGL.glViewport(0, 0, width, height);
            mGL.glMatrixMode(GL10.GL_PROJECTION);
            mGL.glLoadIdentity();
            float aspect = (float) width/height;
            GLU.gluPerspective(mGL, 45.0f, aspect, 1.0f, 30.0f);
             mGL.glClearColor(0.5f,0.5f,0.5f,1);
             
             // the only way to draw primitives with OpenGL ES
             mGL.glEnableClientState(GL10.GL_VERTEX_ARRAY);
             
             // some rendering options
             mGL.glShadeModel(GL10.GL_SMOOTH);
             mGL.glEnable(GL10.GL_DEPTH_TEST);
             //mGL.glEnable(GL10.GL_CULL_FACE);

            Log.i("GL", "GL initialized");
        }
        
        public void initEGL() {
            mEGL = (EGL10) GLDebugHelper.wrap(EGLContext.getEGL(),
                    GLDebugHelper.CONFIG_CHECK_GL_ERROR
                            | GLDebugHelper.CONFIG_CHECK_THREAD,  null);
            
            mGLDisplay = mEGL.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

            int[] curGLVersion = new int[2];
            mEGL.eglInitialize(mGLDisplay, curGLVersion);

            Log.i("GL", "GL version = " + curGLVersion[0] + "."
                    + curGLVersion[1]);

            EGLConfig[] configs = new EGLConfig[1];
            int[] num_config = new int[1];
            mEGL.eglChooseConfig(mGLDisplay, mConfigSpec, configs, 1,
                    num_config);
            mGLConfig = configs[0];

            mGLSurface = mEGL.eglCreateWindowSurface(mGLDisplay, mGLConfig, sv
                    .getHolder(), null);

            mGLContext = mEGL.eglCreateContext(mGLDisplay, mGLConfig,
                    EGL10.EGL_NO_CONTEXT, null);

            mEGL.eglMakeCurrent(mGLDisplay, mGLSurface, mGLSurface, mGLContext);
            mGL = (GL10) GLDebugHelper.wrap(mGLContext.getGL(),
                    GLDebugHelper.CONFIG_CHECK_GL_ERROR
                            | GLDebugHelper.CONFIG_CHECK_THREAD
                            | GLDebugHelper.CONFIG_LOG_ARGUMENT_NAMES, null);
        }
        
        // main OpenGL variables
        GL10 mGL;
        EGL10 mEGL;
        EGLDisplay mGLDisplay;
        EGLConfig mGLConfig;
        EGLSurface mGLSurface;
        EGLContext mGLContext;
        int[] mConfigSpec = { EGL10.EGL_RED_SIZE, 5, 
                EGL10.EGL_GREEN_SIZE, 6, EGL10.EGL_BLUE_SIZE, 5, 
                EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE };
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    SurfaceView mAndroidSurface;
}
