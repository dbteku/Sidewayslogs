package player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerSettings {

	private String verticalLock = "Vertical-Lock";
	private final boolean VERTICAL_LOCK = false;
	private HashMap<String,Boolean> settings = new HashMap<>();
	
	public PlayerSettings(){
		settings.put(verticalLock, VERTICAL_LOCK);
	}
	
	public HashMap<String,Boolean> getSettings(){
		return settings;
	}
	
}
