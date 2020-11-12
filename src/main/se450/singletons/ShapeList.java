package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IShape;


public class ShapeList {
	private static ShapeList shapeList = null;

	private ArrayList<IShape> iShapes = null;

	static {
		shapeList = new ShapeList();
	}

	private ShapeList() {
		iShapes = new ArrayList<IShape>();
	}

	public final static ShapeList getShapeList() {
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
