package com.dbteku.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.dbteku.controllers.ServerManager;
import com.dbteku.io.PlayerFileLoader;
import com.dbteku.io.PlayerFileWriter;
import com.dbteku.language.Messenger;
import com.dbteku.models.VerticalLockSetting;

public class PlayerMemory{

	private PlayerFileLoader playerInput;
	private PlayerFileWriter playerOutput;
	private Map<String, VerticalLockSetting> localMemory;

	public PlayerMemory(ConsoleCommandSender console, Messenger messenger){
		playerInput = new PlayerFileLoader();
		playerOutput = new PlayerFileWriter();
		localMemory = new HashMap<>();
	}

	public void loadFromDisk(String playerName){
		VerticalLockSetting setting = playerInput.loadSettingFromFile(playerName);
		if(!localMemory.containsKey(playerName)){
			localMemory.put(playerName, setting);
		}
	}

	public void writeToDisk(VerticalLockSetting setting){
		playerOutput.writeSettingToDisk(setting);
	}

	public void checkDirectory(ServerManager manager){
		boolean exists = playerInput.doesDirectoryExist();
		if(!exists){
			playerOutput.initDirectory();
		}
	}

	public void toggleSetting(String playerName){
		boolean isLocked = false;
		VerticalLockSetting setting = localMemory.get(playerName);
		isLocked = setting.isEnabled();
		isLocked = !isLocked;
		setting.setEnabled(isLocked);
		writePlayerSettingToDisk(playerName);
	}

	public boolean isVerticallyLocked(String playerName){
		return localMemory.get(playerName).isEnabled();
	}

	@SuppressWarnings("unchecked")
	public void checkCurrentPlayers(){
		List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
		if(!players.isEmpty()){
			for(Player p: players){
				loadFromDisk(p.getName());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean playerIsOnline(String playerName){
		List<Player> playersEntity = (List<Player>) Bukkit.getOnlinePlayers();
		List<String> players = new ArrayList<String>();
		for(Player p: playersEntity){
			players.add(p.getName());
		}
		return players.contains(playerName);
	}

	public void forceSave(){
		Iterator<String> memory = localMemory.keySet().iterator();
		String key = "";
		while(memory.hasNext()){
			key = memory.next();
			VerticalLockSetting setting = localMemory.get(key);
			writeToDisk(setting);
		}
	}

	public void forceSaveAndRemovePlayers() {
		Iterator<String> memory = localMemory.keySet().iterator();
		String key = "";
		while(memory.hasNext()){
			key = memory.next();
			VerticalLockSetting setting = localMemory.get(key);
			memory.remove();
			writeToDisk(setting);
		}
	}
	
	public void writePlayerSettingToDiskAndRemove(String playerName){
		writePlayerSettingToDisk(playerName);
		localMemory.remove(playerName);
	}

	public void writePlayerSettingToDisk(String playerName) {
		VerticalLockSetting setting = localMemory.get(playerName);
		playerOutput.writeSettingToDisk(setting);
	}
}
