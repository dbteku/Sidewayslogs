package tool;

import interfaces.TimerListener;

public class Timer {

	private int time;
	private TimerListener listener;
	private Timer timer;

	public Timer(int timeInSeconds){
		this.time = (timeInSeconds * 1000);
		this.timer = this;
	}

	public void startTimer(){
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				boolean run = true;
				long startTime = System.currentTimeMillis();
				long endTime = 0;
				while(run){
					endTime = System.currentTimeMillis();
					long difference = endTime - startTime;
					run = difference <= time;
					if(!run){
						listener.onTimerComplete(timer);
					}
				}
			}

		});
		thread.start();
	}

	public void register(TimerListener listener){
		this.listener = listener;
	}

	public int getTime() {
		return time;
	}

}
