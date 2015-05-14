package events;

import interfaces.AuthorizedMemoryAccess;
import memory.PlayerMemory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class OnPlayerJoin implements Listener, AuthorizedMemoryAccess{
	
	private PlayerMemory memory;
	
	public OnPlayerJoin(PlayerMemory memory){
		this.memory = memory;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		String playerName = event.getPlayer().getName();
		memory.loadFromDisk(this, playerName);
	}
	
}
