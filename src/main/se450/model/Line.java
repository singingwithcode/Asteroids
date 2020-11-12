package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;

public class Line extends Shape
{
	private Line2D line = new Line2D.Float(0.0f,0.0f,0.0f,0.0f);
	
	public Line(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy, String size, int score, int multiplier, int children)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, size, score, multiplier, children);
	}
	
	@Override
	public void draw(Graphics graphics) 
	{
  		line.setLine(getX1(), getY1(), getX3(), getY3());
		
  		Graphics2D g2d = (Graphics2D)(graphics);
  		
  		g2d.setColor(getColor());
  		g2d.draw(line);
	}
	
	@Override
	public float getMinX()
	{
		return Math.min(getX1(), getX3());
	}

	@Override
	public float getMinY()
	{
		return Math.min(getY1(), getY3());
	}
	
	@Override
	public float getMaxX()
	{
		return Math.max(getX1(), getX3());
	}

	@Override
	public float getMaxY()
	{
		return Math.max(getY1(), getY3());
	}

	@Override
	public LineCollection getLineCollection() {
		return null;
	}

	@Override
	public ArrayList<IShape> addChildren() {
		return null;
	}
}
    
      