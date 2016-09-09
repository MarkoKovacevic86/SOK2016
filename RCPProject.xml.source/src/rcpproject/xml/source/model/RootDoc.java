package rcpproject.xml.source.model;

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

public class RootDoc extends Graph {

	public RootDoc(String location) {
		super(location);
		System.out.println(location);
		File file = new File(location);
		Document doc = null;
		DocumentBuilder docBuilder = null;
		try{
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}
		try{
			doc = docBuilder.parse(file);
		}catch(SAXException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		parseXML(doc);
	}
	
	private void getNode(NodeList nodeList, Element parrent){
		Element element = null;
		for(int count = 0; count < nodeList.getLength(); count++){
			Node tNode = nodeList.item(count);
			if(tNode.getNodeType() == Node.ELEMENT_NODE){
				element = new Element(tNode.getNodeName(), "XML Element");
				if(parrent != null){
					parrent.getChildNodes().add(element);
				}
				if(tNode.hasAttributes()){
					NamedNodeMap mapOfNodes = tNode.getAttributes();
					for(int i = 0; i < mapOfNodes.getLength();i++){
						Node node = mapOfNodes.item(i);
						Attribute attr = new Attribute(node.getNodeValue(), node.getNodeName());
						element.getNodeProperties().add(attr);
					}
				}
				nodes.add(element);
				if(tNode.hasChildNodes()){
					getNode(tNode.getChildNodes(),element);
				}
			}
		}
	}
	
	private void parseXML(Document doc){
		getNode(doc.getChildNodes(),null);
	}

}
