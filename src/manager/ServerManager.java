package manager;

import language.Messenger;
import main.SideWaysLogs;
import memory.MemoryModule;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class ServerManager implements CommandExecutor{

	private SideWaysLogs plugin;
	private final String PLUGIN_VERSION;
	private final String PLUGIN_NAME;
	private EventManager eventManager;
	private final String BASE_CMD = "swl";
	private final String HELP_CMD = "help";
	private final String VERSION = "version";
	private Messenger messenger;
	private ConsoleCommandSender console;
	private PluginManager pm;
	private MemoryModule memory;

	public ServerManager(String pluginVersion, String pluginName, SideWaysLogs plugin, ConsoleCommandSender console, PluginManager events){
		this.PLUGIN_VERSION = pluginVersion;
		this.PLUGIN_NAME = pluginName;
		this.plugin = plugin;
		this.console = console;
		this.pm = events;
		this.memory = new MemoryModule();
	}

	public void init(){
		eventManager  = new EventManager(plugin, pm, memory);
		eventManager.init();
		messenger = new Messenger(PLUGIN_NAME, PLUGIN_VERSION);
		memory.checkDirectory(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			if(label.equalsIgnoreCase(BASE_CMD)){
				if(args.length > 0){
					if(args[0].equalsIgnoreCase(HELP_CMD)){
						messenger.sendHelpMessage(sender);
					}
					if(args[0].equalsIgnoreCase(VERSION)){
						messenger.sendVersionMessage(sender);
					}
				}else{
					messenger.sendSyntaxMessage(sender);
				}

			}
		}else{
			if(label.equalsIgnoreCase(BASE_CMD)){
				messenger.sendConsoleCommand(sender);
			}
		}
		return false;
	}

}
