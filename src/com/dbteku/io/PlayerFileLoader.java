package com.dbteku.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.dbteku.models.VerticalLockSetting;
import com.google.gson.Gson;

public class PlayerFileLoader {

	private final String UPGRADING_TO_JSON = "XML File Detected. Upgrading to JSON";
	private final String DIRECTORY = "plugins/SWL/players/";
	private final String XML = ".xml";
	private final String JSON = ".json";

	public PlayerFileLoader(){

	}

	public VerticalLockSetting loadSettingFromFile(String playerName){
		VerticalLockSetting setting = new VerticalLockSetting(playerName, false);
		String fileLocation = DIRECTORY + playerName + XML;
		File file = new File(fileLocation);
		if(file.exists()){
			deleteXmlAndReplaceWithJson(file, playerName);
		}else{
			setting = tryLoadingFromDisk(playerName);
		}
		return setting;
	}
	
	public boolean doesPlayerFileExist(String playerName){
		return new File(DIRECTORY + playerName + XML).exists() || new File(DIRECTORY + playerName + JSON).exists();
	}

	public boolean doesDirectoryExist() {
		File file = new File(DIRECTORY);
		return file.exists() && file.isDirectory();
	}

	private void deleteXmlAndReplaceWithJson(File file, String playerName){
		System.out.println(UPGRADING_TO_JSON);
		file.delete();
		file = new File(DIRECTORY + playerName + JSON);
		try {
			PrintWriter writer = new PrintWriter(file);
			Gson gson = new Gson();
			String content = gson.toJson(new VerticalLockSetting(playerName, false));
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private VerticalLockSetting tryLoadingFromDisk(String playerName){
		VerticalLockSetting setting = new VerticalLockSetting(playerName, false);
		String fileLocation = DIRECTORY + playerName + JSON;
		File file = new File(fileLocation);
		List<String> lines = new ArrayList<>();
		String line = "";
		if(file.exists()){
			try{
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line = reader.readLine()) != null){
					lines.add(line);
				}
				reader.close();
				String json = linesToJson(lines);
				setting = new Gson().fromJson(json, VerticalLockSetting.class);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return setting;
	}
	
	private String linesToJson(List<String> lines){
		StringBuilder builder = new StringBuilder();
		
		for (String line : lines) {
			builder.append(line);
			builder.append("\n");
		}
		
		return builder.toString();
	}

}
