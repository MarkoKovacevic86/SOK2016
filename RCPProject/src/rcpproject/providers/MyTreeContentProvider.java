package rcpproject.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import rcpproject.model.Graph;
import rcpproject.model.GraphNode;
import rcpproject.model.ModelRoot;

public class MyTreeContentProvider implements ITreeContentProvider{
	
	private ModelRoot model;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		this.model = (ModelRoot) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		List<Graph> list = new ArrayList<Graph>(model.getLoadedGraphs().values());
		return list.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if(parentElement instanceof Graph){
			Graph graph = (Graph) parentElement;
				return graph.getNodes().toArray();
		}else if(parentElement instanceof GraphNode){
			GraphNode gn = (GraphNode)parentElement;
			return gn.getNodeProperties().toArray();
		}return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		if(element instanceof Graph || element instanceof GraphNode){
			return true;
		}return false;
	} 

}
