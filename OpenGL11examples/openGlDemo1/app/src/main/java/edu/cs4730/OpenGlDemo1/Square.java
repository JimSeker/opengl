package edu.cs4730.OpenGlDemo1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

//there is both GL10 Es 1.0 and GL11 ES 1.1
//much of GL11 subclasses GL10, but some methods only take GL10.
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

/*
 * A vertex shaded square
 */
public class Square {

	private FloatBuffer mFVertexBuffer;
	private ByteBuffer mColorBuffer;
	private ByteBuffer mIndexBuffer;
	
	public Square() {
		//we are defining our square. For larger objects, we would likely read this in from a file. 
		float vertices[] = {
			-1.0f, 1.0f,
			 -1.0f, -1.0f,
			1.0f,  -1.0f,
			 1.0f,  1.0f,
		};
		byte maxColor=(byte)255;
		//we are defining colors similar to the square
		//where there are four components: red, green, blue, and alpha (transparency)
		//These map directory to our 4 vertices in the square.
		//using bytes, instead of floats is for space saving (plus floats will get converted to bytes anyway, so save both space and time)
		byte colors[] = {
			maxColor, 0, 0, maxColor,
			maxColor, 0,0, maxColor,
			maxColor,0,0,maxColor,
			maxColor,0,0,maxColor
		};
/*		byte colors[] = {
				maxColor, maxColor, 0, maxColor,
				0, maxColor,maxColor, maxColor,
				0,0,0,maxColor,
				maxColor,0,maxColor,maxColor
			};
			*/
		// this matches up the vertices to specific triangles, the first triples says vertices 0,3,1 make triangle 0, 
		byte indices[] =  {
				0, 1, 2,
				0, 2, 3
		};
		//The rest matches up everything, converts their internal java formats to the OpenGL
		//mainly makes sure the ordering of the bytes is correctly, otherwise depending on hardware, they might get reversed.
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
		vbb.order(ByteOrder.nativeOrder());
		mFVertexBuffer = vbb.asFloatBuffer();
		mFVertexBuffer.put(vertices);
		mFVertexBuffer.position(0);
		
		mColorBuffer = ByteBuffer.allocateDirect(colors.length);
		mColorBuffer.put(colors);
		mColorBuffer.position(0);
		
		mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
		mIndexBuffer.put(indices);
		mIndexBuffer.position(0);
	}
	/*
	 * method called when we want to render this square.
	 * in this case, from SquareRenderer.drawFrame()
	 */
	public void draw(GL10 gl) {  
		//tell openGL how the vertices are ordering their faces
		//this is both for efficiently so openGL can ignore the "backside" and not attempt to draw it.
		gl.glFrontFace(GL11.GL_CCW);  //counter clockwise, so any counter triangles are ignored.
		//send the buffers to the renderer
		//specific the number of elements per vertex, which there are 2.
		gl.glVertexPointer(2, GL11.GL_FLOAT, 0, mFVertexBuffer); //8
		//color buffer is added, with the size of the 4 elements
		gl.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 0, mColorBuffer); //9
		
		//finally draw the element, we the connectivity array, using triangles
		//could also be triangle lists, points or lines
		gl.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_BYTE, mIndexBuffer);
		
		//return the openGL back to the default value.
		gl.glFrontFace(GL11.GL_CCW); //11
	}
}
