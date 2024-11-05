package domW0E0RY1105;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyW0E0RY {
	
	public static void main(String argv[]) {
		
		try {
			File inputFile = new File("Hallgatokquery.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse(inputFile);
			
			Node hallgatok = doc.getFirstChild();
			
			Node hallgat = doc.getElementsByTagName("hallgato").item(0);
			
			//hallgato attribútumának módosítása
			NamedNodeMap attr = hallgat.getAttributes();
			Node nodeAttr = attr.getNamedItem("id");
			nodeAttr.setTextContent("01");
			
			// loop the hallgato child node
			NodeList list = hallgat.getChildNodes();
			
			for (int temp = 0; temp <list.getLength(); temp++) {
				Node node = list.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					
					if("keresztnev".equals(eElement.getNodeName())){
						if("piroska".equals(eElement.getTextContent())){
							eElement.setTextContent("olivia");
						}
					}
					if("vezeteknev".equals(eElement.getNodeName())){
						if("olah".equals(eElement.getTextContent())){
							eElement.setTextContent("vigh");
						}
					}
				
				}
			
			}
			//Tartalom konzolra kiírása
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			//Ez DOMSource tartalmazza a Dom filet
			DOMSource source = new DOMSource(doc);
			
			System.out.println("---Módosított fájl---");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			
			}catch (Exception e){
			e.printStackTrace();
		}
	}
}
