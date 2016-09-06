package rcpproject.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.zest.core.viewers.GraphViewer;

import rcpproject.model.Graph;
import rcpproject.model.ModelRoot;
import rcpproject.source.ISource;
import rcpproject.source.SourceDescription;
import rcpproject.source.SourceManager;
import rcpproject.view.SelectionDialog;
import rcpproject.viewers.IViewer;
import rcpproject.viewers.ViewerDescription;
import rcpproject.viewers.ViewerManager;
import rcpproject.views.MainView;

public class GraphHandler extends AbstractHandler {
	IWorkbenchWindow window;
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindow(event);
		SelectionDialog sd = new SelectionDialog(window.getShell());
		int response = sd.open();
		if(response == Window.OK){
			try{
				ISource source = getGraphFromSourceDesc(sd.getSourceName());
				Graph graph = source.getSource(window);
				ModelRoot root = ModelRoot.getModelRootInstance();
				window.getActivePage();
				IViewPart view = window.getActivePage().findView("rcpproject.views.mainview");
				if(graph != null && !root.getLoadedGraphs().containsKey(graph.getLocation())){
					IViewer viewer = getViewerFromViewerDesc("rcpproject.viewer");
					if(viewer != null && view != null){
						MainView mv = (MainView)view;
						mv.setContentProvider(viewer.getContentProvider());
						mv.setLableProvider(viewer.getLabelProvider());
						mv.setInput(graph);
						
					}
					root.addGraph(graph);
				}
			}catch(Exception e){e.printStackTrace();}
		}
		
		return null;
		// TODO Auto-generated method stub
		
	}
	
	public ISource getGraphFromSourceDesc(String id){
		for(SourceDescription desc : SourceManager.getInstance().getSources()){
			if(id.equals(desc.getId())){
				return SourceManager.getInstance().createSource(desc.getId());
			}
		}return null;
	}
	
	
	public IViewer getViewerFromViewerDesc(String id) {
		for (ViewerDescription desc : ViewerManager.getInstance().getViewers()) {
			if (id.equals(desc.getId())) {
				return ViewerManager.getInstance().createViewer(desc.getId());
			}
		}
		return null;
	}
	

}
