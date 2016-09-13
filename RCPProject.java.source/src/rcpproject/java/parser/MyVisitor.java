package rcpproject.java.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class MyVisitor extends ASTVisitor {
	List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	List<VariableDeclaration> variables = new ArrayList<VariableDeclaration>();
	
	@Override
	public boolean visit(MethodDeclaration node) {
		// TODO Auto-generated method stub
		methods.add(node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(VariableDeclarationFragment node) {
		// TODO Auto-generated method stub
		variables.add(node);
		return super.visit(node);
	}
	public List<MethodDeclaration> getMethods() {
		return methods;
	}

	public List<VariableDeclaration> getVariables() {
		return variables;
	}

}
