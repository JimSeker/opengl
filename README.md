OpenGl Examples
===========
eclipse/ has the examples that eclipse can load (no longer updated).  Otherwise, everything uses android studio.

<b>OpenGL11examples</b> has all the open GL 1.1 examples

<h3>OpenGL 2.0 examples</h3>
<b>HelloOpenGLES20</b> is Google’s basic framework example.  2D square and triangle with touch events to spin the triangle  API 8+

<b>Opengl2ex1</b>  uses a renderer (lessonOne from http://www.learnopengles.com/android-lesson-one-getting-started/) and mainactivity to setup everything
    NOTE: this example may fail in the emulators, but works on a physical device.

<b>Opengl2ex2</b>  extends a GLsurfaceView to setup everything.  Again, use the Lessonone renderer.

<h3>OpenGL 3.0 examples</h3> intended to get you started in OpenGL 3.0, but don't provide any depth of coverage.

<b>HelloOpenGLES30</b> is Google’s basic framework example from 2.0, updated to OpenGL ES 3.0.  2D square and triangle with touch events to spin the triangle.  API 18+

<b>Opengl3ex1</b> is based on Opengl2ex1, with references changed to GLES30.  Note this may fail in emulators.
  
<b>Opengl3ex2</b> is based on Opengl2ex2, again with references changed. Note this may fail in emulators.

<b>Opengl3ex3</b> is from the OpenGL ES 3.0 Programing Guide, which can be found here: http://opengles-book.com/samplecode.html

<b>OpenGL30Cube</b> is Cube that spins and uses touchlistener to move up/down left/right.

<b>OpenGL30Pryamid</b> is Pyramid that spins and uses touchlistener to move up/down left/right.

<b>OpenGL30CubeTextureView</b> is the same renderer as OpenGL30Cube, except this uses a TextureView instead of surfaceView.  The TextureView has to setup all the openGL stuff, which takes a chunk of code to do.

<b>HelloOpenGLES31</b> is Google’s basic framework example from 2.0, updated to OpenGL ES 3.1.  2D square and triangle with touch events to spin the triangle.  The setEGLContextClientVersion(3);  maybe wrong, since I could not find how to set it for 3.1. All other entries are set correctly.  API 21+

<b>HelloOpenGLES32</b> is Google’s basic framework example from 2.0, updated to OpenGL ES 3.2.  2D square and triangle with touch events to spin the triangle.  The setEGLContextClientVersion(3);  maybe wrong, since I could not find how to set it for 3.2.  All other entries are set correctly.  API 24+


These are example code for University of Wyoming, Cosc 4730 Mobile Programming course.
All examples are for Android.
