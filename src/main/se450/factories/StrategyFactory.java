package main.se450.factories;

import main.se450.exceptions.BadStrategyException;
import main.se450.interfaces.IStrategy;
import main.se450.strategies.ShotDepreciateStrategy;
import main.se450.strategies.PassThruStrategy;
import main.se450.strategies.ReboundStrategy;

public class StrategyFactory
{
	private StrategyFactory()
	{
	}
	
	public static IStrategy makeStrategy(final String sStrategy) throws BadStrategyException
	{
		IStrategy iStrategy = null;

    	if (sStrategy.equals("PassThru"))
    	{
    		iStrategy = new PassThruStrategy();
    	}
        else if (sStrategy.equals("Rebound"))
    	{
        	iStrategy = new ReboundStrategy();
    	}
        else if (sStrategy.equals("Depreciate"))
    	{
        	iStrategy = new ShotDepreciateStrategy();
    	}
    	else
    	{
    		throw new BadStrategyException(sStrategy);
    	}
		
		return iStrategy;
	}
}
      