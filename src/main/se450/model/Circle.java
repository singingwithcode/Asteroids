package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import main.se450.collections.LineCollection;
import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.factories.ShapeFactory;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.SoundManager;

public class Circle extends ShapeDroid{
	private Line2D line = new Line2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);

	public Circle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation,
			Color cColor, IStrategy iStrategy, String size, int score, int multiplier, int children) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, size, score, multiplier, children);
	}

	@Override
	public void draw(Graphics graphics) {
		line.setLine(getCenterX(), getCenterY(), getX1(), getY1());
		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());

		Graphics2D g2d = (Graphics2D) (graphics);

		g2d.setColor(getColor());
		g2d.draw(circle);
		g2d.draw(line);
	}

	@Override
	public void addSides(LineCollection lineCollection) {
		if (lineCollection != null) {
			lineCollection.add(new Line(getX1(), getY1(), getX2(), getY2(), getX(), getY(), getRotation(), Color.BLACK,
					getStrategy(), getSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection.add(new Line(getX2(), getY2(), getX3(), getY3(), getX(), getY(), getRotation(), Color.BLACK,
					getStrategy(), getSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection.add(new Line(getX3(), getY3(), getX4(), getY4(), getX(), getY(), getRotation(), Color.BLACK,
					getStrategy(), getSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection.add(new Line(getX4(), getY4(), getX1(), getY1(), getX(), getY(), getRotation(), Color.BLACK,
					getStrategy(), getSize(), getScore(), getMultiplier(), getChildren()));
		}
	}

	@Override
	public float getMinX() {
		return getCenterX() - getRadius();
	}

	@Override
	public float getMinY() {
		return getCenterY() - getRadius();
	}

	@Override
	public float getMaxX() {
		return getCenterX() + getRadius();
	}

	@Override
	public float getMaxY() {
		return getCenterY() + getRadius();
	}

	public float getCenterX() {
		return getMidpointX1X3();
	}

	public float getCenterY() {
		return getMidpointY1Y3();
	}

	public float getRadius() {
		return getWidth() * 0.5f; // getWidth == getHeight for circle
	}

	@Override
	public void update() {
		super.update();
	}

	/**
	 * This method adds children to the ShapeListTemp singleton since ShapeList
	 * singleton is currently in iteration. We need it here to create instances
	 * of itself because we do not know why type of iShape this is.
	 */
	@Override
	public ArrayList<IShape> addChildren() {

		ArrayList<IShape> iShapes = new ArrayList<IShape>();

		if (getSize() != "Small") {
			if (getSize().equals("Large")) {
				//Explode Sound Large
				SoundManager.getSoundManager().explodeLarge();
				for (int i = 0; i < getChildren(); i++) {
					try {
						iShapes.add(ShapeFactory.makeShape("Circle", getCenterX() - (getRadius() / 2),
								getCenterY() - (getRadius() / 2), getCenterX() + (getRadius() / 2),
								getCenterY() - (getRadius() / 2), randNum5(), randNum5(), randNum5(), getColor(),
								getStrategy(), "Medium", getScore() * getMultiplier(), getMultiplier() * 2,
								getChildren()));
					} catch (BadShapeException e) {
					} catch (UnsupportedShapeException e) {
					}
					
				}
			} else if (getSize().equals("Medium")) {
				//Explode Sound Medium
				SoundManager.getSoundManager().explode();
				for (int i = 0; i < getChildren(); i++) {
					try {
						iShapes.add(ShapeFactory.makeShape("Circle", getCenterX() - (getRadius() / 2),
								getCenterY() - (getRadius() / 2), getCenterX() + (getRadius() / 2),
								getCenterY() - (getRadius() / 2), randNum5(), randNum5(), randNum5(), getColor(),
								getStrategy(), "Small", getScore() * getMultiplier(), getMultiplier() * 2,
								0));
					} catch (BadShapeException e) {
					} catch (UnsupportedShapeException e) {
					}
				}
			} 
		} else {
			SoundManager.getSoundManager().explodeSmall();
		}
		return iShapes;
	}


	
	
	
}
