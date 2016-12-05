package com.dbteku.main;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.dbteku.controllers.EventManager;
import com.dbteku.controllers.ServerManager;
import com.dbteku.language.Messenger;
import com.dbteku.memory.PlayerMemory;

public class SideWaysLogs extends JavaPlugin {
	private static final String MAIN_ERROR = "You cannot run this file its a library!";
	public final Logger LOG = Logger.getLogger("Minecraft");
	public static SideWaysLogs plugin;
	private PluginDescriptionFile pdf = this.getDescription();
	private final String BASE_CMD = "swl";
	private final String NAME = ChatColor.RED + pdf.getName();
	private final String VERSION_NUM = ChatColor.AQUA + pdf.getVersion(); 
	private ServerManager manager;
	private PluginManager events;
	private Messenger messenger;
	private ConsoleCommandSender console;
	private PlayerMemory memory;
	
	@Override
	public void onDisable(){
		memory.forceSaveAndRemovePlayers();
		messenger.sendSavingMessage(console);
		messenger.sendDisableMessage(console);
	}
	
	@Override
	public void onEnable(){
		console = getServer().getConsoleSender();
		events = getServer().getPluginManager();
		messenger = new Messenger(NAME, VERSION_NUM);
		memory = new PlayerMemory(console, messenger);
		manager = new ServerManager(console, messenger, memory);
		EventManager eventManager = new EventManager(this, events, memory);
		eventManager.registerEvents();
		manager.init();
		messenger.sendEnableMessage(console);
		getCommand(BASE_CMD).setExecutor(manager);
		checkOnlinePlayers();
	}
	
	public void checkOnlinePlayers(){
		memory.checkCurrentPlayers();
	}
	
	public static void main(String[] args) {
		System.out.println(MAIN_ERROR);
	}
	
}
