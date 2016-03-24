package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.command.ConsoleCommandSender;

import interfaces.Setting;
import language.Messenger;
import server.ServerSettings;

public class ServerMemory {
	
	private HashMap<String, Setting> localMemory = new HashMap<>();
	private ConsoleCommandSender console;
	private Messenger messenger;
	private ServerSettings settings;
	
	public ServerMemory(ConsoleCommandSender console, Messenger messenger){
		this.console = console;
		this.messenger = messenger;
		
	}

	public void loadSettings() {
		settings = new ServerSettings();
		settings.loadOrCreate();
		putInMemory();
	}
	
	public void saveSettings(){
		Set<String> keys = localMemory.keySet();
		List<Setting> settings = new ArrayList<>();
		for (String string : keys) {
			settings.add(localMemory.get(string));
		}
		this.settings.save(settings);
	}
	
	public void shutdownAndSave(){
		Set<String> keys = localMemory.keySet();
		List<Setting> settings = new ArrayList<>();
		for (String string : keys) {
			settings.add(localMemory.get(string));
		}
		this.settings.save(settings);
		localMemory.clear();
		
	}
	
	private void putInMemory(){
		List<Setting> setting = settings.getSettings();
		for (Setting s : setting) {
			localMemory.put(s.getName(), s);
		}
	}
	
	public boolean isSettingEnabled(String key){
		boolean isEnabled = false;
		if(localMemory.containsKey(key)){
			isEnabled = localMemory.get(key).isEnabled();
		}
		return isEnabled;
	}
}
