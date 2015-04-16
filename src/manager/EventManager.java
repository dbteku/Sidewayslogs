package manager;

import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import player.PlayerSettings;
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
	private PlayerSettings playerSettings;
	
	public EventManager(SideWaysLogs plugin, PluginManager pm, MemoryModule memory, PlayerSettings playerSettings){
		this.memory = memory;
		this.plugin = plugin;
		this.pm = pm;
		this.playerSettings = playerSettings;
	}
	
	public void init(){
		onBlockPlace = new OnBlockPlace(plugin, memory, playerSettings);
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
