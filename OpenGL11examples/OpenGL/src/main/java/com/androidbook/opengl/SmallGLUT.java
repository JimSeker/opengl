package com.androidbook.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class SmallGLUT {

    public static final float PI = 3.14159265358979323846f;
    
    
    ByteBuffer getByteBufferFromByteArray( byte array[]) {
	ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
	buffer.put(array);
	buffer.position(0);
	return buffer;
    }
    
    FloatBuffer getFloatBufferFromFloatArray(float array[]) {
        ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
        tempBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = tempBuffer.asFloatBuffer();
        buffer.put(array);
        buffer.position(0);
        return buffer;
    }
    
    IntBuffer getIntBufferFromIntArray( int array[]) {
	ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
	tempBuffer.order(ByteOrder.nativeOrder());
	IntBuffer buffer = tempBuffer.asIntBuffer();
	buffer.put(array);
	buffer.position(0);
	return buffer;
    }
    
    
}
