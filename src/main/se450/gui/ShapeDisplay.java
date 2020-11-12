package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;

import main.se450.model.PlayerShip;
import main.se450.model.Shot;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.PlayerShipList;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShapeListTemp;
import main.se450.singletons.ShotList;


public class ShapeDisplay extends JPanel implements IObservable {

	private static final long serialVersionUID = 1L;

	public ShapeDisplay() {
	}

	@Override
	public void validateTree() {
		super.validateTree();

		Dimension dimension = getSize();

		DisplayManager.getDisplayManager().setDisplaySize(dimension.width, dimension.height);
	}

	public void paint(Graphics graphics) {
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, DisplayManager.getDisplayManager().getWidth(),
				DisplayManager.getDisplayManager().getHeight());
		
		//Draw Shapes
		final ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
		if (iShapeList != null) {
			Iterator<IShape> iiShapes = iShapeList.iterator();
			while (iiShapes.hasNext()) {
				IShape iShape = iiShapes.next();
				if (iShape != null) {
					iShape.update();
					if (iShape.getMFK() == false) {
						iShape.draw(graphics);				
					}
				}
			}
		}
		
		//Draw PlayerShip(s) 
		final ArrayList<PlayerShip> iShipList = PlayerShipList.getShipList().getShips();
		if (iShipList != null) {
			Iterator<PlayerShip> iiShapes = iShipList.iterator();
			while (iiShapes.hasNext()) {
				IShape iShape = iiShapes.next();
				if (iShape != null) {
					iShape.update();
					if (iShape.getMFK() == false) {
						iShape.draw(graphics);				
					}		
				}
			}
		}
		
		//Draw Shots
		final ArrayList<Shot> iShotList = ShotList.getShotList().getShots();
		if (iShotList != null) {
			Iterator<Shot> iiShapes = iShotList.iterator();
			while (iiShapes.hasNext()) {
				IShape iShape = iiShapes.next();
				if (iShape != null) {
					iShape.update();
					if (iShape.getMFK() == false) {
						iShape.draw(graphics);				
					}			
				}
			}
		}
		
		//Set new shape list, deleting old ones
		//Children are already added to ShapeListTemp
		final ArrayList<IShape> iOldShapeList = ShapeList.getShapeList().getShapes();
		if (iOldShapeList != null) {
			Iterator<IShape> iiShapes = iOldShapeList.iterator();
			while (iiShapes.hasNext()) {
				IShape iShape = iiShapes.next();
				if (iShape != null) {
					//Check to see if mark for kill
					if (iShape.getMFK() == false) {
						//Add since not marked for kill
						ShapeListTemp.getShapeList().addShape(iShape);
					}			
				}
			}
		}

		//Update/switch Lists
		ShapeList.getShapeList().clearShapes();
		ShapeList.getShapeList().addShapes(ShapeListTemp.getShapeList().getShapes());
		ShapeListTemp.getShapeList().clearShapes();
	}

	@Override
	public void update() {
		repaint();
	}
}
