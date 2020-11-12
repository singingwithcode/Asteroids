package main.se450.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import main.se450.collision.Collide;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.ScoreBoard;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShapeListTemp;

/**
 * Shot extends the shape Circle and is required to check itself to see if it
 * hit a Shape
 * 
 * @author Matthew Klich
 *
 */
public class Shot extends Circle {

	/**
	 * @param nLeft
	 *            The leftmost point
	 * @param nTop
	 *            The topmost point
	 * @param nRight
	 *            The rightmost point
	 * @param nBottom
	 *            The lowest point
	 * @param nX
	 *            The numerator of slope
	 * @param nY
	 *            The denominator of slope
	 * @param nRotation
	 *            The rotation speed. 0
	 * @param cColor
	 *            The Color
	 * @param iStrategy
	 *            The boarder strategy
	 * @param size
	 *            The size of the shot
	 * @param score
	 *            The score. 0
	 * @param multiplier
	 *            The amount it can break into. 0
	 * @param children
	 *            The amount of children it can have. 0
	 */
	public Shot(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor,
			IStrategy iStrategy, String size, int score, int multiplier, int children) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, size, score, multiplier, children);
	}

	/**
	 * This update overrides so we can check to see if Shot hit a Shape. We also
	 * keep track of the score, kill shapes and shots, and create more children
	 * inside.
	 */
	@Override
	public void update() {
		super.update();

		// Checking shape vs shot
		final ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();

		if (iShapeList != null) {
			Iterator<IShape> iiShapes = iShapeList.iterator();
			while (iiShapes.hasNext()) {
				IShape iShape = iiShapes.next();
				if (iShape.getMFK() == false) {
					if (Collide.collided(getLineCollection(), iShape.getLineCollection())) {

						System.out.println("Shot a shape!");

						// Score
						ScoreBoard.getScoreBoard().scored(iShape.getScore());

						// Make more shapes?
						ArrayList<IShape> ish = iShape.addChildren();
						if (!ish.isEmpty()) {
							ShapeListTemp.getShapeList().addShapes(ish);
						}

						// Mark For Kill - Shape
						iShape.setMFKTrue();

						// Mark For Kill - this Shot
						markForKill();
					}
				}
			}
		}
	}

}