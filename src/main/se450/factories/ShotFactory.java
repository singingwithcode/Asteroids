package main.se450.factories;

import java.awt.Color;

import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.model.Shot;

import main.se450.singletons.ConfigManager;
import main.se450.strategies.ShotDepreciateStrategy;

public class ShotFactory {
	
	private static double shotradius = ConfigManager.getConfigManager().getShotD()/2;
	private static double shotspeed = ConfigManager.getConfigManager().getShotSpeed();
	
	private ShotFactory() {
	}

	public static Shot makeShot(final float xLocation, final float yLocation, final float xTrajectory, final float yTrajectory) throws BadShapeException, UnsupportedShapeException {

		return new Shot((float)(xLocation - shotradius), (float)(yLocation - shotradius), (float)(xLocation + shotradius),
				(float)(yLocation + shotradius), (float)(shotspeed * xTrajectory), (float)(shotspeed * yTrajectory), 0.0f, Color.WHITE,
				new ShotDepreciateStrategy(), "", 0, 0, 0);
	}
}
