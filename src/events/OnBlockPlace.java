package events;

import main.SideWaysLogs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener{
	
	SideWaysLogs plugin;
	
	public OnBlockPlace(SideWaysLogs plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){	
		final Block block = event.getBlockPlaced();
		//String playerName = event.getPlayer().getDisplayName();
		if ((block.getType() == Material.LOG)  || (block.getType() == Material.LOG_2)) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
			{
				@SuppressWarnings("deprecation")
				public void run()
				{
					block.setData((byte)(block.getData() % 4));
				}
			});
		}
	}
}
