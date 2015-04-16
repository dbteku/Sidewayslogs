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

import player.PlayerSettings;

public class ServerManager implements CommandExecutor{

	private SideWaysLogs plugin;
	private EventManager eventManager;
	private final String BASE_CMD = "swl";
	private final String HELP_CMD = "help";
	private final String VERSION = "version";
	private final String TOGGLE = "toggle";
	private final String STATUS = "status";
	private Messenger messenger;
	private ConsoleCommandSender console;
	private PluginManager pm;
	private MemoryModule memory;
	private PlayerSettings playerSettings;
	private String verticalLock;

	public ServerManager(SideWaysLogs plugin, ConsoleCommandSender console, PluginManager events, Messenger messenger, MemoryModule memory, PlayerSettings playerSettings){
		this.plugin = plugin;
		this.console = console;
		this.pm = events;
		this.messenger = messenger;
		this.memory = memory;
		this.playerSettings = playerSettings;
		verticalLock = playerSettings.getVerticalNameSetting();
	}

	public void init(){
		eventManager  = new EventManager(plugin, pm, memory, playerSettings);
		eventManager.init();
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
					if(args[0].equalsIgnoreCase(TOGGLE)){
						memory.toggleVerticalLock(sender, verticalLock);
						boolean b = getVerticalLock(sender);
						if(b){
							messenger.sendVerticalLock(sender);
						}else{
							messenger.sendNormalPlacement(sender);
						}
					}
					if(args[0].equalsIgnoreCase(STATUS)){
						boolean b = getVerticalLock(sender);
						if(b){
							messenger.sendVerticalStatus(sender);
						}else{
							messenger.sendNormalStatus(sender);
						}
					}
				
				}else{
					messenger.sendSyntaxMessage(sender);
				}

			}
		}else{
			if(label.equalsIgnoreCase(BASE_CMD)){
				messenger.sendConsoleMessage(sender);
//				if(args.length > 0){
//					if(args[0].equalsIgnoreCase("")){
//						
//					}
//				}
			}
		}
		return false;
	}

	public boolean getVerticalLock(CommandSender sender){
		return memory.getVerticalLock(sender, verticalLock);
	}

}
