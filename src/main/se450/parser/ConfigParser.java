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
 * This parser is used to obtain all the config values from the specified file.
 * 
 * @author Matthew Klich
 */

public class ConfigParser {

	public static ArrayList<String> getConfig(String fileName) {
		ArrayList<String> strings = new ArrayList<String>();

		try {
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray jsonArray = (JSONArray) jsonObject.get("game");

			Iterator<?> jsonIterator = jsonArray.iterator();
			while (jsonIterator.hasNext()) {
				JSONObject someConfig = (JSONObject) jsonIterator.next();
				strings.add(someConfig.get("framespersecond").toString());
				strings.add(someConfig.get("repeatkeyspeed").toString());
				strings.add(someConfig.get("width").toString());
				strings.add(someConfig.get("height").toString());
				strings.add(someConfig.get("shapes").toString());
				strings.add(someConfig.get("shipwidth").toString());
				strings.add(someConfig.get("shipheight").toString());
				strings.add(someConfig.get("shotspeed").toString());
				strings.add(someConfig.get("shotdiameter").toString());
				strings.add(someConfig.get("shotlifetime").toString());
				strings.add(someConfig.get("forwardthrust").toString());
				strings.add(someConfig.get("reversethrust").toString());
				strings.add(someConfig.get("friction").toString());
				strings.add(someConfig.get("leftright").toString());
				strings.add(someConfig.get("color").toString());
				strings.add(someConfig.get("borders").toString());
			}
		} catch (FileNotFoundException eFileNotFound) {
		} catch (IOException eIOException) {

		} catch (ParseException eParseException) {
		}

		return strings;
	}
}
