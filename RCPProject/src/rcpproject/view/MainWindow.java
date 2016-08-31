package rcpproject.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import rcpproject.views.MainView;





public class MainWindow{
	
	
	
	public MainWindow(){
		Display display = new Display();
		Shell shell = new Shell(display,SWT.SHELL_TRIM);
		MainView mv = new MainView();
		mv.createPartControl(shell);
		shell.open();

		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}display.dispose();
		
	}
	

}
