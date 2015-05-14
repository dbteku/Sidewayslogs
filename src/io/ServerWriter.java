package io;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import server.ServerSettings;

public class ServerWriter {

	public ServerWriter(){
		
	}

	@SuppressWarnings("unchecked")
	public void createNewFile(String playerName, ServerSettings settings) {
		
		JSONObject serverSettings = new JSONObject();
		serverSettings.put(playerName, settings);
		FileWriter file;
		try {
			file = new FileWriter("plugins\\SWL\\Settings.json");
			file.write(serverSettings.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			System.err.println("Failed to write server settings.");
		}
		
	}
}
