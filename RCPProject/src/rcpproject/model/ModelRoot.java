package rcpproject.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class ModelRoot extends Observable{
	
	private static ModelRoot instance;
	private Graph activeGraph;
	private Map<String, Graph> loadedGraphs;
	
	
	public ModelRoot() {
		// TODO Auto-generated constructor stub
		loadedGraphs = new HashMap<String, Graph>();
	}
	
	public static ModelRoot getModelRootInstance(){
		if(instance == null){
			instance = new ModelRoot();
		}return instance;
	}

	public Graph getActiveGraph() {
		return activeGraph;
	}

	public void setActiveGraph(Graph activeGraph) {
		this.activeGraph = activeGraph;
	}

	public Map<String, Graph> getLoadedGraphs() {
		return loadedGraphs;
	}
	
	public void addGraph(Graph g){
		if(!loadedGraphs.containsKey(g.id)){
			loadedGraphs.clear();
			System.out.println(g.id);
			loadedGraphs.put(g.getId(), g);
			activeGraph = g;
			setChanged();
			notifyObservers();
		}
	}
	
	public void removeGraph(String id){
		if(loadedGraphs.containsKey(id)){
			loadedGraphs.remove(id);
			setChanged();
			notifyObservers();
		}
	}
	

}
