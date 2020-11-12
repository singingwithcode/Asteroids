package test.se450.test.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import main.se450.model.Circle;

public class ShapeTest {
	@Test
	public void testFriction() {
		{
			Color cColor = new Color(-1);
			Circle c = new Circle((float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 5, (float) 1,
					cColor, null, "Large", 10, 2, 2);
			c.friction(1);
			assertTrue(c.getX() < 1);
			assertTrue(c.getY() < 5);
		}
	}
	
	@Test
	public void testFrictionZero() {
		{
			Color cColor = new Color(-1);
			Circle c = new Circle((float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 0.1, (float) 0.1,
					cColor, null, "Large", 10, 2, 2);
			c.friction(1);
			assertTrue(c.getX() == 0);
			assertTrue(c.getY() == 0);
		}
	}
}
