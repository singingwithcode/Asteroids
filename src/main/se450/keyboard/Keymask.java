package main.se450.keyboard;

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

public class Keymask
{
	public static final int NO_KEY             = 0x0;
	public static final int FIRE_KEY           = 0x1;
	public static final int LEFT_KEY           = 0x10;
	public static final int RIGHT_KEY          = 0x100;
	public static final int FORWARD_THRUST_KEY = 0x1000;
	public static final int REVERSE_THRUST_KEY = 0x10000;
	public static final int ALL_REPEAT_KEYS    = (REVERSE_THRUST_KEY | FORWARD_THRUST_KEY | RIGHT_KEY | LEFT_KEY | FIRE_KEY);
}
