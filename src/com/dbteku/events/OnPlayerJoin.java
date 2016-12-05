package com.dbteku.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.dbteku.memory.PlayerMemory;


public class OnPlayerJoin implements Listener{
	
	private PlayerMemory memory;
	
	public OnPlayerJoin(PlayerMemory memory){
		this.memory = memory;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		String playerName = event.getPlayer().getName();
		memory.loadFromDisk(playerName);
	}
	
}
