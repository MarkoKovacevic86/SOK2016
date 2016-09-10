package rcpproject.jar.model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import rcpproject.model.Graph;

public class JARFile extends Graph {

	String location;
	
	public JARFile(String location) {
		super(location);
		File file = new File(location);
		Document doc = null;
		DocumentBuilder docBuilder = null;
		try{
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}try{
			doc = docBuilder.parse(file);
		}catch(SAXException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated constructor stub
	}
	
	private void getNode(NodeList nl, JARClass parent){
		JARClass jarClass = null;
		for(int i = 0; i < nl.getLength();i++){
			Node tempNode = nl.item(i);
			if(tempNode.getNodeType() == Node.ELEMENT_NODE){
				jarClass = new JARClass(tempNode.getNodeName(),"JAR Class");
				if(parent != null){
					parent.getChildNodes().add(jarClass);
				}
				if(tempNode.hasAttributes()){
					NamedNodeMap mapOfNodes = tempNode.getAttributes();
					for(int j = 0; j < mapOfNodes.getLength();i++){
						Node node = mapOfNodes.item(i);
						ClassContent cc = new ClassContent(node.getNodeValue(), node.getNodeName());
						jarClass.getNodeProperties().add(cc);
					}
				}jarClass.addChildNode(jarClass);
				if(tempNode.hasChildNodes()){
					getNode(tempNode.getChildNodes(),jarClass);
				}
			}
		}
	}
	
	private void parseJAR(Document doc){
		getNode(doc.getChildNodes(),null);
	}

}
