package project.viewers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;

public class ViewerManager {
	
	
	public static final String ID = "project.viewer";
	private static ViewerManager manager = null;
	private List<ViewerDescription> viewers;
	
	
	public ViewerManager() {
		super();
		viewers = new ArrayList<ViewerDescription>();
	}
	
	public static ViewerManager getInstance(){
		if(manager == null){
			manager = new ViewerManager();
			manager.initialize();
		}return manager;
	}
	
	private void initialize(){
		IConfigurationElement[] extensions = RegistryFactory.getRegistry().getConfigurationElementsFor(ID);
		for(IConfigurationElement extension : extensions){
			viewers.add(new ViewerDescription(extension.getAttribute("id"), extension.getAttribute("name"), extension.getAttribute("sourceId")));			
		}
	}

	public List<ViewerDescription> getViewers() {
		return viewers;
	}
	
	public IViewer creatViewer(String id){
		IConfigurationElement[] extensions = RegistryFactory.getRegistry().getConfigurationElementsFor(ID);
		try{
			for(IConfigurationElement extension : extensions){
				if(id.equals(extension.getAttribute("id"))){
					return (IViewer) extension.createExecutableExtension("class");
				}
			}
		}catch(CoreException e){
			e.printStackTrace();
		}return null;
	}
	
	
}
