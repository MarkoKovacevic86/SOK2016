package rcpproject.views;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import rcpproject.model.GraphNode;
import rcpproject.model.ModelRoot;
import rcpproject.model.Property;
import rcpproject.providers.MyLabelProvider;
import rcpproject.providers.MyTreeContentProvider;

public class TreeView extends ViewPart implements Observer{
	
	public static final String ID = "rcpproject.views.treeview";
	private TreeViewer tView;
	
	public TreeView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		tView.refresh();
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		ModelRoot.getModelRootInstance().addObserver(this);
		initView(parent);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	private void initView(Composite parent){
		tView = new TreeViewer(parent, SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL);
		tView.setContentProvider(new MyTreeContentProvider());
		tView.setLabelProvider(new MyLabelProvider());
		tView.setInput(ModelRoot.getModelRootInstance());
		tView.expandAll();
		tView.refresh();
		
	}
	
}
