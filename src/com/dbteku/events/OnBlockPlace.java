package com.dbteku.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.dbteku.main.SideWaysLogs;
import com.dbteku.memory.PlayerMemory;

public class OnBlockPlace implements Listener{

	private SideWaysLogs plugin;
	private PlayerMemory memory;

	public OnBlockPlace(SideWaysLogs plugin, PlayerMemory memory){
		this.memory = memory;
		this.plugin = plugin;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){	
		final Block block = event.getBlockPlaced();
		String playerName = event.getPlayer().getName();
		if(isVerticalLocked(playerName)){
			if ((block.getType() == Material.LOG)  || (block.getType() == Material.LOG_2)) {
				flipBlock(block);
			}
		}
	}

	private void flipBlock(Block block){
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
		{
			@SuppressWarnings("deprecation")
			public void run()
			{
				block.setData((byte)(block.getData() % 4));
			}
		});
	}
	
	private boolean isVerticalLocked(String playerName){

		boolean isLocked = memory.isVerticallyLocked(playerName);

		return isLocked;
	}

}
