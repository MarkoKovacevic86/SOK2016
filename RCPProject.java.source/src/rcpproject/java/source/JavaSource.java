package rcpproject.java.source;

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
			JClass jclass = new JClass(location);
			return jclass;
			
		}
		return null;
	}

}
