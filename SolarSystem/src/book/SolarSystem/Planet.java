package book.SolarSystem;

import java.util.*;
import java.nio.*;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES11;


public class Planet 
{
    FloatBuffer m_VertexData;
    FloatBuffer m_NormalData;
    FloatBuffer m_ColorData;

    float m_Scale;
    float m_Squash;
    float m_Radius;
    int m_Stacks, m_Slices;

    public Planet(int stacks, int slices, float radius, float squash) 
    {
        this.m_Stacks = stacks;
        this.m_Slices = slices;
        this.m_Radius = radius;
        this.m_Squash = squash;
        
        init(m_Stacks,m_Slices,radius,squash,"dummy");
    }
    
    private void init(int stacks,int slices, float radius, float squash, String textureFile)		
    {
         float[] vertexData;
         float[] colorData;                              
         float colorIncrement=0f;                                                                                    
    	
         float blue=0f;
         float red=1.0f;
         int vIndex=0;				//vertex index
         int cIndex=0;				//color index
    						
         m_Scale=radius;						
         m_Squash=squash;
    	
         colorIncrement=1.0f/(float)stacks;                             

         {
	    	m_Stacks = stacks;
	    	m_Slices = slices;
    		    				
	    	//vertices
    		
            vertexData = new float[ 3*((m_Slices*2+2) * m_Stacks)];	      
    		
            //color data
    		
            colorData = new float[ (4*(m_Slices*2+2) * m_Stacks)];      	
		
            int phiIdx, thetaIdx;
    		
            //latitude
    		
            for(phiIdx=0; phiIdx < m_Stacks; phiIdx++)	               		
            {
            	//starts at -90 degrees (-1.57 radians) goes up to +90 degrees (or +1.57 radians)
    			
            	//the first circle
     	             	             	             	             	             	             	       
            	float phi0 = (float)Math.PI * ((float)(phiIdx+0) * (1.0f/(float)(m_Stacks)) - 0.5f);	
    			
            	//the next, or second one.
	             	             	             	             	             	             	      
            	float phi1 = (float)Math.PI * ((float)(phiIdx+1) * (1.0f/(float)(m_Stacks)) - 0.5f);	

            	float cosPhi0 = (float)Math.cos(phi0);	                     
            	float sinPhi0 = (float)Math.sin(phi0);
            	float cosPhi1 = (float)Math.cos(phi1);
            	float sinPhi1 = (float)Math.sin(phi1);
    			
            	float cosTheta, sinTheta;
    			
            	//longitude
    			
            	for(thetaIdx=0; thetaIdx < m_Slices; thetaIdx++)               
            	{
	    	         //increment along the longitude circle each "slice"
	    				                                                                   
	    	         float theta = (float) (-2.0f*(float)Math.PI * ((float)thetaIdx) * (1.0/(float)(m_Slices-1)));			
	    	         cosTheta = (float)Math.cos(theta);		
	    	         sinTheta = (float)Math.sin(theta);
	    				
	    	        //we're generating a vertical pair of points, such 
	    	        //as the first point of stack 0 and the first point of stack 1
	    	        //above it. This is how TRIANGLE_STRIPS work, 
	    	        //taking a set of 4 vertices and essentially drawing two triangles
	    	        //at a time. The first is v0-v1-v2 and the next is v2-v1-v3. Etc.
	    				
	    	        //get x-y-z for the first vertex of stack
	    				
	    	         vertexData[vIndex+0] = m_Scale*cosPhi0*cosTheta; 	
	         	     vertexData[vIndex+1] = m_Scale*(sinPhi0*m_Squash); 
	        	     vertexData[vIndex+2] = m_Scale*(cosPhi0*sinTheta); 
	    				
	    	         vertexData[vIndex+3] = m_Scale*cosPhi1*cosTheta;
	    	         vertexData[vIndex+4] = m_Scale*(sinPhi1*m_Squash); 
	    	         vertexData[vIndex+5] = m_Scale*(cosPhi1*sinTheta); 
	
	    	         colorData[cIndex+0] = (float)red;			  	
	    	         colorData[cIndex+1] = (float)0f;
	    	         colorData[cIndex+2] = (float)blue;
	          	     colorData[cIndex+4] = (float)red;
	         	     colorData[cIndex+5] = (float)0f;
	         	     colorData[cIndex+6] = (float)blue;
	         	     colorData[cIndex+3] = (float)1.0;
	    	         colorData[cIndex+7] = (float)1.0;
	
	    	         cIndex+=2*4; 	             	             	             	             	             	
	          	     vIndex+=2*3; 				           
	            }
    			
            	blue+=colorIncrement;				         
            	red-=colorIncrement;
		    			
		    	// create a degenerate triangle to connect stacks and maintain winding order
			     	           	             	             	             	             	             
		    	vertexData[vIndex+0] = vertexData[vIndex+3] = vertexData[vIndex-3];
		    	vertexData[vIndex+1] = vertexData[vIndex+4] = vertexData[vIndex-2]; 
		    	vertexData[vIndex+2] = vertexData[vIndex+5] = vertexData[vIndex-1];
		     }
			
		 }
         
         m_VertexData = makeFloatBuffer(vertexData); 	             
         m_ColorData = makeFloatBuffer(colorData);
    }


    public void draw(GL10 gl) 
    {
 	
    	gl.glFrontFace(GL10.GL_CW);					
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, m_VertexData);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	
    	gl.glColorPointer(4, GL10.GL_FLOAT, 0, m_ColorData);		

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (m_Slices+1)*2*(m_Stacks-1)+2);
    }
    
    protected static FloatBuffer makeFloatBuffer(float[] arr) 
    {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }
}
