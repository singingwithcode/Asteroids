package main.se450.utilities;

import java.awt.Color;

/**
*
*  The parser for the shapes file
* 
* @author Anthony Freund
*
*/

public class Extractor
{
	final public static Color extractColor(final Object object)
	{
		return new Color(extractInteger(object));
	}
	
	final public static Integer extractInteger(final Object object)
	{
		return Integer.parseInt(object.toString(), 10);
	}

	final public static Float extractFloat(final Object object)
	{
		return Float.parseFloat(object.toString());
	}
	
	final public static Double extractDouble(final Object object)
	{
		return Double.parseDouble(object.toString());
	}
	
}
