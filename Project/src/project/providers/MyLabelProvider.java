package project.providers;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import project.model.Graph;
import project.model.GraphNode;
import project.model.Property;

public class MyLabelProvider extends LabelProvider {
	
	private static final Image GRAPH = getImage("GRAPH.gif");
	private static final Image PAGE = getImage("PAGE.gif");
	private static final Image ATTRIBUTE = getImage("ATTRIBUTE.gif");
	
	public Image getImage(Object element){
		if(element instanceof Graph){
			return GRAPH;
		}else if(element instanceof GraphNode){
			return PAGE;
		}return ATTRIBUTE;
	}
	
	private static Image getImage(String file){
		Bundle bundle = FrameworkUtil.getBundle(MyLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();
	}
	
	public String getText(Object element){
		if(element instanceof Graph){
			Graph graph = (Graph) element;
			return graph.getLocation();
		}else if(element instanceof GraphNode){
			GraphNode gn = (GraphNode) element;
			return gn.getType() + " => " + gn.getName();
		}else if(element instanceof Property){
			Property property = (Property) element;
			return property.getType() + " => "+ property.getName();
		}return null;
	}
	
}
