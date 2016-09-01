package rcpproject.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import rcpproject.model.Graph;
import rcpproject.model.GraphNode;

public class ViewerContentProvider implements IGraphEntityContentProvider{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getConnectedTo(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 instanceof GraphNode){
			try{
				GraphNode gn = (GraphNode) arg0;
				List<Object> list = new ArrayList<Object>();
				for(GraphNode n : gn.getChildNodes()){
					list.add(n);
				}return list.toArray();
			}catch (NullPointerException e) {
				// TODO: handle exception
				return null;
			}
		}else
			return null;
	}

	@Override
	public Object[] getElements(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Graph){
			Graph g = (Graph)arg0;
			List<Object> list = new ArrayList<Object>();
			for(GraphNode node : g.getNodes()){
				list.add(node);
			}return list.toArray();
		}return new Object[] {arg0};
	}

}
