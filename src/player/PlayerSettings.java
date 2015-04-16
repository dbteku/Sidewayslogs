package player;

import java.util.HashMap;
import java.util.Set;

public class PlayerSettings {

	private final String VERTICAL_NAME = "Vertical-Lock";
	private final boolean VERTICAL_VALUE = false;
	private HashMap<String,Boolean> settings = new HashMap<>();
	
	public PlayerSettings(){
		settings.put(VERTICAL_NAME, VERTICAL_VALUE);
	}
	
	public HashMap<String,Boolean> getSettings(){
		return settings;
	}
	
	public Set<String> getKeys(){
		return settings.keySet();
	}
	
	public String getVerticalNameSetting(){
		return VERTICAL_NAME;
	}
	
}
