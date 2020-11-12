package main.se450.utilities;

import main.se450.model.PlayerShip;

/**
 * This thread based implementation is used to stall the shield around the ship.
 * The thread will return to the PlayerShip and set it back to false (not
 * active).
 * 
 * @author Matt Klich
 *
 */
public class TimeDelay implements Runnable {

	private PlayerShip ps;

	public TimeDelay(PlayerShip playerShip) {
		this.ps = playerShip;
	}

	@Override
	public void run() {
		long end = System.currentTimeMillis() + 5000;
		while (System.currentTimeMillis() < end) {
			// Wait
		}
		System.out.println("Shield is off");
		ps.setShieldOff();
	}

}
