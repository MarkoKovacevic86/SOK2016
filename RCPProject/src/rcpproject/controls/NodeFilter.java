package rcpproject.controls;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import rcpproject.model.GraphNode;
import rcpproject.model.Property;

public class NodeFilter extends ViewerFilter {

	private String filterWord = "";
	
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(!filterWord.trim().equals("")){
			if(element instanceof GraphNode){
				GraphNode graphNode = (GraphNode)element;
				return graphNode.getName().toLowerCase().contains(filterWord.toLowerCase());
				
			}
			if(element instanceof Property){
				Property property = (Property)element;
				return property.getType().toLowerCase().contains(filterWord.toLowerCase());
			}
		}
		return true;
	}


	public String getFilterWord() {
		return filterWord;
	}


	public void setFilterWord(String filterWord) {
		this.filterWord = filterWord;
	}
	
	

}
