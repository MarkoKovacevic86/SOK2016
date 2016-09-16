package rcpproject.java.model;

import java.io.IOException;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;

import rcpproject.java.parser.ClassParser;
import rcpproject.model.Graph;

public class JClass extends Graph{

	String className;
	ClassParser cparser;
	JMethod constructor;
	
	public JClass(String location) throws IOException {
		super(location);
		cparser = new ClassParser(location);
		className = cparser.getClassName();
		getNode();
	}
	
	private void getNode(){
		for(MethodDeclaration md : cparser.getMethods()){
			if(className.contains(md.getName().toString())){
				constructor = new JMethod(md.getName().toString(), "method");
				for(MethodDeclaration md2 : cparser.getMethods()){
					if(!md.equals(md2)){
						JMethod method2 = new JMethod(md2.getName().toString(), "method");
						getUsedVars(md2,method2);
						constructor.addChildNode(method2);
					}
				}
			} 
			
		}
		nodes.add(constructor);
		parseVariable(constructor);
	}
	
	private void getUsedVars(MethodDeclaration md, JMethod method){
		for(VariableDeclaration vd : cparser.getVariables()){
			if(md.getBody().toString().contains(vd.getName().toString().trim())){
				method.addNodeProperty(new JAttribute("var", vd.getName().toString().trim()));
			}
		}
	}
	
	private void parseVariable(JMethod jmethod){
		for(VariableDeclaration vd : cparser.getVariables()){
			JAttribute attr = new JAttribute("var", vd.getName().toString().trim());
			jmethod.addNodeProperty(attr);
		}
	}
	
}
