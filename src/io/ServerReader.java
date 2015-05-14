package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.ServerSettings;

public class ServerReader {
	
	ServerSettings setting;
	
	public ServerReader(ServerSettings setting){
		this.setting = setting;
	}

	private JSONParser parser = new JSONParser();
	
	public HashMap<String, Boolean> loadPlayerFile(String playerName) {
		HashMap<String, Boolean> file = new HashMap<>();
		JSONObject serverSettings = null;
		try {
			serverSettings = (JSONObject) parser.parse(new FileReader("plugins\\SWL\\Settings.json"));
		} catch (Exception e) {
		}
		
		file = encodeAndReturn(serverSettings);
		
		return file;
	}
	
	private HashMap<String,Boolean> encodeAndReturn(JSONObject settings){
		HashMap<String, Boolean> file = new HashMap<>();
		JSONObject o = null;
		if(settings != null){
			Iterator<String> values = setting.getKeys().iterator();
			String name ="";
			while(values.hasNext()){
				name = values.next();
				o.get(name);
				file.put(name, true);
			}
		}
		
		return file;
	}

}
