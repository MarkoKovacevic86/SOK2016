package rcpproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Graph {
	public static enum Layouts {Radial, Grid, Spring, Tree};
	
	protected String id;
	static AtomicInteger nextId = new AtomicInteger();
	protected String location;
	
	protected List<GraphNode> nodes = new ArrayList<GraphNode>();
	
	public Graph(String location){
		id = Integer.toString(nextId.incrementAndGet());
		this.location = location;
	}
	
	public int getNumberOfNodes(){
		return nodes.size();
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public List<GraphNode> getNodes() {
		return nodes;
	}
	
}
