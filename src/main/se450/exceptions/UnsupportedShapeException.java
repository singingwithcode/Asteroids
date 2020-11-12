package main.se450.exceptions;

public class UnsupportedShapeException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type = "Unknown";
	
	public UnsupportedShapeException()
	{
	}
	
	public UnsupportedShapeException(final String sType)
	{
		type = sType;
	}
	
	public String getMessage()
	{
		return "The Shape : "  + type + " is no longer supported!";
	}
}
    