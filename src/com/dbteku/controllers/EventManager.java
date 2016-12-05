package com.dbteku.controllers;

import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import com.dbteku.events.OnBlockPlace;
import com.dbteku.events.OnPlayerJoin;
import com.dbteku.events.OnPlayerLeave;
import com.dbteku.main.SideWaysLogs;
import com.dbteku.memory.PlayerMemory;

public class EventManager {
	
	private PluginManager pm;
	private SideWaysLogs plugin;
	private OnBlockPlace onBlockPlace;
	private OnPlayerJoin onPlayerJoin;
	private OnPlayerLeave onPlayerLeave;
	private ArrayList<Listener> events;
	private PlayerMemory memory;
	
	public EventManager(SideWaysLogs plugin, PluginManager pm, PlayerMemory memory){
		this.memory = memory;
		this.plugin = plugin;
		this.pm = pm;
		this.events = new ArrayList<Listener>();
	}
	
	public void registerEvents(){
		onBlockPlace = new OnBlockPlace(plugin, memory);
		onPlayerJoin = new OnPlayerJoin(memory);
		onPlayerLeave = new OnPlayerLeave(memory);
		events.add(onBlockPlace);
		events.add(onPlayerJoin);
		events.add(onPlayerLeave);
		register();
	}
	
	private void register(){
		for(Listener l: events){
			pm.registerEvents(l, plugin);
		}
	}

}
