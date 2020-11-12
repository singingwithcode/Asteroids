package main.se450.keyboard;

import java.util.Observable;

/*
 * Name     : 
 * Depaul#  : 
 * Class    : SE 450
 * Project  : Final
 * Due Date : xx/xx/2017
 *
 * class Keyboard
 *
 */

public class Keystate extends Observable
{
    private int keyState = Keymask.NO_KEY;

    public Keystate()
    {
        keyState = Keymask.NO_KEY;
    }
    
    public Keystate(int keystate)
    {
        keyState = keystate;
    }
    
	public void clear()
	{
		keyState = Keymask.NO_KEY;
	}
	
	public boolean isClear()
	{
		return (keyState == Keymask.NO_KEY);
	}
	
	public int getState()
	{
		return keyState;
	}
	
	public void setKeyDown(int keyMask)
	{
		keyState |= keyMask;
	}

	public void setKeyUp(int keyMask)
	{
		keyState &= (~keyMask);
	}
	
	public boolean isPressed(int keyMask)
	{
		return ((keyMask & keyState) == keyMask);
	}
	
	public boolean isReleased(int keyMask)
	{
		return ((keyMask & keyState) == 0);
	}

	public int allKeyPressedStates()
	{
		return (keyState & Keymask.ALL_REPEAT_KEYS); 
	}
	
	public static boolean isAnyKeyPressed(int keystate)
	{
		return (keystate != Keymask.NO_KEY);
	}
	
	public static boolean isPressed(int keystate, int keymask)
	{
		return ((keymask & keystate) == keymask);
	}
}
