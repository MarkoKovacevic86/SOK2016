package project.source;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;

public class SourceManager {
	public static final String ID = "project.source";
	
	private static SourceManager manager = null;
	
	protected List<SourceDescription> sources;

	public SourceManager() {
		super();
		sources = new ArrayList<SourceDescription>();
	}
	
	private void initialize(){
		IConfigurationElement[] extensions = RegistryFactory.getRegistry().getConfigurationElementsFor(ID);
		
		for(IConfigurationElement extension : extensions){
			sources.add(new SourceDescription(extension.getAttribute("id"), extension.getAttribute("name")));
					
		}
	}
	
	public ISource createSource(String id){
		IConfigurationElement[] extensions = RegistryFactory.getRegistry().getConfigurationElementsFor(ID);
		try{
			for(IConfigurationElement extension : extensions){
				if(id.equals(extension.getAttribute("id"))){
					return (ISource) extension.createExecutableExtension("class");
				}
			}
		}catch(CoreException e){
			e.printStackTrace();
		}return null;
	}

	public List<SourceDescription> getSources() {
		return sources;
	}
	
}
