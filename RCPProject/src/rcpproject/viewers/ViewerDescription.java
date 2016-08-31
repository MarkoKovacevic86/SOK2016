package rcpproject.viewers;

public class ViewerDescription {
	
	private String id;
	private String name;
	private String sourcid;
	public ViewerDescription(String id, String name, String sourcId) {
		super();
		this.id = id;
		this.name = name;
		this.sourcid = sourcId;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSourcId() {
		return sourcid;
	}
	
}
