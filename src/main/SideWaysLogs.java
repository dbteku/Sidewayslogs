package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import language.Messenger;
import manager.ServerManager;
import memory.MemoryModule;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import player.PlayerSettings;

public class SideWaysLogs extends JavaPlugin {
	public final Logger LOG = Logger.getLogger("Minecraft");
	public static SideWaysLogs plugin;
	private PluginDescriptionFile pdf = this.getDescription();
	private final String BASE_CMD = "swl";
	private final String DISABLED = "Has Been Disabled!";
	private final String ENABLED = "Has Been Enabled!";
	private final String NAME = ChatColor.RED + pdf.getName();
	private final String VERSION = ChatColor.GREEN + "Version: ";
	private final String VERSION_NUM = ChatColor.AQUA + pdf.getVersion(); 
	private ServerManager manager;
	private PluginManager events;
	private Messenger messenger;
	private ConsoleCommandSender console;
	private MemoryModule memory;
	private PlayerSettings playerSettings = new PlayerSettings();
	
	@Override
	public void onDisable(){
		memory.forceSave();
		messenger.sendSavingMessage(console);
		console.sendMessage(NAME + " " +  DISABLED);
	}
	
	@Override
	public void onEnable(){
		console = getServer().getConsoleSender();
		events = getServer().getPluginManager();
		messenger = new Messenger(NAME, VERSION_NUM);
		memory = new MemoryModule(console, messenger, playerSettings);
		manager = new ServerManager(this, console, events, messenger, memory, playerSettings);
		manager.init();
		getCommand(BASE_CMD).setExecutor(manager);
		console.sendMessage(NAME + " " + VERSION + VERSION_NUM +  " " + ENABLED);
		checkOnlinePlayers();
	}
	
	public void checkOnlinePlayers(){
			memory.checkCurrentPlayers();
	}
	
}
