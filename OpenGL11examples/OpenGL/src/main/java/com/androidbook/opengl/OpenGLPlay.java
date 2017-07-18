package com.androidbook.opengl;


public class OpenGLPlay extends Menu {


   @Override
    void prepareMenu() {
        addMenuItem("1. GL Triangle", BasicGL.class);
        addMenuItem("2. GL Cube", BasicGLCube.class);
        addMenuItem("3. Lit Cube", SimpleLitGLCube.class);
        addMenuItem("4. Display FPS", SimpleFPSDisplay.class);
        addMenuItem("5. Texture GL", TextureGL.class);
        
        addMenuItem("6. Android 1.5 Style", AndroidOpenGL.class);
    }
}