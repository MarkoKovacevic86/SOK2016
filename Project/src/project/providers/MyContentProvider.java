package project.providers;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import project.model.GraphNode;
import project.model.Property;

public class MyContentProvider extends ArrayContentProvider implements IGraphEntityContentProvider{

	@Override
	public Object[] getConnectedTo(Object entity) {
		// TODO Auto-generated method stub
		if(entity instanceof GraphNode){
			GraphNode gnode = (GraphNode)entity;
			return gnode.getChildNodes().toArray();
		}else if(entity instanceof Property){
			Property prop = (Property)entity;
			return prop.getParentNodes().toArray();
		}
		return null;
	}

}
