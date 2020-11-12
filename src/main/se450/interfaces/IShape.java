package main.se450.interfaces;

import java.awt.Graphics;
import java.util.ArrayList;

import main.se450.collections.LineCollection;

public interface IShape 
{
	void update();
	
	void draw(Graphics g);
	
	float getMinX();
	
	float getMinY();
	
	float getMaxX();
	
	float getMaxY();
	
	void setMFKTrue();
	
	Boolean getMFK();
	
	int getScore();
	
	ArrayList<IShape> addChildren();
	
	int getChildren();
	
    LineCollection getLineCollection();
}
      