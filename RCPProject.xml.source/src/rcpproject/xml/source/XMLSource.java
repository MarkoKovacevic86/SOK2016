package rcpproject.xml.source;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;

import rcpproject.model.Graph;
import rcpproject.source.ISource;
import rcpproject.xml.source.model.RootDoc;

public class XMLSource implements ISource{

	String location;
	
	@Override
	public Graph getSource(IWorkbenchWindow window) {
		SourceDialog dlg = new SourceDialog(window);
		int response = dlg.open();
		if (response == Window.OK) {
			location = dlg.getLocation();

			RootDoc xmldoc = new RootDoc(location);
			return xmldoc;
		}
		return null;
	}
}
