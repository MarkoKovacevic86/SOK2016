package project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Property {
	static AtomicInteger nextId = new AtomicInteger();
	
	protected String name;
	protected String type;
	protected String id;
	protected List<GraphNode> parentNodes = new ArrayList<GraphNode>();

	public Property(String name, String type){
		this.name = name;
		this.type = type;
		id = "property: " + Integer.toString(nextId.incrementAndGet());
	}
	
	public Property(String name, String type, GraphNode parentNode){
		this(name,type);
		this.parentNodes.add(parentNode);
	}

	public List<GraphNode> getParentNodes() {
		return parentNodes;
	}

	public void setParentNode(GraphNode parentNode) {
		this.parentNodes.add(parentNode);
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
	
	
}
