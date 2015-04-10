package manager;

import events.OnBlockPlace;

public class EventManager {
	
	private OnBlockPlace onBlockPlace;
	
	public EventManager(){
		
	}
	
	public void init(){
		onBlockPlace = new OnBlockPlace();
	}

}
