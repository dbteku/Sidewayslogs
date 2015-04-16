package events;

import interfaces.AuthorizedMemoryAccess;
import memory.MemoryModule;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class OnPlayerJoin implements Listener, AuthorizedMemoryAccess{
	
	private MemoryModule memory;
	
	public OnPlayerJoin(MemoryModule memory){
		this.memory = memory;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		String playerName = event.getPlayer().getName();
		memory.loadFromDisk(this, playerName);
	}
	
}
