package com.dbteku.memory;

import com.dbteku.interfaces.TimerListener;
import com.dbteku.tools.Timer;

public class AutoSaver implements TimerListener{

	private final int MS = 1000;
	private boolean isRunning = true;
	private PlayerMemory memory;
	private int interval;
	
	public AutoSaver(int saveIntervalMinutes, PlayerMemory memory) {
		this.interval = saveIntervalMinutes;
		this.memory = memory;
	}
	
	public void start(){
		int seconds = convertMinutesToSeconds(interval);
		createNewTimer(seconds  * MS);
	}
	
	public void stop(){
		this.isRunning = !isRunning;
	}
	
	@Override
	public void onTimerComplete(Timer t) {
		synchronized(memory){
			memory.forceSave();
			createNewTimer(t.getTime());
		}
	}
	
	private int convertMinutesToSeconds(int time){
		return time * 60;
	}
	
	private void createNewTimer(int secondInterval){
		Timer timer = new Timer(secondInterval);
		timer.register(this);
		timer.startTimer();
	}
	
}
