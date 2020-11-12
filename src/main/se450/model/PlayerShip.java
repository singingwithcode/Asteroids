package main.se450.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import main.se450.collision.Collide;
import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.factories.ShotFactory;
import main.se450.interfaces.IPlayObserve;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.keyboard.Keyboard;
import main.se450.singletons.ConfigManager;
import main.se450.singletons.ScoreBoard;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShotList;
import main.se450.singletons.SoundManager;
import main.se450.utilities.TimeDelay;

/**
 * The PlayerShip class is an extension of Ship but contains all the methods for
 * the Player. This class has been constructed so it is possible to have a
 * multi_player game, although not fully ready.
 * 
 * @author Matt Klich
 *
 */
public class PlayerShip extends Ship implements IPlayObserve {

	// We pass this through because it may be different for another player
	private double shotspeed = 0;
	private float lr = 0;
	private double rtMax = 0;
	private double ftMax = 0;
	// If our shield is active
	private Boolean shield = false;

	/**
	 * Constructor for PlayerShip
	 * 
	 * @param nLeft
	 *            The leftmost point of PlayerShip
	 * @param nTop
	 *            The topmost point of PlayerShip
	 * @param nRight
	 *            The rightmost point of PlayerShip
	 * @param nBottom
	 *            The lowest point of PlayerShip
	 * @param nX
	 *            The numerator of slope
	 * @param nY
	 *            The denominator of slope
	 * @param nRotation
	 *            The rotation speed of PlayerShip. Typically 0
	 * @param cColor
	 *            The Color of PlayerShip
	 * @param iStrategy
	 *            The boarder strategy of PlayerShip
	 * @param shotspeed
	 *            The speed we pass Shot
	 * @param ft
	 *            The forward thrust speed
	 * @param rt
	 *            The reverse thrust speed
	 * @param lr
	 *            The rotation speed controlled by player
	 * @param size
	 *            The size of the shot
	 * @param score
	 *            The score. Typically 0
	 * @param multiplier
	 *            The amount a ship can break into. Typically 0
	 * @param children
	 *            The amount of children PlayerShip can have. Typically 0
	 */
	public PlayerShip(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation,
			Color cColor, IStrategy iStrategy, double shotspeed, double ft, double rt, double lr, String size,
			int score, int multiplier, int children) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, size, score, multiplier, children);
		this.shotspeed = shotspeed;
		this.ftMax = ft;
		this.lr = (float) lr;
		this.rtMax = rt;
	}

	/**
	 * The thrusts() is used to give PlayerShip velocity. For a reverse thrust,
	 * please pass a negated number
	 * 
	 * @param fVal
	 *            is the amount of thrust that should be given
	 */
	private void thrust(float fVal) {
		float xB = getMidpointX1X2();// x tip of ship
		float xA = getMidpointX1X3();// x bottom of ship
		float yB = getMidpointY1Y2();// y tip of ship
		float yA = getMidpointY1Y3();// y bottom of ship

		float xDiff = xB - xA;
		float yDiff = yB - yA;

		float fX = 0.0f;
		float fY = 0.0f;
		if (xDiff == 0.0f) {
			fY += fVal * Math.signum(yDiff);
		} else if (yDiff == 0.0f) {
			fX += fVal * Math.signum(xDiff);
		} else {
			float ffDiff = Math.abs(xDiff) + Math.abs(yDiff);
			fX += fVal * Math.sin(Math.toRadians(90 * xDiff / ffDiff));
			fY += fVal * Math.sin(Math.toRadians(90 * yDiff / ffDiff));
		}

		// Use fX & fY to update your existing X&Y velocity
		setX(getX() + fX);
		setY(getY() + fY);
	}

	/**
	 * The shipRotate() rotates PLayerShip using the config threshold and player
	 * keyboard observer. Please negate lr if you want a left turn.
	 * 
	 * @param lr
	 *            The threshold of ho wmuch to rotate
	 */
	private void shipRotate(double lr) {

		float midX = getMidpointX1X3();
		float midY = getMidpointY1Y3();

		translateXY(-midX, -midY);

		float radians = (float) Math.toRadians(lr);
		float sinR = (float) Math.sin(radians);
		float cosR = (float) Math.cos(radians);

		float xPrime1 = (float) ((getX1() * cosR) - (getY1() * sinR));
		float yPrime1 = (float) ((getY1() * cosR) + (getX1() * sinR));

		float xPrime2 = (float) ((getX2() * cosR) - (getY2() * sinR));
		float yPrime2 = (float) ((getY2() * cosR) + (getX2() * sinR));

		float xPrime3 = (float) ((getX3() * cosR) - (getY3() * sinR));
		float yPrime3 = (float) ((getY3() * cosR) + (getX3() * sinR));

		float xPrime4 = (float) ((getX4() * cosR) - (getY4() * sinR));
		float yPrime4 = (float) ((getY4() * cosR) + (getX4() * sinR));

		setX1(midX + xPrime1);
		setX2(midX + xPrime2);
		setX3(midX + xPrime3);
		setX4(midX + xPrime4);
		setY1(midY + yPrime1);
		setY2(midY + yPrime2);
		setY3(midY + yPrime3);
		setY4(midY + yPrime4);
	}

	/**
	 * This is used to stop the shift in it's path
	 */
	private void stop() {
		setX(0);
		setY(0);
	}

	/**
	 * This is used to randomly make PlayerShip jump around within view
	 */
	private void hyperSpace() {

		double heightShip = ConfigManager.getConfigManager().getShipHeight();
		double widthShip = ConfigManager.getConfigManager().getShipWidth();
		double height = ConfigManager.getConfigManager().getHeight() - 80 - (heightShip * 3);
		double width = ConfigManager.getConfigManager().getWidth() - 30 - (widthShip * 3);

		translateXY((float) randNum(width, widthShip) - getX1(), (float) randNum(height, heightShip) - getY1());

	}

	/**
	 * This is used to generate a random int with the given constraints. Method is public for testing. 
	 * 
	 * @param max
	 *            This is the max of the number but need to take into account
	 *            the size of the ship
	 * @param sizeForShip
	 *            This is the total size of the ship
	 * @return we return a random int
	 */
	public int randNum(double max, double sizeForShip) {
		int sizeOfShip = (int) sizeForShip;
		Random random = new Random();
		int ret = random.nextInt((int) (max - (sizeOfShip * 2)) + sizeOfShip);
		return ret;
	}

	/**
	 * This method creates a shot at PlayerShips location and uses a velocity
	 * algorithm to send it on it's way. The shot is added to the list of shots
	 * for review.
	 * 
	 * @param fVal
	 *            The shot speed
	 * @throws UnsupportedShapeException 
	 * @throws BadShapeException 
	 */
	private void createShot(float fVal) throws BadShapeException, UnsupportedShapeException {
		float xB = getMidpointX1X2();// x tip of ship
		float xA = getMidpointX1X3();// x bottom of ship
		float yB = getMidpointY1Y2();// y tip of ship
		float yA = getMidpointY1Y3();// y bottom of ship

		float xDiff = xB - xA;
		float yDiff = yB - yA;

		float fX = 0.0f;
		float fY = 0.0f;
		if (xDiff == 0.0f) {
			fY += fVal * Math.signum(yDiff);
		} else if (yDiff == 0.0f) {
			fX += fVal * Math.signum(xDiff);
		} else {
			float ffDiff = Math.abs(xDiff) + Math.abs(yDiff);
			fX += fVal * Math.sin(Math.toRadians(90 * xDiff / ffDiff));
			fY += fVal * Math.sin(Math.toRadians(90 * yDiff / ffDiff));
		}

		//ShotList.getShotList().createShot(getMidpointX1X2(), getMidpointY1Y2(), fX, fY);
		ShotList.getShotList().addShot(ShotFactory.makeShot(getMidpointX1X2(), getMidpointY1Y2(), fX, fY));
	}

	/**
	 * This method overrides above Update() after they are completed. Here we
	 * check to see shapes vs PlayerShip. If a PlayerShip is hit, game ends. We
	 * also handle the shield and game friction in this iteration.
	 */
	@Override
	public void update() {
		super.update();

		// Checking Shield
		if (shield == false && ScoreBoard.getScoreBoard().getIfGameOver() == false) {
			// Checking Shape vs PlayerShip
			final ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
			if (iShapeList != null) {
				Iterator<IShape> iiShapes = iShapeList.iterator();
				while (iiShapes.hasNext()) {
					IShape iShape = iiShapes.next();
					if (Collide.collided(getLineCollection(), iShape.getLineCollection())) {
						System.out.println("Game Over");
						ScoreBoard.getScoreBoard().setGameOverTrue();
						ScoreBoard.getScoreBoard().update();
						Keyboard.stopObserving();
						SoundManager.getSoundManager().gameOver();
					}
				}
			}
		}
		// Applying friction
		friction();
	}

	/**
	 * This is a public method so a thread can turn off the shield when it is
	 * time.
	 */
	public void setShieldOff() {
		shield = false;
	}

	/**
	 * This method is overriding our IPlayObserve interface to get notified if
	 * the keyboard state has changed. All keyboard controls on ship are below.
	 */
	@Override
	public void eventOccured(String arg) {
		if (arg == "Left") {
			shipRotate(-lr);
		} else if (arg == "Right") {
			shipRotate(lr);
		}
		if (arg == "ForwardThrust") {
			thrust((float) ftMax);
			SoundManager.getSoundManager().forwardThrust();
		} else if (arg == "ReverseThrust") {
			thrust(-(float) rtMax);
		}
		if (arg == "Fire") {
			try {
				createShot((float) shotspeed);
			} catch (BadShapeException | UnsupportedShapeException e) {
			}
			SoundManager.getSoundManager().fire();
		}
		if (arg == "HyperSpace") {
			hyperSpace();
		}
		if (arg == "Stop") {
			stop();
		}
		if (arg == "Shield") {
			shield = true;
			System.out.println("Shield is on");
			new Thread(new TimeDelay(this)).start();
		}
	}
}