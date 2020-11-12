package main.se450.singletons;

import java.awt.Graphics;

public class DisplayManager {
	private static DisplayManager displayManager = null;

	private int width = 0;
	private int height = 0;

	private Graphics graphics = null;

	static {
		displayManager = new DisplayManager();
	}

	private DisplayManager() {
	}

	public final static DisplayManager getDisplayManager() {
		return displayManager;
	}

	public void setDisplaySize(int nWidth, int nHeight) {
		width = nWidth;
		height = nHeight;
	}

	public void setGraphics(Graphics oGraphics) {
		graphics = oGraphics;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
