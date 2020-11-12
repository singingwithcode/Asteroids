package main.se450.singletons;

import java.util.ArrayList;

import main.se450.model.PlayerShip;

/**
 * As explained in PlayerShip, this implementation allows for multi_player use.
 * Here we keep track of the PlayerShips.
 * 
 * @author Matt Klich
 *
 */
public class PlayerShipList {

	private static PlayerShipList playerShipList = null;
	private ArrayList<PlayerShip> playerShip = null;

	static {
		playerShipList = new PlayerShipList();
	}

	private PlayerShipList() {
		playerShip = new ArrayList<PlayerShip>();
	}

	public final static PlayerShipList getShipList() {
		return playerShipList;
	}

	public final ArrayList<PlayerShip> getShips() {
		return playerShip;
	}

	public void addPlayerShip(PlayerShip ps) {
		playerShip.add(ps);
	}

}
