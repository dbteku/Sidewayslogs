package manager;

import language.Messenger;
import main.SideWaysLogs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ServerManager implements CommandExecutor{

	private SideWaysLogs plugin;
	private final String PLUGIN_VERSION;
	private final String PLUGIN_NAME;
	private EventManager eventManager;
	private final String BASE_CMD = "swl";
	private final String HELP_CMD = "help";
	private Messenger messenger;
	private ConsoleCommandSender console;

	public ServerManager(String pluginVersion, String pluginName, SideWaysLogs plugin, ConsoleCommandSender console){
		this.PLUGIN_VERSION = pluginVersion;
		this.PLUGIN_NAME = pluginName;
		this.plugin = plugin;
		this.console = console;
	}

	public void init(){
		eventManager  = new EventManager();
		eventManager.init();
		messenger = new Messenger(PLUGIN_NAME, PLUGIN_VERSION, console);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
		if(sender instanceof Player){
			if(label.equalsIgnoreCase(BASE_CMD)){
				if(args.length > 0){
					if(args[0].equalsIgnoreCase(HELP_CMD)){
						messenger.sendHelpMessage(player);
					}
				}else{
					messenger.sendSyntaxMessage(player);
				}
			}
		}else{
			if(label.equalsIgnoreCase(BASE_CMD)){
				if(args.length > 0){
					messenger.sendVersionMessage(console);
				}else{
					messenger.sendVersionMessage(console);
				}
			}
		}
		return false;
	}

}
