package com.dbteku.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dbteku.models.VerticalLockSetting;
import com.google.gson.Gson;

public class PlayerFileWriter {

	private final String MAIN_DIRECTORY = "plugins/SWL";
	private final String PLAYER_DIRECTORY = "plugins/SWL/players/";
	private final String FILE_TYPE = ".json";

	public void initDirectory(){
		createDirectory();
	}
	
	public void writeSettingToDisk(VerticalLockSetting setting){
		String fileLocation = PLAYER_DIRECTORY + setting.getPlayerId() + FILE_TYPE;
		String json = new Gson().toJson(setting);
		File file = new File(fileLocation);
		try{
			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void createDirectory(){
		if(!new File(MAIN_DIRECTORY).exists() ){
			File mainDirectory = new File(MAIN_DIRECTORY);
			mainDirectory.mkdir();
		}
		if(!new File(PLAYER_DIRECTORY).exists()){
			File playerDirectory = new File(PLAYER_DIRECTORY);
			playerDirectory.mkdir();
		}
	}
}
