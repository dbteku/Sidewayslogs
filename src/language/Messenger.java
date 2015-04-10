package language;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Messenger {

	private final String SYNTAX = ChatColor.RED +  "Syntax: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl help";
	private final String SPACE = " ";
	private final String COMMANDS = ChatColor.GRAY + "Commands:";
	private final String NEW_LINE = "\n";
	private final String TOGGLE = ChatColor.GRAY + "Toggle Placement: " + ChatColor.GREEN + "/" + ChatColor.AQUA + "swl toggle";
	private final String VERSION_MESSAGE;
	private String name;
	private String version;
	private ConsoleCommandSender console;

	public Messenger(String pluginName, String pluginVersion, ConsoleCommandSender console){
		this.name = pluginName;
		this.version = pluginVersion;
		this.console = console;
		VERSION_MESSAGE = ChatColor.GRAY + "Version: " + name + SPACE + version;
	}

	public void sendHelpMessage(CommandSender sender){
		console.sendMessage(name);
		console.sendMessage(version);
		sender.sendMessage(COMMANDS + NEW_LINE + TOGGLE + NEW_LINE + VERSION_MESSAGE);
	}

	public void sendSyntaxMessage(CommandSender sender) {
		sender.sendMessage(SYNTAX);
	}

	public void sendVersionMessage(ConsoleCommandSender sender) {
		sender.sendMessage(VERSION_MESSAGE);

	}


}
