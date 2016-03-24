package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ServerReader {

	
	public String getConfig()throws IOException{
		
		String json = "";
		
		File file = new File("plugins/SWL/config.json");
		if(file.exists()){
			StringBuilder builder = new StringBuilder();
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = "";
			while((line = reader.readLine()) != null){
				builder.append(line);
			}
			json = builder.toString();
		}
		
		return json;
	}
	
	public boolean configExists(){
		File file = new File("plugins/SWL/config.json");
		boolean exists = file.exists();
		return exists;
	}
	
	public boolean directoryExists() {
		File file = new File("plugins/SWL/");
		return file.exists() && file.isDirectory();
	}

	
}
