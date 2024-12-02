package hudomparseW0E0RY;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyW0E0RY {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub

		//XML fájl meghívása
		File xmlFile = new File("XMLW0E0RY.xml");
		
		//Document builder definiálása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//Fájl betöltése a DocumentBuilderbe
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//A 002-es id-val rendelkezõ Busz rendszamat ABC-123-ra valtoztatjuk
		NodeList nodes = doc.getElementsByTagName("Busz");
		for(int i=0;i<nodes.getLength();i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				if(node.getAttributes().getNamedItem("BuszId").getTextContent().equals("002")) {
					NodeList childNodes = node.getChildNodes();
					for(int j=0;j<childNodes.getLength();j++) {
						Node childNode = childNodes.item(j);
						if(childNode.getNodeName().equals("Rendszam")) {
							childNode.setTextContent("ABC-123");
						}
						
					}
				}
			}
		}
		
		//Újabb Buszt adunk hozzá
		Element Busz = (Element)doc.getElementsByTagName("Busz").item(0);
		Busz.appendChild(createBusz(doc,"010", "LKC-154", "Ikarus", "40"));
		
		//Újabb Sofort adunk hozzá
		Element Sofor = (Element)doc.getElementsByTagName("Soforok").item(0);
		Sofor.appendChild(createSofor(doc,"L93751", "Korti Gyula", "086", "005"));
		
		//Módosítjuk a 504-es id-val rendelkezõ kalauzt 601-re
		modifyId(doc, "kalauz", "kalauzId", "504", " 601");
		
		//Lementjük a módosított dokumentumot
		SaveAsDoc(doc, "teszt.xml");
	}
	
	//Lementjük a módosított xml dokumentumot
	public static void SaveAsDoc(Document doc, String filename) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File(filename);
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
	
	//Buszt hozzunk létre
	private static Node createBusz(Document doc, String BuszId, String Rendszam, String Tipus, String max_hely) {
		
		Element user = doc.createElement("Busz");
		
		user.setAttribute("BuszId", BuszId);
		user.appendChild(createElement(doc, "Rendszam", Rendszam));
		user.appendChild(createElement(doc, "Tipus", Tipus));
		user.appendChild(createElement(doc, "max_hely", max_hely));
		
		return user;
	}
	
	
	//Sofort hozzunk létre
	private static Node createSofor(Document doc, String SoforId, String Jegykiadoszam, String Nev, String BuszId_fk) {
		
		Element user = doc.createElement("Szektor");
		
		user.setAttribute("SoforId", SoforId);
		user.appendChild(createElement(doc, "Jegykiadoszam", Jegykiadoszam));
		user.appendChild(createElement(doc, "Nev", Nev));
		user.appendChild(createElement(doc, "BuszId_fk", BuszId_fk));

		
		return user;
	}
	
	//Elementet hozzunk létre
	private static Node createElement(Document doc, String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	//Módosítjuk az id-t
	
	public static void modifyId(Document doc, String tagName, String attrName, String oldData, String newData) {

		NodeList nodes = doc.getElementsByTagName(tagName);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getAttributes().getNamedItem(attrName).getTextContent().equals(oldData)) {
					node.getAttributes().getNamedItem(attrName).setTextContent(newData);
				}
			}
		}
	}

}
