package server;

import java.util.HashMap;
import java.util.Set;

public class ServerSettings {
	
	private String globalSetting = "globalLock";
	private boolean globalLock = false;
	
	private HashMap<String, Boolean> serverSettings = new HashMap<>(); 
	
	public ServerSettings(){
		serverSettings.put(globalSetting, globalLock);
	}
	
	public Set<String> getKeys(){
		return serverSettings.keySet();
	}
	
	public HashMap<String, Boolean> getSettings(){
		return serverSettings;
	}
	
}
