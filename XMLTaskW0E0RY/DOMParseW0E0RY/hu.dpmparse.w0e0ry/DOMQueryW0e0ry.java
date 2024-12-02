package hudomparseW0E0RY;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryW0E0RY {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		
		//XML fájl meghívása
		File xmlFile = new File("XMLW0E0RY.xml");
		
		//Document builder definiálása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//Fájl betöltése a documentum builderbe
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//Soforok kilistázása
		
		System.out.println("");
		System.out.println("Soforok kilistazasa: ");
		System.out.println("");
		
		NodeList soforList = doc.getElementsByTagName("Sofor");
		for(int i=0;i<soforList.getLength();i++) {
			Node nNode = soforList.item(i);
			printSofor(nNode);
		}
	

		
		//Mercedes Tipusu autobuszok kilistazasa
		System.out.println("");
		System.out.println("Mercedes tipusu autobuszok: ");
		System.out.println("");
		
		NodeList feederList = doc.getElementsByTagName("Autobusz");
		for(int i=0;i<feederList.getLength();i++) {
			Node nNode = feederList.item(i);
			printAutobusz(nNode, "Mercedes");
		}
	
		
		//Szervizek kilistázása másik megoldással
		
		NodeList SzervizList =doc.getElementsByTagName("Szerviz");
		
		for(int i=0;i<SzervizList.getLength();i++) {
			Node nNode = SzervizList.item(i);
			System.out.println("");
			printSzerviz(nNode);
		}
	
		

		  
		//kalauzok kilistázása
		
		System.out.println("");
		System.out.println("kalauzk kilistázása");
		
		NodeList kalauzList = doc.getElementsByTagName("kalauz");
		for(int i=0;i<kalauzList.getLength();i++) {
			Node nNode = kalauzList.item(i);
			System.out.println("");
			printkalauz(nNode);
		}
	
	}



	
	//Soforok kilistázása
	private static void printSofor(Node nNode) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String SoforId = elem.getAttribute("SoforId");
			
			Node nNode1 = elem.getElementsByTagName("Nev").item(0);
			String Nev = nNode1.getTextContent();
			
			System.out.printf("SoforID: %s%n", SoforId);
			System.out.printf("Nev: %s%n", Nev);
		}
	}

	//Mercedes tipusu autobuszok kilistazasa
	private static void printAutobusz(Node nNode, String Tipus) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String AutobuszId = elem.getAttribute("AutobuszId");
			
			Node nNode1 = elem.getElementsByTagName("Rendszam").item(0);
			String Rendszam = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("Tipus").item(0);
			String Tipus2 = nNode2.getTextContent();
			
			Node nNode3 = elem.getElementsByTagName("max_hely").item(0);
			String max_hely = nNode3.getTextContent();
			
			if(Tipus.equals(Tipus2)) {
				System.out.printf("AutobuszID: %s%n", AutobuszId);
				System.out.printf("Rendszam: %s%n", Rendszam);
				System.out.printf("Tipus %s%n", Tipus2);
				System.out.printf("max_hely %s%n", max_hely);
				System.out.println("");
			}
		}
	}
	
	

	
	//Szervizek kilistázása másik megoldással
	private static void printSzerviz(Node nNode) {
		if(nNode.getNodeType()==Node.ELEMENT_NODE) {
			Element element =(Element) nNode;
			NodeList nList = element.getChildNodes();
			for (int j = 0; j < nList.getLength(); j++) {
                Node node2 = nList.item(j);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node2;
                    System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
                }
			}
		}	
	}
	
	
	
	//kalauzk kilistázása
	private static void printkalauz(Node nNode) {
		if(nNode.getNodeType()==Node.ELEMENT_NODE) {
			Element element =(Element) nNode;
			NodeList nList = element.getChildNodes();
			for (int j = 0; j < nList.getLength(); j++) {
                Node node2 = nList.item(j);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node2;
                    System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
                    
                   
                }
			}
		}	
	}
}
