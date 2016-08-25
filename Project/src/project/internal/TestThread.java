package project.internal;

import project.view.MainWindow;

public class TestThread extends Thread {
	private volatile boolean active = true;
	
	public void run(){
		while(active){
			try{
				MainWindow mw = new MainWindow();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void stopThread(){
		active = false;
	}
}
