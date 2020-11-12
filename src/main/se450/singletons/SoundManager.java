package main.se450.singletons;

import java.util.HashMap;

import main.se450.constants.Constants;
import main.se450.interfaces.ISound;
import main.se450.sound.Explode;
import main.se450.sound.ExplodeLarge;
import main.se450.sound.ExplodeSmall;
import main.se450.sound.Fire;
import main.se450.sound.ForwardThrust;
import main.se450.sound.GameOver;
import main.se450.sound.ReverseThrust;

public class SoundManager {
	private static SoundManager soundManager = null;

	private HashMap<String, ISound> sounds = null;

	static {
		soundManager = new SoundManager();
	}

	private SoundManager() {
		sounds = new HashMap<String, ISound>();

		sounds.put(Constants.FIRE, new Fire());
		sounds.put(Constants.FORWARD_THRUST_PRESSED, new ForwardThrust());
		sounds.put(Constants.REVERSE_THRUST_PRESSED, new ReverseThrust());
		sounds.put(Constants.EXPLODE, new Explode());
		sounds.put(Constants.EXPLODESMALL, new ExplodeSmall());
		sounds.put(Constants.EXPLODELARGE, new ExplodeLarge());
		sounds.put(Constants.GAMEOVER, new GameOver());
	}

	public final static SoundManager getSoundManager() {
		return soundManager;
	}

	public void fire() {
		sounds.get(Constants.FIRE).play();
	}

	public void forwardThrust() {
		sounds.get(Constants.FORWARD_THRUST_PRESSED).play();
	}

	public void reverseThrust() {
		sounds.get(Constants.REVERSE_THRUST_PRESSED).play();
	}

	public void explode() {
		sounds.get(Constants.EXPLODE).play();
	}

	public void explodeLarge() {
		sounds.get(Constants.EXPLODELARGE).play();
	}

	public void explodeSmall() {
		sounds.get(Constants.EXPLODELARGE).play();
	}
	
	public void gameOver() {
		sounds.get(Constants.GAMEOVER).play();
	}
}
