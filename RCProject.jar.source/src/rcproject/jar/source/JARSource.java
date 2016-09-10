package rcproject.jar.source;

import java.util.jar.JarFile;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;

import rcpproject.jar.model.JARFile;
import rcpproject.model.Graph;
import rcpproject.source.ISource;


public class JARSource implements ISource{

	String location;
	
	@Override
	public Graph getSource(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		SourceDialog dlg = new SourceDialog(window);
		int response = dlg.open();
		if(response == Window.OK){
			location = dlg.getLocation();
			JARFile file = new JARFile(location);
			return file;
		}
		return null;
	}

	

}
