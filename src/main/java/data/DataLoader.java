package data;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import gui.BookInfoWindow;
import gui.QuestionAndAnswerWindow;

public class DataLoader {
	/**
	 * Load JSON files, parse them and pass data to the GUI classes
	 */
	public static void loadData() {
		QuestionAndAnswerWindow.storeDictionary(readJSON("/data/QandA.json")); 
		BookInfoWindow.storeDictionary(readJSON("/data/BooksNames.json"));
	}
	
	private static JSONObject readJSON(String path) {
		URL resource = DataLoader.class.getResource(path);
		File jsonFile;
		Object obj = null;
		try {
			jsonFile = Paths.get(resource.toURI()).toFile();
			JSONParser jsonParser = new JSONParser();
			FileReader reader = new FileReader(jsonFile.getAbsolutePath());
			obj = jsonParser.parse(reader);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (JSONObject) obj;
	}
}
