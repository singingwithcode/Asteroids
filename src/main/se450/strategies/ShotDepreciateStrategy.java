package main.se450.strategies;

import main.se450.interfaces.IStrategy;
import main.se450.model.Shape;
import main.se450.singletons.ConfigManager;

/**
 * This strategy was created for Shots. We want them to pass through and die out
 * after the ShotLifetime value.
 * 
 * @author Matt Klich
 *
 */
public class ShotDepreciateStrategy implements IStrategy {

	public ShotDepreciateStrategy() {
	}

	@Override
	public void execute(Shape shape) {

		// Pass Thru
		PassThruStrategy pts = new PassThruStrategy();
		pts.execute(shape);

		// Depreciate
		int count = shape.getCount();
		shape.setCount(count + 1);
		if (count >= ConfigManager.getConfigManager().getShotLifetime()) {
			shape.markForKill();
		}
	}
}