package main.se450.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
*
*  The parser for the shapes file
* 
* @author Anthony Freund
*
*/

public class ShapeParser
{
	/**
	*
	* Parses the shape file
	* 
	* @author Anthony Freund
	* 
	* @param the file to parse
	*
	* @return array of shapes
	*/

	public static ArrayList<String> getShapes(String fileName)
	{
        ArrayList<String> strings = new ArrayList<String>();
        
		try
		{ 
	        JSONParser parser = new JSONParser();
	 
	        Object obj = parser.parse(new FileReader(fileName));
	 
	        JSONObject jsonObject = (JSONObject) obj;
	 
	        JSONArray jsonArray = (JSONArray) jsonObject.get("shapes");
	        
	        Iterator<?> jsonIterator = jsonArray.iterator();
	        while (jsonIterator.hasNext())
	        {
	        	JSONObject someShape = (JSONObject)jsonIterator.next();
	        	if (someShape.containsKey("type"))
	        	{
	        		strings.add(someShape.toString());
	        	}
	        }
		}
        catch(FileNotFoundException eFileNotFound)
        {
        }
        catch(IOException eIOException)
        {
        	
        }
        catch(ParseException eParseException)
        {
        }
		
		return strings;
	}
}
