package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ServerWriter {

	public void writeSettings(String settings) throws IOException{
		File file = new File("plugins/SWL/config.json");
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(settings);
		bw.close();
		
	}

	public void createDirectory(){
		File mainDirectory = new File("plugins/SWL");
		mainDirectory.mkdir();
	}
}
