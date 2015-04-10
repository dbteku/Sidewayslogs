package manager;

import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import main.SideWaysLogs;
import events.OnBlockPlace;
import events.OnPlayerJoin;

public class EventManager {
	
	private PluginManager pm;
	private SideWaysLogs plugin;
	private OnBlockPlace onBlockPlace;
	private OnPlayerJoin onPlayerJoin;
	private ArrayList<Listener> events = new ArrayList<Listener>();
	
	public EventManager(SideWaysLogs plugin, PluginManager pm){
		this.plugin = plugin;
		this.pm = pm;
	}
	
	public void init(){
		onBlockPlace = new OnBlockPlace(plugin);
		onPlayerJoin = new OnPlayerJoin();
		events.add(onBlockPlace);
		events.add(onPlayerJoin);
		register();
	}
	
	private void register(){
		for(Listener l: events){
			pm.registerEvents(l, plugin);
		}
	}

}
