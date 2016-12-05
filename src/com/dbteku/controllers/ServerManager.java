package com.dbteku.controllers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.dbteku.language.Messenger;
import com.dbteku.memory.PlayerMemory;

public class ServerManager implements CommandExecutor{

	private final String BASE_CMD = "swl";
	private final String HELP_CMD = "help";
	private final String VERSION = "version";
	private final String TOGGLE = "toggle";
	private final String STATUS = "status";
	private final String PLAYER_EXIST = ChatColor.RED + "Player doesn't exist or hasn't joined the server!";
	private Messenger messenger;
	private ConsoleCommandSender console;
	private PlayerMemory memory;

	public ServerManager( ConsoleCommandSender console, Messenger messenger, PlayerMemory memory){
		this.console = console;
		this.messenger = messenger;
		this.memory = memory;
	}

	public void init(){
		memory.checkDirectory(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			playerCommand(label, args, sender);
		}else{
			consoleCommands(label,args,sender);
		}
		return false;
	}
	
	private void consoleCommands(String label, String[] args, CommandSender sender){
		if(label.equalsIgnoreCase(BASE_CMD)){
			if(args.length > 0){
				if(args[0].equalsIgnoreCase("status")){
					if(args.length > 1){
						if(!args[1].isEmpty()){
							try{
								boolean b = memory.isVerticallyLocked(sender.getName());
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
				else if(args[0].equalsIgnoreCase(VERSION)){
					messenger.sendVersionMessage(sender);
				}
			}else{
				messenger.sendConsoleHelpMessage(sender);
			}
		}
	}
	
	private void playerCommand(String label, String[] args, CommandSender sender){
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
					memory.toggleSetting(player);
					boolean b = memory.isVerticallyLocked(player);
					if(b){
						messenger.sendVerticalLock(sender);
					}else{
						messenger.sendNormalPlacement(sender);
					}
				}
				else if(args[0].equalsIgnoreCase(STATUS)){
					boolean b = memory.isVerticallyLocked(player);
					if(b){
						messenger.sendVerticalStatus(sender);
					}else{
						messenger.sendNormalStatus(sender);
					}
				}else{
					messenger.sendSyntaxMessage(sender);
				}
			}else{
				memory.toggleSetting(player);
				boolean b = memory.isVerticallyLocked(player);
				if(b){
					messenger.sendVerticalLock(sender);
				}else{
					messenger.sendNormalPlacement(sender);
				}
			}
		}
	}

}
