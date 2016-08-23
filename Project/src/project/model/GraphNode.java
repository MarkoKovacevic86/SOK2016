package project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphNode {
	static AtomicInteger nextId = new AtomicInteger();
	protected String name;
	protected String type;
	protected String id;
	protected List<Property> nodeProperties = new ArrayList<Property>();
	protected List<GraphNode> childNodes = new ArrayList<GraphNode>();
	
	public GraphNode(String name, String type){
		this.name = name;
		this.type = type;
		id = "Node: " + Integer.toString(nextId.incrementAndGet());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Property> getNodeProperties() {
		return nodeProperties;
	}

	public void setNodeProperties(List<Property> nodeProperties) {
		this.nodeProperties = nodeProperties;
	}

	public List<GraphNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<GraphNode> childNodes) {
		this.childNodes = childNodes;
	}
	
	
}