package main.se450.collision;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IShape;

public class Collide 
{
    public static final float EPSILON = 0.000001f;

    public synchronized static boolean collided(final LineCollection collectionA, final LineCollection collectionB)
    {
    	boolean bCollided = false;
    	if ((collectionA != null) && (collectionB != null))
    	{
    		if (boundingBoxesIntersect(collectionA, collectionB))
    		{
    			ArrayList<IShape> linesA = collectionA.getLines();
    			if (linesA != null)
    			{
    				Iterator<IShape> iiLinesA = linesA.iterator(); 
	    			while ((iiLinesA.hasNext()) && (!bCollided))
	    			{
	    				IShape lineA = iiLinesA.next();
	    				if (lineA != null)
	    				{
	    	    			ArrayList<IShape> linesB = collectionB.getLines();
	    	    			if (linesA != null)
	    	    			{
		        				Iterator<IShape> iiLinesB = linesB.iterator(); 
		    	    			while ((iiLinesB.hasNext()) && (!bCollided))
		    	    			{
		    	    				IShape lineB = iiLinesB.next();
		    	    				if (lineB != null)
		    	    				{
		    	    					bCollided = lineSegmentTouchesOrCrossesLine(lineA, lineB) && lineSegmentTouchesOrCrossesLine(lineB, lineA);
		    	    				}
		    	    			}
	    	    			}
	    				}
	    			}
    			}
    		}
    	}
    	
    	return bCollided;
    }
    
    /*quick test to determine if its worth checking all the lines*/
    private static boolean boundingBoxesIntersect(final LineCollection collectionA, final LineCollection collectionB)
    {
        return (collectionA.getMinX() <= collectionB.getMaxX() && collectionA.getMaxX() >= collectionB.getMinX() && 
        		collectionA.getMinY() <= collectionB.getMaxY() && collectionA.getMaxY() >= collectionB.getMinY());
    }
    
    private static boolean isPointOnLine(IShape lineA, float bX, float bY)
    {
        float cross = crossProduct(lineA.getMaxX() - lineA.getMinX(),
	        					   lineA.getMaxY() - lineA.getMinY(),
	        					   bX - lineA.getMinX(), 
	        					   bY - lineA.getMinY());
        
        return Math.abs(cross) < EPSILON;
    }

    private static boolean isPointRightOfLine(IShape lineA, float xB, float yB) 
    {
        return crossProduct(lineA.getMaxX() - lineA.getMinX(), 
        				    lineA.getMaxY() - lineA.getMinY(), 
        				    xB - lineA.getMinX(),
        				    yB - lineA.getMinY()) < 0;
    }

    private static boolean lineSegmentTouchesOrCrossesLine(IShape iShapeA, IShape iShapeB) 
    {
        return isPointOnLine(iShapeA, iShapeB.getMinX(), iShapeB.getMinY()) || 
        	   isPointOnLine(iShapeA, iShapeB.getMaxX(), iShapeB.getMaxY()) || 
        	   (isPointRightOfLine(iShapeA, iShapeB.getMinX(), iShapeB.getMinY()) ^ isPointRightOfLine(iShapeA, iShapeB.getMaxX(), iShapeB.getMaxY()));
    }


    private static float crossProduct(float xA, float yA, float xB, float yB) 
    {
        return ((xA * yB) - (xB * yA));
    }
}