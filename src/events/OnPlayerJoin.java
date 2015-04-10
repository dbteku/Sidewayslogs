package events;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class OnPlayerJoin implements Listener{
	
	private CommandSender console;
	
	public OnPlayerJoin(){
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		//Works
		//TODO have it load the player file into an object.
	}
	
}
