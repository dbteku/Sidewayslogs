package memory;

import java.util.ArrayList;
import java.util.HashMap;

import manager.ServerManager;
import player.PlayerSettings;
import interfaces.AuthorizedMemoryAccess;
import io.Input;
import io.Output;

public class MemoryModule {

	private Input input;
	private Output output;
	private PlayerSettings playerSettings = new PlayerSettings();
	private HashMap<String, Boolean> settings;
	HashMap<String, HashMap<String, Boolean>> localMemory = new HashMap<>();

	public MemoryModule(){
		input = new Input();
		output = new Output();
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
			HashMap<String, Boolean> loadedFile = input.loadPlayerFile(playerName);
			if(!loadedFile.isEmpty()){
				putInMemory(playerName, loadedFile);

			}else{
				output.createNewFile(playerName, settings);
				loadedFile = input.loadPlayerFile(playerName);
				putInMemory(playerName,loadedFile);
			}
		}
	}

	public void writeToDisk(AuthorizedMemoryAccess sender, String playerName){
		if(sender instanceof AuthorizedMemoryAccess){
			output.update(playerName, localMemory.get(playerName));
			localMemory.remove(playerName);
		}
	}
	
	public void checkDirectory(ServerManager manager){
		if(manager instanceof ServerManager){
			boolean b = input.checkDirectory();
			if(!b){
				output.initDirectory();
			}
		}
	}
}
