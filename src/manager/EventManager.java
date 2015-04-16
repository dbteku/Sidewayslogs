package manager;

import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import main.SideWaysLogs;
import memory.MemoryModule;
import events.OnBlockPlace;
import events.OnPlayerJoin;
import events.OnPlayerLeave;

public class EventManager {
	
	private PluginManager pm;
	private SideWaysLogs plugin;
	private OnBlockPlace onBlockPlace;
	private OnPlayerJoin onPlayerJoin;
	private OnPlayerLeave onPlayerLeave;
	private ArrayList<Listener> events = new ArrayList<Listener>();
	private MemoryModule memory;
	
	public EventManager(SideWaysLogs plugin, PluginManager pm, MemoryModule memory){
		this.memory = memory;
		this.plugin = plugin;
		this.pm = pm;
	}
	
	public void init(){
		onBlockPlace = new OnBlockPlace(plugin);
		onPlayerJoin = new OnPlayerJoin(memory);
		onPlayerLeave = new OnPlayerLeave(memory);
		events.add(onBlockPlace);
		events.add(onPlayerJoin);
		events.add(onPlayerLeave);
		register();
	}
	
	private void register(){
		for(Listener l: events){
			pm.registerEvents(l, plugin);
		}
	}

}
