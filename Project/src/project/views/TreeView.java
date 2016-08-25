package project.views;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import project.model.ModelRoot;
import project.providers.MyLabelProvider;
import project.providers.MyTreeContentProvider;
import project.test.TestDataProvider;

public class TreeView extends ViewPart implements Observer {
	
	public static final String ID = "project.views.treeview";
	public TreeViewer treeViewer;
	
	public TreeView() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		treeViewer.refresh();
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		ModelRoot.getModelRootInstance().addObserver(this);
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer.setContentProvider(new MyTreeContentProvider());
		treeViewer.getTree().setHeaderVisible(true);
		treeViewer.setLabelProvider(new MyLabelProvider());
		TestDataProvider tdp = new TestDataProvider();
		treeViewer.setInput(tdp.getNodes());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
