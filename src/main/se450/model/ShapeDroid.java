package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;

public abstract class ShapeDroid extends Shape
{
    private LineCollection lineCollection = new LineCollection();
    
	//Read only pattern
	public ShapeDroid(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy, String size, int score, int multiplier, int children)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, size, score, multiplier, children);

        createLines();
    }

    @Override
    public void update()
    {
        super.update();
        
        createLines();
    }
    
    private void createLines()
    {
    	lineCollection.clear();

    	addSides(lineCollection);
    }
    
    public abstract void addSides(LineCollection lineCollection);

	@Override
	public void draw(Graphics graphics) 
	{
    	Iterator<IShape> iSides = lineCollection.getLines().iterator();
        while (iSides.hasNext())
        {
            iSides.next().draw(graphics);
        }
    }
	
	@Override
	public float getMinX()
	{
        return lineCollection.getMinX();
	}

	@Override
	public float getMinY()
	{
        return lineCollection.getMinY();
	}

	@Override
	public float getMaxX()
	{
        return lineCollection.getMaxX();
	}

	@Override
	public float getMaxY()
	{
        return lineCollection.getMaxY();
	}
	
	@Override
	public LineCollection getLineCollection()
	{
		return lineCollection;
	}
	

}
    