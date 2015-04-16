package events;

import java.util.HashMap;
import java.util.Set;

import interfaces.AuthorizedMemoryAccess;
import main.SideWaysLogs;
import memory.MemoryModule;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import player.PlayerSettings;

public class OnBlockPlace implements Listener, AuthorizedMemoryAccess{

	private SideWaysLogs plugin;
	private MemoryModule memory;
	private PlayerSettings playerSettings;
	private String verticalLock;

	public OnBlockPlace(SideWaysLogs plugin, MemoryModule memory, PlayerSettings playerSettings){
		this.memory = memory;
		this.plugin = plugin;
		this.playerSettings = playerSettings;
		verticalLock = playerSettings.getVerticalNameSetting();
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){	
		final Block block = event.getBlockPlaced();
		String playerName = event.getPlayer().getDisplayName();
		if(isVerticalLocked(playerName)){
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

	private boolean isVerticalLocked(String playerName){

		boolean isLocked = false;
		HashMap<String,Boolean> settings = new HashMap<>();

		Set<String> keys = playerSettings.getKeys();

		if(keys.contains(verticalLock)){
			settings = memory.getPlayerSettings(this, playerName);
			isLocked = settings.get(verticalLock);
		}

		return isLocked;
	}

}
