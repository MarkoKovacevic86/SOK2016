package project.test;

import java.util.ArrayList;
import java.util.List;

import project.model.GraphNode;
import project.model.Property;

public class TestDataProvider {
	private List<GraphNode> nodes;
	
	public TestDataProvider(){
		nodes = new ArrayList<GraphNode>();
		GraphNode node = new GraphNode("1", "Hamburg");
		nodes.add(node);
		GraphNode node2 = new GraphNode("2", "Frankfurt");
		node.addChildNode(node2);
		nodes.add(node2);
		Property prop1 = new Property("Test", "typetpye");
		node.addNodeProperty(prop1);
		
	}
	
	public List<GraphNode> getNodes(){
		return nodes;
	}
}
