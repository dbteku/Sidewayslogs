package memory;

import interfaces.AuthorizedMemoryAccess;
import io.PlayerFileLoader;
import io.PlayerFileWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import language.Messenger;
import manager.ServerManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import player.PlayerSettings;

public class MemoryModule implements AuthorizedMemoryAccess{

	private PlayerFileLoader playerInput;
	private PlayerFileWriter playerOutput;
	private PlayerSettings playerSettings;
	private HashMap<String, Object> settings;
	private HashMap<String, HashMap<String, Object>> localMemory = new HashMap<>();
	private ConsoleCommandSender console;
	private Messenger messenger;

	public MemoryModule(ConsoleCommandSender console, Messenger messenger, PlayerSettings playerSettings){
		this.console = console;
		this.messenger = messenger;
		playerInput = new PlayerFileLoader();
		playerOutput = new PlayerFileWriter();
		settings = playerSettings.getSettings();
	}

	private void putInMemory(String playerName, HashMap<String, Object> settings){
		if(!localMemory.containsKey(playerName)){
			localMemory.put(playerName, settings);
		}
	}

	public HashMap<String, Object> getPlayerSettings(AuthorizedMemoryAccess sender, String playerName){

		HashMap<String, Object> playerSettings = new HashMap<>();

		if(sender instanceof AuthorizedMemoryAccess){
			playerSettings = localMemory.get(playerName);
		}else{
			// UnAuthorized sender.
		}

		return playerSettings;
	}

	public void loadFromDisk(AuthorizedMemoryAccess sender, String playerName){
		if(sender instanceof AuthorizedMemoryAccess){
			HashMap<String, Object> loadedFile = playerInput.loadPlayerFile(playerName);
			if(!loadedFile.isEmpty()){
				putInMemory(playerName, loadedFile);

			}else{
				if(playerIsOnline(playerName)){
					playerOutput.createNewFile(playerName, settings);
				}
				loadedFile = playerInput.loadPlayerFile(playerName);
				if(!loadedFile.isEmpty()){
					putInMemory(playerName,loadedFile);
				}
			}
		}
	}

	public void writeToDisk(AuthorizedMemoryAccess sender, String playerName){
		if(sender instanceof AuthorizedMemoryAccess){
			playerOutput.update(playerName, localMemory.get(playerName));
			if(!playerIsOnline(playerName)){
				localMemory.remove(playerName);
			}
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

	public void toggleSetting(String playerName, String setting){
		HashMap<String, Object> settings = new HashMap<>();
		boolean isLocked = false;
		settings = localMemory.get(playerName);
		isLocked = (boolean) settings.get(setting);
		isLocked = !isLocked;
		System.out.println("IS LOCKED " + isLocked);
		settings.replace(setting, isLocked);
		localMemory.replace(playerName, settings);
	}

	public boolean getSetting(String playerName, String setting){
		HashMap<String, Object> settings = new HashMap<>();
		boolean isLocked = false;
		if(localMemory.containsKey(playerName)){
			settings = localMemory.get(playerName);
		}else{
			loadFromDisk(this, playerName);
			settings = localMemory.get(playerName);
		}
		isLocked = (boolean) settings.get(setting);

		if(!playerIsOnline(playerName)){
			memoryCleanup();
		}

		return isLocked;
	}

	private void memoryCleanup() {

		Set<String> keys = localMemory.keySet();
		List<Player> playersEntity = (List<Player>) Bukkit.getOnlinePlayers();
		List<String> players = new ArrayList<String>();
		for(Player p: playersEntity){
			players.add(p.getName());
		}
		if(!localMemory.isEmpty()){
			for(String s: keys){
				if(!players.contains(s)){
					localMemory.remove(s);
				}
			}
		}
	}

	public void checkCurrentPlayers(){
		List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
		if(!players.isEmpty()){
			for(Player p: players){
				this.loadFromDisk(this, p.getName());
			}
		}
	}

	public boolean playerIsOnline(String playerName){
		List<Player> playersEntity = (List<Player>) Bukkit.getOnlinePlayers();
		List<String> players = new ArrayList<String>();
		for(Player p: playersEntity){
			players.add(p.getName());
		}
		return players.contains(playerName);
	}

	public void forceSave() {

		Set<String> memory = localMemory.keySet();

		for(String s: memory){
			writeToDisk(this,s);
		}
	}
}
