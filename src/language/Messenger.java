package language;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class Messenger {

	private final String SWL = ChatColor.GREEN + "[" + ChatColor.AQUA + "SWL" + ChatColor.GREEN + "] ";
	private final String SYNTAX = ChatColor.RED +  "Syntax: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl help";
	private final String SPACE = " ";
	private final String COMMANDS = ChatColor.GRAY + "Commands:";
	private final String NEW_LINE = "\n";
	private final String TOGGLE = ChatColor.GRAY + "Toggle Placement: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl toggle";
	private final String VERSION_COMMAND = ChatColor.GRAY + "Show Version: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl version";
	private final String STATUS_COMMAND = ChatColor.GRAY + "Current Placement: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl status";
	private final String VERSION_MESSAGE;
	private final String CONSOLE_MESSAGE = ChatColor.RED + "Sorry Console Commands Are For Players.";
	private String name;
	private String version;
	private ConsoleCommandSender console;

	public Messenger(String pluginName, String pluginVersion, ConsoleCommandSender console){
		this.name = pluginName;
		this.version = pluginVersion;
		this.console = console;
		VERSION_MESSAGE = ChatColor.GRAY + "Version:" + SPACE + version;
	}

	public void sendHelpMessage(CommandSender sender){
		sender.sendMessage(SWL + COMMANDS + NEW_LINE + TOGGLE + NEW_LINE + VERSION_COMMAND + NEW_LINE + STATUS_COMMAND);
	}

	public void sendSyntaxMessage(CommandSender sender) {
		sender.sendMessage(SWL + SYNTAX);
	}

	public void sendVersionMessage(CommandSender sender) {
		sender.sendMessage(SWL + VERSION_MESSAGE);
	}
	
	public void sendConsoleCommand(CommandSender sender){
		sender.sendMessage(SWL + CONSOLE_MESSAGE);
	}


}
