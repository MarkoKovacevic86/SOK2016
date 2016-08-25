package project.viewers;

public class ViewerDescription {
	private String id;
	private String name;
	private String sourceId;
	
	public ViewerDescription(String id, String name, String sourceId) {
		super();
		this.id = id;
		this.name = name;
		this.sourceId = sourceId;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSourceId() {
		return sourceId;
	}
	
	
	
}
