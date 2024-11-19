OpenGl Examples
===========
`eclipse/` has the examples that eclipse can load (no longer updated).  Otherwise, everything uses android studio.

`OpenGL11examples/` has all the open GL 1.1 examples  No longer updated.

---

**OpenGL 2.0 examples:**

`HelloOpenGLES20` is Google’s basic framework example.  2D square and triangle with touch events to spin the triangle  API 8+ and works on android 15 devices.  

`Opengl2ex1`  uses a renderer (lessonOne from http://www.learnopengles.com/android-lesson-one-getting-started/) and mainactivity to setup everything
    NOTE: this example may fail in the emulators, but works on a physical device.  verified it works on android 15 devices.

`Opengl2ex2`  extends a GLsurfaceView to setup everything.  Again, use the Lessonone renderer.  verified works on android 15 devices.

---

**OpenGL 3.X examples:**

intended to get you started in OpenGL 3.0, but don't provide any depth of coverage.  
$\color{red}{\textsf{NOTE, in android 15 some of these examples now fail and I do not know why.}}$
$\color{red}{\textsf{They work correctly on a pixel4a running android 13, but then fail to run pixel 7 and 8 devices. }}$  
$\color{red}{\textsf{likely there is a error in library, but android states, opengl is no longer under active developement.   }}$
$\color{red}{\textsf{Vulkan is the new graphics package and opengl is on the way out.  Use at your own risk or maybe v2 instead.}}$

`HelloOpenGLES30` is Google’s basic framework example from 2.0, updated to OpenGL ES 3.0.  2D square and triangle with touch events to spin the triangle.  API 18+

`Opengl3ex1` is based on Opengl2ex1, with references changed to GLES30.  Note this may fail in emulators.  Works on android 15, but using the older v2 code, instead of the commmented out v3 code.  the v3 code works on android 13, but not android 15 devices.  
  
`Opengl3ex2` is based on Opengl2ex2, again with references changed. Note this may fail in emulators.  Works on android 15, but using the older v2 code, instead of the commmented out v3 code.  the v3 code works on android 13, but not android 15 devices.  

`Opengl3ex3` is from the OpenGL ES 3.0 Programing Guide, which can be found here: http://opengles-book.com/samplecode.html

`OpenGL30Cube` is Cube that spins and uses touchlistener to move up/down left/right.

`OpenGL30Pryamid` is Pyramid that spins and uses touchlistener to move up/down left/right.

`OpenGL30CubeTextureView` is the same renderer as OpenGL30Cube, except this uses a TextureView instead of surfaceView.  The TextureView has to setup all the openGL stuff, which takes a chunk of code to do.

`HelloOpenGLES31` is Google’s basic framework example from 2.0, updated to OpenGL ES 3.1.  2D square and triangle with touch events to spin the triangle.  The setEGLContextClientVersion(3);  maybe wrong, since I could not find how to set it for 3.1. All other entries are set correctly.  API 21+   Also doesn't work on android 15 on a pixel 7 or 8.  does work on pixel 4a with android 13.

`HelloOpenGLES32` is Google’s basic framework example from 2.0, updated to OpenGL ES 3.2.  2D square and triangle with touch events to spin the triangle.  The setEGLContextClientVersion(3);  maybe wrong, since I could not find how to set it for 3.2.  All other entries are set correctly.  API 24+   Also doesn't work on android 15 on a pixel 7 or 8.  does work on pixel 4a with android 13.
 
---

These are example code for University of Wyoming, Cosc 4730 Mobile Programming course and cosc 4735 Advance Mobile Programing course. 
All examples are for Android.
