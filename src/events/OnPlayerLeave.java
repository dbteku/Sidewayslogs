package events;

import interfaces.AuthorizedMemoryAccess;
import memory.MemoryModule;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeave implements Listener,AuthorizedMemoryAccess{

	private MemoryModule memory;
	
	public OnPlayerLeave(MemoryModule memory){
		this.memory = memory;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
	    String playerName = event.getPlayer().getName();
	    memory.writeToDisk(this, playerName);
	}
}
