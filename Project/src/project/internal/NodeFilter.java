package project.internal;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import project.model.GraphNode;
import project.model.Property;

public class NodeFilter extends ViewerFilter {
	
	private String filterWord = "";

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO Auto-generated method stub
		if(element instanceof GraphNode){
			GraphNode node = (GraphNode)element;
			if(!filterWord.trim().equals("")){
				return node.getName().toLowerCase().contains(filterWord.toLowerCase());
			}return true;
		}
		else if(element instanceof Property){
			Property node = (Property) element;
			if(!filterWord.trim().equals("")){
				return node.getType().toLowerCase().contains(filterWord.toLowerCase());
			}return true;
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
