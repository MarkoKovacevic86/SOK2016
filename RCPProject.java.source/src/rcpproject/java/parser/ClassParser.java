package rcpproject.java.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.bag.SynchronizedBag;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ClassParser {
	
	private CompilationUnit cu;
	private MyVisitor visitor;
	private String filePath; 
	private String className;
	
	
	public ClassParser(String filePath) throws IOException {
		// TODO Auto-generated constructor stub
		initParser(filePath);
	}
				
	private void initParser(String filePath) throws IOException{
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(readFileToString(filePath).toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		cu = (CompilationUnit) parser.createAST(null);
		visitor = new MyVisitor();
		cu.accept(visitor);
		parseOutClass(cu.toString());
	}
	
	
	
	private static String readFileToString(String filePath) throws IOException{
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[10];
		int numRead = 0;
		while((numRead = reader.read(buf)) != -1){
			String readData = String.valueOf(buf,0,numRead);
			fileData.append(readData);
			buf = new char[1024];
		}reader.close();
		return fileData.toString();
	}
	
	public void readMethods(){
		for(MethodDeclaration md : visitor.getMethods()){
			System.out.println("METHOD: " + md.getName());
		}
	}
	
	public List<MethodDeclaration> getMethods(){
		return visitor.getMethods();
	}
	
	public void readNames(){
		for(SimpleName name : visitor.getNames()){
			System.out.println("NAME : " + name.getIdentifier());
		}
	}
	
	public List<VariableDeclaration> getVariables(){
		return visitor.getVariables();
	}
	
	public void readClassName(){
		
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	private void parseOutClass(String text){
		String[] parsedText = text.split("\\{");
		parsedText = parsedText[0].split("class");
		className = parsedText[1].trim();
	}

	public String getClassName() {
		return className;
	}	
	
}
