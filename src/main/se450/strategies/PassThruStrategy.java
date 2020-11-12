package main.se450.strategies;

import main.se450.interfaces.IStrategy;
import main.se450.model.Shape;
import main.se450.singletons.DisplayManager;

public class PassThruStrategy implements IStrategy {

	public PassThruStrategy() {
	}

	@Override
	public void execute(Shape shape) {
		// Check X's
		float maxX = shape.getMaxX();
		float minX = shape.getMinX();

		if (maxX < 0.0f) {
			shape.translateX(-minX + DisplayManager.getDisplayManager().getWidth());
		} else if (minX > DisplayManager.getDisplayManager().getWidth()) {
			shape.translateX(-maxX);
		}

		// Check Y's
		float maxY = shape.getMaxY();
		float minY = shape.getMinY();

		if (maxY < 0.0f) {
			shape.translateY(-minY + DisplayManager.getDisplayManager().getHeight());
		} else if (minY > DisplayManager.getDisplayManager().getHeight()) {
			shape.translateY(-maxY);
		}
	}
}