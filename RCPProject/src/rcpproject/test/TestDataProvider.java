package rcpproject.test;

import java.util.ArrayList;
import java.util.List;

import rcpproject.model.GraphNode;
import rcpproject.model.Property;

public class TestDataProvider {
	private List<Object> nodes;
	
	public TestDataProvider(){
		nodes = new ArrayList<Object>();
		GraphNode node = new GraphNode("1", "Hamburg");
		nodes.add(node);
		GraphNode node2 = new GraphNode("2", "Frankfurt");
		node.addChildNode(node2);
		nodes.add(node2);
		Property prop1 = new Property("Test", "typetpye",node);
		node.addNodeProperty(prop1);
		prop1.setParentNode(node);
		nodes.add(prop1);
	}
	
	public List<Object> getNodes(){
		return nodes;
	}
}
