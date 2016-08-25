package project.source;

import org.eclipse.ui.IWorkbenchWindow;

import project.model.Graph;

public interface ISource {
	
	public Graph getSource(IWorkbenchWindow window);
	
}
