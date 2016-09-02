package rcpproject.viewer;

import javax.lang.model.element.Element;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.zest.core.viewers.IFigureProvider;

import rcpproject.model.GraphNode;

public class ViewerLabelProvider extends LabelProvider implements IFigureProvider {

	private Image img;
	
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		if(element instanceof GraphNode)
			return img;
		return null;
	}
	
	@Override
	public String getText(Object element) {
		if(element instanceof GraphNode){
			GraphNode gn = (GraphNode) element;
			return gn.getName();
		}return "";
	}
	
	@Override
	public IFigure getFigure(Object arg0) {
		if(arg0 instanceof GraphNode){
			
		}
		return null;
	}

}
