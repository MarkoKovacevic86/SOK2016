package project.internal;

import project.view.MainWindow;

public class TestThread extends Thread {
	private volatile boolean active = true;
	
	public void run(){
		while(active){
			try{
				MainWindow mw = new MainWindow();
			}catch(Exception e){
				System.out.println("Test thread interupted");
			}
		}
	}
	
	public void stopThread(){
		active = false;
	}
}
