package test.se450.test.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import main.se450.model.PlayerShip;

public class PlayerShipTest {
	@Test
	public void testRandNum() {
		{
			Color co = new Color(-1);
			PlayerShip ship = new PlayerShip(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, co, null, (double) 0,
					(double) 0, (double) 0, (double) 0, "", 0, 0, 0);

			int i = 0;
			
			//testing 10 times
			while (i <= 10) {
				assertTrue(ship.randNum(500, 10) < 500);
				i++;
			}
		}
	}
}
