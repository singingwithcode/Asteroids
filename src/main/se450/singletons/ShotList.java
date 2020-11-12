package main.se450.singletons;

import java.util.ArrayList;

import main.se450.model.Shot;

/**
 * This class is a singleton used to keep track of all the shots made by
 * PLayerShip
 * 
 * @author Matt Klich
 */
public class ShotList {
	private static ShotList shotList = null;
	private ArrayList<Shot> iShots = null;

	//private double shotradius = ConfigManager.getConfigManager().getShotD() / 2;
	//private double shotspeed = ConfigManager.getConfigManager().getShotSpeed();

	static {
		shotList = new ShotList();
	}

	// So it cannot be instantiated. Sets up a new ArrayList
	private ShotList() {
		iShots = new ArrayList<Shot>();
	}

	/**
	 * To be able to access
	 * 
	 * @return Instance of this class
	 */
	public final static ShotList getShotList() {
		return shotList;
	}

	/*
	 * To get all the shots that have happened
	 * 
	 * @return ArrayList<Shot> returns the list of shots
	 */
	public final ArrayList<Shot> getShots() {
		return iShots;
	}

	/**
	 * Creates each shot and adds it to the list
	 * 
	 * @param xLocation
	 *            The x value location
	 * @param yLocation
	 *            The y value location
	 * @param xTrajectory
	 *            The x value of where it is heading
	 * @param yTrajectory
	 *            The y value of where it is heading
	 */
	/*public void createShot(float xLocation, float yLocation, float xTrajectory, float yTrajectory) {
		iShots.add(new Shot((float) (xLocation - shotradius), (float) (yLocation - shotradius),
				(float) (xLocation + shotradius), (float) (yLocation + shotradius), (float) (shotspeed * xTrajectory),
				(float) (shotspeed * yTrajectory), 0.0f, Color.WHITE, new ShotDepreciateStrategy(), "", 0, 0, 0));
	}
*/

	/**
	 * Adds a new shot to the singleton
	 * @param newShot the new Shot
	 */
	public void addShot(Shot newShot) {
		iShots.add(newShot);
	}

}
