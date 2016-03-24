package server;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import interfaces.Setting;
import io.ServerReader;
import io.ServerWriter;
import settings.DefaultFlip;
import settings.GlobalLock;

public class ServerSettings {

	private final List<Setting> SETTINGS = Arrays.asList(new GlobalLock(false),new DefaultFlip(false));
	
	public ServerSettings(){
	}
	
	public void loadOrCreate(){
		ServerReader reader = new ServerReader();
		if(reader.directoryExists()){
			if(reader.configExists()){
				readConfig(reader);
			}else{
				writeConfig(SETTINGS);
				readConfig(reader);
			}
		}else{
			ServerWriter writer = new ServerWriter();
			writer.createDirectory();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void writeConfig(List<Setting> settings){
		JSONObject object = new JSONObject();
		for (Setting setting : settings) {
			object.put(setting.getName(), setting.isEnabled());
		}
		StringWriter writer = new StringWriter();
		try {
			object.writeJSONString(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String json = writer.toString();
		ServerWriter serverWriter = new ServerWriter();
		try {
			serverWriter.writeSettings(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readConfig(ServerReader reader){
		String json = "{}";
		try {
			json = reader.getConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		convertIntoSettings(json);
	}
	
	private void convertIntoSettings(String json){
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(json);
			JSONObject jsonObject = (JSONObject) obj;
			for (Setting setting : SETTINGS) {
				boolean isEnabled = (boolean) jsonObject.get(setting.getName());
				setting.setEnabled(isEnabled);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		catch(ClassCastException cce){
			cce.printStackTrace();
		}
//		System.out.println(SETTINGS.length);
//		for (Setting setting : SETTINGS) {
//			System.out.println(setting);
//		}
	}

	public List<Setting> getSettings() {
		return SETTINGS;
	}

	public void save(List<Setting> settings) {
		writeConfig(settings);
	}
	
}
