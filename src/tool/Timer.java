package tool;

import interfaces.TimerListener;

public class Timer {

	private int time;
	private TimerListener listener;
	private Timer timer;

	public Timer(int time){
		this.time = time;
		this.timer = this;
	}

	public void startTimer(){
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				try {

					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				listener.onTimerComplete(timer);
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
