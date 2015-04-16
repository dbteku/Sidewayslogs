package memory;

import interfaces.AuthorizedMemoryAccess;
import io.PlayerFileLoader;
import io.PlayerFileWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import language.Messenger;
import manager.ServerManager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import player.PlayerSettings;

public class MemoryModule implements AuthorizedMemoryAccess{

	private PlayerFileLoader playerInput;
	private PlayerFileWriter playerOutput;
	private PlayerSettings playerSettings;
	private HashMap<String, Boolean> settings;
	private HashMap<String, HashMap<String, Boolean>> localMemory = new HashMap<>();
	private ConsoleCommandSender console;
	private Messenger messenger;

	public MemoryModule(ConsoleCommandSender console, Messenger messenger, PlayerSettings playerSettings){
		this.console = console;
		this.messenger = messenger;
		playerInput = new PlayerFileLoader();
		playerOutput = new PlayerFileWriter();
		settings = playerSettings.getSettings();
	}

	private void putInMemory(String playerName, HashMap<String, Boolean> settings){
		//System.out.println("RUNNING MEMORY");
		if(!localMemory.containsKey(playerName)){
			localMemory.put(playerName, settings);
		}
	}

	public HashMap<String, Boolean> getPlayerSettings(AuthorizedMemoryAccess sender, String playerName){

		HashMap<String, Boolean> playerSettings = null;

		if(sender instanceof AuthorizedMemoryAccess){
			playerSettings = localMemory.get(playerName);
		}else{
			// UnAuthorized sender.
		}

		return playerSettings;
	}

	public void loadFromDisk(AuthorizedMemoryAccess sender, String playerName){
		if(sender instanceof AuthorizedMemoryAccess){
			HashMap<String, Boolean> loadedFile = playerInput.loadPlayerFile(playerName);
			if(!loadedFile.isEmpty()){
				putInMemory(playerName, loadedFile);

			}else{
				playerOutput.createNewFile(playerName, settings);
				loadedFile = playerInput.loadPlayerFile(playerName);
				putInMemory(playerName,loadedFile);
			}
		}
	}

	public void writeToDisk(AuthorizedMemoryAccess sender, String playerName){
		if(sender instanceof AuthorizedMemoryAccess){
			playerOutput.update(playerName, localMemory.get(playerName));
			localMemory.remove(playerName);
		}
	}

	public void checkDirectory(ServerManager manager){
		if(manager instanceof ServerManager){
			boolean b = playerInput.checkDirectory();
			if(!b){
				playerOutput.initDirectory();
			}
		}
	}

	public void toggleVerticalLock(CommandSender sender, String setting){
		String playerName = sender.getName();
		HashMap<String,Boolean> settings = new HashMap<>();
		boolean isLocked = false;
		settings = localMemory.get(playerName);
		isLocked = settings.get(setting);
		isLocked = !isLocked;
		settings.replace(setting, isLocked);
		localMemory.replace(playerName, settings);
	}

	public boolean getVerticalLock(CommandSender sender, String setting){
		String playerName = sender.getName();
		HashMap<String,Boolean> settings = new HashMap<>();
		boolean isLocked = false;
		settings = localMemory.get(playerName);
		isLocked = settings.get(setting);

		return isLocked;

	}

	public void checkCurrentPlayers(){
		List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
		if(!players.isEmpty()){
			for(Player p: players){
				this.loadFromDisk(this, p.getName());
			}
		}
	}

	public void forceSave() {

		Set<String> memory = localMemory.keySet();

		for(String s: memory){
			writeToDisk(this,s);
		}

	}
}
