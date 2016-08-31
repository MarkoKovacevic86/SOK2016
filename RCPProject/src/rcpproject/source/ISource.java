package rcpproject.source;

import org.eclipse.ui.IWorkbenchWindow;

import rcpproject.model.Graph;

public interface ISource {
	
	public Graph getSource(IWorkbenchWindow window);
}
