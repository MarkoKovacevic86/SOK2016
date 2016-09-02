package rcpproject.viewer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import rcpproject.viewers.IViewer;

public class BlockViewer implements IViewer {

	@Override
	public LabelProvider getLabelProvider() {
		// TODO Auto-generated method stub
		return new ViewerLabelProvider();
	}

	@Override
	public IGraphEntityContentProvider getContentProvider() {
		// TODO Auto-generated method stub
		return new ViewerContentProvider();
	}

}
