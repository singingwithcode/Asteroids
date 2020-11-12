package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IShape;


public class ShapeListTemp {
	private static ShapeListTemp shapeList = null;

	private ArrayList<IShape> iShapes = null;

	static {
		shapeList = new ShapeListTemp();
	}

	private ShapeListTemp() {
		iShapes = new ArrayList<IShape>();
	}

	public final static ShapeListTemp getShapeList() {
		return shapeList;
	}

	public final ArrayList<IShape> getShapes() {
		
		return iShapes;
	}

	public void addShapes(final ArrayList<IShape> iShapeList) {
		iShapes.addAll(iShapeList);
	}
	
	public void addShape(final IShape iShapeList) {
		iShapes.add(iShapeList);
	}
	
	public void clearShapes() {
		iShapes.clear();
	}
}
