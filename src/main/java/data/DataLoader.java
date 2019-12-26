package data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import gui.BookInfoWindow;
import gui.QuestionAndAnswerWindow;

public class DataLoader {
	/**
	 * Load JSON files, parse them and pass data to the GUI classes
	 */
	public static void loadData() {
		try {
			QuestionAndAnswerWindow.storeDictionary(readJSON("/data/QandA.json"));
			BookInfoWindow.storeDictionary(readJSON("/data/BooksNames.json"));

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static JSONObject readJSON(String resourceName)
			throws UnsupportedEncodingException, IOException, ParseException {
		InputStream inputStream = DataLoader.class.getResourceAsStream(resourceName);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
		return jsonObject;
	}
//		URL resource = DataLoader.class.getResource(path);
//		File jsonFile;
//		Object obj = null;
//		try {
//			jsonFile = Paths.get(resource.toURI()).toFile();
//			JSONParser jsonParser = new JSONParser();
//			FileReader reader = new FileReader(jsonFile.getAbsolutePath());
//			obj = jsonParser.parse(reader);
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return (JSONObject) obj;
//	}
}