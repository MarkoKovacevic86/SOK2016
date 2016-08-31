package rcpproject.viewers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

public interface IViewer {
	
	public LabelProvider getLabelProvider();
	public IGraphEntityContentProvider getContentProvider();
}
