package rcpproject.java.source;

import java.io.IOException;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;

import rcpproject.java.model.JClass;
import rcpproject.model.Graph;
import rcpproject.source.ISource;

public class JavaSource implements ISource {

	String location;
	
	@Override
	public Graph getSource(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		SourceDialog dlg = new SourceDialog(window);
		int response = dlg.open();
		if(response == Window.OK){
			location = dlg.getLocation();
			JClass jclass = null;
			try {
				jclass = new JClass(location);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jclass;
			
		}
		return null;
	}

}
