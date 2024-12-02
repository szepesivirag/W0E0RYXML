import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteNeptunkod {
    public static void main(String[] args) {
        try {
            // 1. DocumentBuilderFactory létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            // 2. DocumentBuilder létrehozása
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // 3. XML fájl betöltése
            Document doc = builder.parse(new File("XMLNeptunkod.xml"));
            
            // 4. Fastruktúra kiírása konzolra
            printNode(doc.getDocumentElement(), 0);
            
            // 5. Új XML fájl létrehozása és mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLNeptunkod1.xml"));
            transformer.transform(source, result);
            
            System.out.println("XML fájl sikeresen létrehozva és elmentve: XMLNeptunkod1.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Fastruktúra kiírása rekurzívan
    private static void printNode(Node node, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getNodeName() + ": " + node.getTextContent().trim());
        
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                printNode(childNode, indent + 1);
            }
        }
    }
}
