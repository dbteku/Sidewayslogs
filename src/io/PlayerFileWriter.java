package io;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class PlayerFileWriter {

	private final String VERSION = "1.0";
	private final String SETTINGS = "Player_Settings";
	private final String FILE_TYPE = ".xml";
	
	public void initDirectory(){
		createDirectory();
	}

	public void createNewFile(String playerName, HashMap<String,Boolean> settings) {
		encode(playerName,settings);
	}
	
	public void update(String playerName, HashMap<String,Boolean> settings){
		encode(playerName,settings);
	}

	@SuppressWarnings("unchecked")
	private void encode(String playerName, HashMap<String,Boolean> settings){

		ArrayList<String> nameSettings = new ArrayList<String>();
		ArrayList<Boolean> values = new ArrayList<Boolean>();
		Set<String> names = settings.keySet();

		for(String s: names){
			nameSettings.add(s);
			values.add(settings.get(s));
		}

		try{
			write(playerName,nameSettings,values);
		}catch(Exception e){
			createDirectory();
			try {
				write(playerName,nameSettings,values);
			} catch (Exception e1) {
			}
		}
	}

	private void write(String playerName, ArrayList<String> names, ArrayList<Boolean> values) throws Exception{

		XMLOutputFactory output = XMLOutputFactory.newFactory();
		XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream("plugins/SWL/Players/" + playerName + FILE_TYPE));
		writer.writeStartDocument(VERSION);
		writer.writeStartElement(playerName);
		writer.writeStartElement(SETTINGS);
		Boolean b = false;
		for(int x = 0; x < names.size(); x++){
			b = values.get(x);
			writer.writeAttribute(names.get(x), Boolean.toString(b));
		}

		writer.writeEndElement();
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.flush();
		writer.close();
	}

	private void createDirectory(){
		File directory = new File("plugins/SWL/Players/");
		directory.mkdir();
	}

}
