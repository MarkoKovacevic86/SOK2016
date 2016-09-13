package rcpproject.java.model;

import java.io.IOException;

import rcpproject.java.parser.ClassParser;
import rcpproject.model.Graph;

public class JClass extends Graph{

	public JClass(String location) throws IOException {
		super(location);
		ClassParser cparser = new ClassParser(location);
		/*cparser.readMethods();
		cparser.readNames();*/
		// TODO Auto-generated constructor stub
	}
	
	
}
