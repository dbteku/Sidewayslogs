package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Input {
	
	private final String FILE_TYPE = ".xml";
	
	public Input(){
		
	}

	@SuppressWarnings("finally")
	public HashMap<String,Boolean> loadPlayerFile(String playerName){
		HashMap<String, Boolean> loadedFile = new HashMap<>();
		try{
			loadedFile = read(playerName);
		}catch(Exception e){
			System.err.println("Failed To Read File");
		}
		finally{
			return loadedFile;
		}
	}

	private HashMap<String, Boolean> read(String playerName) throws FileNotFoundException, XMLStreamException{
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		XMLInputFactory input = XMLInputFactory.newFactory();
		XMLStreamReader reader = input.createXMLStreamReader(new FileInputStream("plugins/SWL/" + playerName + FILE_TYPE));
		HashMap<String, Boolean> loadedFile = new HashMap<>();
		while(reader.hasNext()){
			int eventType = reader.getEventType();
			switch(eventType){
			case XMLStreamReader.START_ELEMENT:
				int attributeCount = reader.getAttributeCount();
				for(int x = 0; x  <attributeCount; x++){
					loadedFile.put(reader.getAttributeLocalName(x) , Boolean.parseBoolean(reader.getAttributeValue(x)));
				}
				break;
			}
			reader.next();
		}
		reader.close();
		return loadedFile;
	}

	public boolean checkDirectory() {
		File file = new File("plugins/SWL");
		return file.exists() && file.isDirectory();
	}
}
