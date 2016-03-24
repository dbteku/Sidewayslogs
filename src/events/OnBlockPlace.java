package events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import interfaces.AuthorizedMemoryAccess;
import main.SideWaysLogs;
import memory.PlayerMemory;
import player.PlayerSettings;

public class OnBlockPlace implements Listener, AuthorizedMemoryAccess{

	private SideWaysLogs plugin;
	private PlayerMemory memory;
	private PlayerSettings playerSettings;
	private String verticalLock;

	public OnBlockPlace(SideWaysLogs plugin, PlayerMemory memory, PlayerSettings playerSettings){
		this.memory = memory;
		this.plugin = plugin;
		this.playerSettings = playerSettings;
		verticalLock = playerSettings.getVerticalNameSetting();
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

		boolean isLocked = memory.getSetting(playerName, verticalLock);

		return isLocked;
	}

}
