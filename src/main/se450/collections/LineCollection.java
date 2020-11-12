package main.se450.collections;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IShape;
import main.se450.model.Line;

public class LineCollection
{
	private float minX = Long.MAX_VALUE;// Float.MAX_VALUE/MIN_VALUE isn't reliable with Math functions
	private float minY = Long.MAX_VALUE;
	private float maxX = Long.MIN_VALUE;
	private float maxY = Long.MIN_VALUE;
	
	private ArrayList<IShape> lines = new ArrayList<IShape>();
	
	private void recalculateMinMax()
	{
		minX = Long.MAX_VALUE;// Float.MAX_VALUE/MIN_VALUE isn't reliable with Math functions
		minY = Long.MAX_VALUE;
		maxX = Long.MIN_VALUE;
		maxY = Long.MIN_VALUE;

	    Iterator<IShape> iSides = lines.iterator();
	    while (iSides.hasNext())
	    {
	    	IShape iShape = iSides.next();
	    	
	    	minX = Math.min(minX, iShape.getMinX());
	        maxX = Math.max(maxX, iShape.getMaxX());
	        minY = Math.min(minY, iShape.getMinY());
	        maxY = Math.max(maxY, iShape.getMaxY());
	    }
	}
	
	public void clear()
	{
    	lines.clear();
    	
    	recalculateMinMax();
	}
	
	public void add(final Line line)
	{
		lines.add(line);
		
    	minX = Math.min(minX, line.getMinX());
        maxX = Math.max(maxX, line.getMaxX());
        minY = Math.min(minY, line.getMinY());
        maxY = Math.max(maxY, line.getMaxY());
	}
	
	public final ArrayList<IShape> getLines()
	{
		return lines;
	}
	
	public float getMinX()
	{
        return minX;
	}

	public float getMinY()
	{
        return minY;
	}

	public float getMaxX()
	{
        return maxX;
	}

	public float getMaxY()
	{
        return maxY;
	}
}
    
      