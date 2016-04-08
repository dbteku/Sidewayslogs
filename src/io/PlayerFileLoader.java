package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import interfaces.Setting;
import settings.VerticalLock;

public class PlayerFileLoader {
	
	private final String FILE_TYPE = ".xml";
	
	public PlayerFileLoader(){
		
	}

	@SuppressWarnings("finally")
	public Map<String, Setting> loadPlayerFile(String playerName){
		Map<String, Setting> loadedFile = new HashMap<>();
		try{
			loadedFile = read(playerName);
		}catch(Exception e){
			// File not found.
		}
		finally{
			return loadedFile;
		}
	}

	private HashMap<String, Setting> read(String playerName) throws FileNotFoundException, XMLStreamException{
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		XMLInputFactory input = XMLInputFactory.newFactory();
		XMLStreamReader reader = input.createXMLStreamReader(new FileInputStream("plugins/SWL/players/" + playerName + FILE_TYPE));
		HashMap<String, Setting> loadedFile = new HashMap<>();
		while(reader.hasNext()){
			int eventType = reader.getEventType();
			switch(eventType){
			case XMLStreamReader.START_ELEMENT:
				int attributeCount = reader.getAttributeCount();
				for(int x = 0; x  <attributeCount; x++){
					loadedFile.put(reader.getAttributeLocalName(x) , new VerticalLock(Boolean.parseBoolean(reader.getAttributeValue(x))));
				}
				break;
			}
			reader.next();
		}
		reader.close();
		return loadedFile;
	}

	public boolean checkDirectory() {
		File file = new File("plugins/SWL/");
		return file.exists() && file.isDirectory();
	}
}
