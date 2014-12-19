package com.droidnova.android.games.vortex;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class VortexView extends GLSurfaceView {
    private static final String LOG_TAG = VortexView.class.getSimpleName();
    private VortexRenderer _renderer;
    private float _x = 0;
    private float _y = 0;
    
    public VortexView(Context context) {
        super(context);
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);  //fix for error No config chosen
        _renderer = new VortexRenderer();
        setRenderer(_renderer);
    }
    
    public boolean onTouchEvent(final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _x = event.getX();
            _y = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            final float xdiff = (_x - event.getX());
            final float ydiff = (_y - event.getY());
            queueEvent(new Runnable() {
                public void run() {
                    _renderer.setXAngle(_renderer.getXAngle() + ydiff);
                    _renderer.setYAngle(_renderer.getYAngle() + xdiff);
                }
            });
            _x = event.getX();
            _y = event.getY();
        }
        return true;
    }
}