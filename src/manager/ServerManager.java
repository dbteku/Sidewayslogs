package manager;

import interfaces.AuthorizedMemoryAccess;
import language.Messenger;
import main.SideWaysLogs;
import memory.PlayerMemory;
import memory.ServerMemory;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import player.PlayerSettings;
import server.ServerSettings;

public class ServerManager implements CommandExecutor, AuthorizedMemoryAccess{

	private SideWaysLogs plugin;
	private EventManager eventManager;
	private final String BASE_CMD = "swl";
	private final String HELP_CMD = "help";
	private final String VERSION = "version";
	private final String TOGGLE = "toggle";
	private final String STATUS = "status";
	private final String PLAYER_EXIST = ChatColor.RED + "Player doesn't exist or hasn't joined the server!";
	private Messenger messenger;
	private ConsoleCommandSender console;
	private PluginManager pm;
	private PlayerMemory memory;
	private PlayerSettings playerSettings;
	private ServerSettings serverSettings;
	private String verticalLock;
	private ServerMemory serverMemory;

	public ServerManager(SideWaysLogs plugin, ConsoleCommandSender console, PluginManager events, Messenger messenger, PlayerMemory memory, PlayerSettings playerSettings){
		this.plugin = plugin;
		this.console = console;
		this.pm = events;
		this.messenger = messenger;
		this.memory = memory;
		this.playerSettings = playerSettings;
		verticalLock = playerSettings.getVerticalNameSetting();
		serverMemory = new ServerMemory(console, messenger, serverSettings);
		//serverMemory.loadFromDisk(this);
	}

	public void init(){
		eventManager  = new EventManager(plugin, pm, memory, playerSettings);
		eventManager.init();
		memory.checkDirectory(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			String player = sender.getName();
			if(label.equalsIgnoreCase(BASE_CMD)){
				if(args.length > 0){
					if(args[0].equalsIgnoreCase(HELP_CMD)){
						messenger.sendHelpMessage(sender);
					}
					else if(args[0].equalsIgnoreCase(VERSION)){
						messenger.sendVersionMessage(sender);
					}
					else if(args[0].equalsIgnoreCase(TOGGLE)){
						memory.toggleSetting(player, verticalLock);
						boolean b = getVerticalLock(player);
						if(b){
							messenger.sendVerticalLock(sender);
						}else{
							messenger.sendNormalPlacement(sender);
						}
					}
					else if(args[0].equalsIgnoreCase(STATUS)){
						boolean b = getVerticalLock(player);
						if(b){
							messenger.sendVerticalStatus(sender);
						}else{
							messenger.sendNormalStatus(sender);
						}
					}else{
						messenger.sendSyntaxMessage(sender);
					}
				}else{
					memory.toggleSetting(player, verticalLock);
					boolean b = getVerticalLock(player);
					if(b){
						messenger.sendVerticalLock(sender);
					}else{
						messenger.sendNormalPlacement(sender);
					}
				}
			}
		}else{
			if(label.equalsIgnoreCase(BASE_CMD)){
				//messenger.sendConsoleMessage(sender);
				if(args.length > 0){
					if(args[0].equalsIgnoreCase("status")){
						if(args.length > 1){
							if(!args[1].isEmpty()){
								try{
									boolean b = memory.getSetting(args[1], verticalLock);
									if(b){
										messenger.sendConsoleVerticalStatus(sender, args[1]);
									}else{
										messenger.sendConsoleNormalStatus(sender, args[1]);
									}
								}catch(Exception e){
									console.sendMessage(PLAYER_EXIST);
								}
							}
						}else{
							messenger.sendConsoleHelpMessage(sender);
						}
					}
					if(args[0].equalsIgnoreCase("memory")){
						//serverMemory.logMemory();
					}
					if(args[0].equalsIgnoreCase(VERSION)){
						messenger.sendVersionMessage(sender);
					}
				}else{
					messenger.sendConsoleHelpMessage(sender);
				}
			}
		}
		return false;
	}

	public boolean getVerticalLock(String player){
		return memory.getSetting(player, verticalLock);
	}

}
