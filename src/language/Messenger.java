package language;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Messenger {

	private final String SWL = ChatColor.GREEN + "[" + ChatColor.AQUA + "SWL" + ChatColor.GREEN + "] ";
	private final String ENABLED = ChatColor.GREEN + "Has Been Enabled!";
	private final String DISABLED = ChatColor.RED + "Has Been Disabled!";
	private final String SYNTAX = ChatColor.RED +  "Syntax: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl help";
	private final String SPACE = " ";
	private final String COMMANDS = ChatColor.GRAY + "Commands:";
	private final String NAME; 
	private final String NEW_LINE = "\n";
	private final String TOGGLE = ChatColor.GRAY + "Toggle Placement: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl toggle";
	private final String VERSION_COMMAND = ChatColor.GRAY + "Show Version: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl version";
	private final String STATUS_COMMAND = ChatColor.GRAY + "Current Placement: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl status";
	private final String VERSION_MESSAGE;
	private final String CONSOLE_STATUS = ChatColor.GRAY + "Certain Player's Status: " + ChatColor.AQUA + "swl status " + ChatColor.GREEN + "[player]";
	private final String CONSOLE_VERSION = ChatColor.GRAY + "Sideways Log Version: " + ChatColor.AQUA + "swl version";
	private final String CONSOLE_MESSAGE = ChatColor.RED + "Sorry Console Commands Are For Players.";
	private final String SAVING = ChatColor.AQUA + "Saving Player Data!";
	private final String TOGGLE_LOCK = ChatColor.GRAY + "You are now placing logs vertically.";
	private final String TOGGLE_NORMAL = ChatColor.GRAY + "You are now placing logs normally.";
	private final String STATUS_LOCK = ChatColor.GRAY + "You are placing logs vertically.";
	private final String STATUS_NORMAL = ChatColor.GRAY + "You are placing logs normally.";
	private final String CONSOLE_STATUS_LOCK = ChatColor.GRAY + "Is placing logs vertically.";
	private final String CONSOLE_STATUS_NORMAL = ChatColor.GRAY + "Is placing logs normally.";
	private String name;
	private String version;

	public Messenger(String pluginName, String pluginVersion){
		this.name = pluginName;
		this.version = pluginVersion;
		VERSION_MESSAGE = ChatColor.GRAY + "Version:" + SPACE + version;
		NAME = name;
	}
	
	public void sendEnableMessage(CommandSender sender){
		sender.sendMessage(SWL + NAME + SPACE + version + SPACE + ENABLED);
	}
	
	public void sendDisableMessage(CommandSender sender){
		sender.sendMessage(SWL + NAME + SPACE + version + SPACE + DISABLED);
	}

	public void sendHelpMessage(CommandSender sender){
		sender.sendMessage(SWL + COMMANDS + NEW_LINE + TOGGLE + NEW_LINE + VERSION_COMMAND + NEW_LINE + STATUS_COMMAND);
	}

	public void sendSyntaxMessage(CommandSender sender) {
		sender.sendMessage(SWL + SYNTAX);
	}

	public void sendVersionMessage(CommandSender sender) {
		sender.sendMessage(SWL + NAME + SPACE + VERSION_MESSAGE);
	}
	
	public void sendConsoleMessage(CommandSender sender){
		sender.sendMessage(SWL + CONSOLE_MESSAGE);
	}
	
	public void sendSavingMessage(CommandSender sender){
		sender.sendMessage(SWL + SAVING);
	}
	
	public void sendVerticalLock(CommandSender sender){
		sender.sendMessage(SWL + TOGGLE_LOCK);
	}
	
	public void sendNormalPlacement(CommandSender sender){
		sender.sendMessage(SWL + TOGGLE_NORMAL);
	}
	
	public void sendVerticalStatus(CommandSender sender){
		sender.sendMessage(SWL + STATUS_LOCK);
	}
	
	public void sendNormalStatus(CommandSender sender){
		sender.sendMessage(SWL + STATUS_NORMAL);
	}
	
	public void sendConsoleNormalStatus(CommandSender sender, String playerName){
		sender.sendMessage(SWL + ChatColor.GREEN + playerName + SPACE + CONSOLE_STATUS_NORMAL);
	}
	
	public void sendConsoleVerticalStatus(CommandSender sender, String playerName){
		sender.sendMessage(SWL + ChatColor.GREEN + playerName + SPACE + CONSOLE_STATUS_LOCK);
	}
	
	public void sendConsoleHelpMessage(CommandSender sender){
		sender.sendMessage(NEW_LINE + SWL + CONSOLE_STATUS + NEW_LINE + SWL + CONSOLE_VERSION);
	}
	
	
}
