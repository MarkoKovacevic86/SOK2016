package project.test;

import java.util.ArrayList;
import java.util.List;

import project.model.GraphNode;

public class TestDataProvider {
	private List<GraphNode> nodes;
	
	public TestDataProvider(){
		nodes = new ArrayList<GraphNode>();
		GraphNode node = new GraphNode("1", "Hamburg");
		nodes.add(node);
		node = new GraphNode("2", "Frankfurt");
		nodes.add(node);
		
	}
	
	public List<GraphNode> getNodes(){
		return nodes;
	}
}
