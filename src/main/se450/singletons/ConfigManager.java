package main.se450.singletons;

import java.awt.Color;
import java.util.ArrayList;

import main.se450.utilities.Extractor;

/**
 * This class is used to set up the entire game. It uses the JSON configuration
 * file. Here we are using a singleton pattern.
 * 
 * @author Matthew Klich
 */
public class ConfigManager {

	private static ConfigManager configManager = null;

	private int framespersecond = 0;
	private int repeatkeyspeed = 0;
	private int width = 0;
	private int height = 0;
	private int shapes = 0;
	private int shipwidth = 0;
	private int shipheight = 0;
	private double shotspeed = 0.0;
	private double shotdiameter = 0.0;
	private int shotlifetime = 0;
	private double forwardthrust = 0.0;
	private double reversethrust = 0.0;
	private double friction = 0.0;
	private double leftright = 0.0;
	private Color color = null;
	private String borders = "";

	static {
		configManager = new ConfigManager();
	}

	// Cannot instantiate
	private ConfigManager() {
	}

	/**
	 * To be able to access the ConfigManager
	 * @return ConfigManager instance of this one
	 */
	public final static ConfigManager getConfigManager() {
		return configManager;
	}

	/**
	 * Sets the configuration from the List
	 * @param strings is and ArrayList of strings with the config data
	 */
	public void setConfiguration(ArrayList<String> strings) {
		framespersecond = Extractor.extractInteger(strings.get(0));
		repeatkeyspeed = Extractor.extractInteger(strings.get(1));
		width = Extractor.extractInteger(strings.get(2));
		height = Extractor.extractInteger(strings.get(3));
		shapes = Extractor.extractInteger(strings.get(4));
		shipwidth = Extractor.extractInteger(strings.get(5));
		shipheight = Extractor.extractInteger(strings.get(6));
		shotspeed = Extractor.extractDouble(strings.get(7));
		shotdiameter = Extractor.extractDouble(strings.get(8));
		shotlifetime = Extractor.extractInteger(strings.get(9));
		forwardthrust = Extractor.extractDouble(strings.get(10));
		reversethrust = Extractor.extractDouble(strings.get(11));
		friction = Extractor.extractDouble(strings.get(12));
		leftright = Extractor.extractDouble(strings.get(13));
		color = Extractor.extractColor(strings.get(14));
		borders = strings.get(15);
	}

	public double getNumShapes() {
		return shapes;
	}

	public double getFriction() {
		return friction;
	}

	public double getShotLifetime() {
		return shotlifetime;
	}

	public double getLR() {
		return leftright;
	}

	public double getShotD() {
		return shotdiameter;
	}

	public double getForwardThrust() {
		return forwardthrust;
	}

	public double getReverseThrust() {
		return reversethrust;
	}

	public double getShotSpeed() {
		return shotspeed;
	}

	public int getShipWidth() {
		return shipwidth;
	}

	public int getShipHeight() {
		return shipheight;
	}

	public String getBoarderS() {
		return borders;
	}

	public int getFPS() {
		return framespersecond;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRKS() {
		return repeatkeyspeed;
	}

	public Color getColor() {
		return color;
	}
}
