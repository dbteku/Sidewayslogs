package main;

import java.util.logging.Logger;

import manager.ServerManager;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
	ConsoleCommandSender console;
	
	@Override
	public void onDisable(){
		console.sendMessage(NAME + " " +  DISABLED);
	}
	
	@Override
	public void onEnable(){
		console = getServer().getConsoleSender();
		events = getServer().getPluginManager();
		manager = new ServerManager(VERSION_NUM, NAME, this, console, events);
		manager.init();
		getCommand(BASE_CMD).setExecutor(manager);
		console.sendMessage(NAME + " " + VERSION + VERSION_NUM +  " " + ENABLED);
	}
	
}
