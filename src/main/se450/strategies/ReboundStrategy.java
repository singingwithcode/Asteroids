package main.se450.strategies;

import main.se450.interfaces.IStrategy;
import main.se450.model.Shape;
import main.se450.singletons.DisplayManager;

public class ReboundStrategy implements IStrategy
{
	public ReboundStrategy()
	{
	}

	@Override
	public void execute(Shape shape) 
	{
  		float nMinX = shape.getMinX();
  		float nMaxX = shape.getMaxX();
  		
  		float nMinY = shape.getMinY();
  		float nMaxY = shape.getMaxY();
		
		if (nMinX < 0.0f)
		{
			shape.setX(Math.abs(shape.getX()));
		}
		else if (nMaxX > DisplayManager.getDisplayManager().getWidth())
		{
			shape.setX(-Math.abs(shape.getX()));
		}
		
		if (nMinY < 0.0f)
		{
			shape.setY(Math.abs(shape.getY()));
		}
		else if (nMaxY > DisplayManager.getDisplayManager().getHeight())
		{
			shape.setY(-Math.abs(shape.getY()));
		}
	}
}