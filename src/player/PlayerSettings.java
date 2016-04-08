package player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import interfaces.Setting;
import settings.VerticalLock;

public class PlayerSettings {

	private final boolean VERTICAL_VALUE = false;
	private HashMap<String, Setting> settings = new HashMap<>();
	private Setting verticalLock;
	
	public PlayerSettings(){
		verticalLock = new VerticalLock(VERTICAL_VALUE);
		settings.put(verticalLock.getName(), verticalLock);
	}
	
	public Set<String> getKeys(){
		return settings.keySet();
	}

	public Map<String, Setting> getSettings() {
		return settings;
	}

	public String getVerticalSettingId() {
		return verticalLock.getName();
	}
	
}
