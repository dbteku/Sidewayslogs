package memory;

import interfaces.AuthorizedMemoryAccess;
import io.ServerReader;
import io.ServerWriter;

import java.util.HashMap;
import java.util.Iterator;

import language.Messenger;

import org.bukkit.command.ConsoleCommandSender;

import server.ServerSettings;

public class ServerMemory {
	
	private final String SERVER = "server";
	private ServerWriter writer;
	private ServerReader reader;
	private HashMap<String, HashMap<String, Boolean>> localMemory = new HashMap<>();
	private ConsoleCommandSender console;
	private Messenger messenger;
	private ServerSettings settings;
	
	public ServerMemory(ConsoleCommandSender console, Messenger messenger, ServerSettings serverSettings){
		this.console = console;
		this.messenger = messenger;
		this.settings = serverSettings;
	}
	
	public void loadFromDisk(AuthorizedMemoryAccess sender){
		if(sender instanceof AuthorizedMemoryAccess){
			HashMap<String, Boolean> loadedFile = reader.loadPlayerFile(SERVER);
			if(!loadedFile.isEmpty()){
				putInMemory(SERVER, loadedFile);
			}else{
				writer.createNewFile(SERVER, settings);
				loadedFile = reader.loadPlayerFile(SERVER);
				if(!loadedFile.isEmpty()){
					putInMemory(SERVER,loadedFile);
				}
			}
		}
	}
	
	private boolean playerIsOnline(String playerName) {
		// TODO Auto-generated method stub
		return true;
	}

	private void putInMemory(String playerName, HashMap<String,Boolean> file){
		
	}

	public void logMemory() {
		
		Iterator<String> settings = localMemory.keySet().iterator();
		String s = "";
		while(settings.hasNext()){
			s = settings.next();
			System.out.println(s);
		}
		
	}
	

}
