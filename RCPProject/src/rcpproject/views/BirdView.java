package rcpproject.views;

import org.eclipse.draw2d.SWTEventDispatcher;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;

import rcpproject.model.Graph;
import rcpproject.viewers.IViewer;
import rcpproject.viewers.ViewerDescription;
import rcpproject.viewers.ViewerManager;

public class BirdView extends ViewPart {

	public static String ID = "rcpproject.views.birdview";
	
	private GraphViewer viewer = null;
	private String visualizerID;
	
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		this.viewer = new GraphViewer(parent, SWT.BORDER);
		this.viewer.setLayoutAlgorithm(new RadialLayoutAlgorithm());
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void refresh(GraphViewer viewer, String first){
		if(first != null){
			MainView mainView = (MainView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(MainView.ID);
			if(viewer.getGraphControl().getNodes().size() == 0){
				return;
			}
			IViewer view = getViewerFromViewerDescription(visualizerID);
			this.viewer.setContentProvider(view.getContentProvider());
			this.viewer.setLabelProvider(view.getLabelProvider());
			this.viewer.setLayoutAlgorithm(mainView.getLayoutAlgorithm());
			//this.viewer.setInput(input);
			this.viewer.applyLayout();
			this.viewer.getGraphControl().getLightweightSystem().setEventDispatcher(new SWTEventDispatcher(){
				
			});
		}
		int i = 0;
		for(Object node : viewer.getGraphControl().getNodes()){
			if(node instanceof GraphNode){
				GraphNode tempNode = (GraphNode)node;
				tempNode.setLocation(((GraphNode)node).getLocation().x, ((GraphNode)node).getLocation().y);
				i++;
			}
		}this.viewer.getGraphControl().setScrollBarVisibility(org.eclipse.zest.core.widgets.Graph.NEVER);
	}
	
	public IViewer getViewerFromViewerDescription(String id){
		for(ViewerDescription desc : ViewerManager.getInstance().getViewers()){
			if(id.equals(desc.getId())){
				return ViewerManager.getInstance().createViewer(desc.getId());
			}
		}return null;
	}

	public static String getID() {
		return ID;
	}

	public static void setID(String iD) {
		ID = iD;
	}
	
	

}
