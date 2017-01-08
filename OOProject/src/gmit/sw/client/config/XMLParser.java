//This class recieves the XML docment and parses it trough
package gmit.sw.client.config;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	
	public XMLParser(){
		//Default
		}
	
	public void file(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("books.xml");
			NodeList bookList =doc.getElementsByTagName("book");
			for (int i =0; i < bookList.getLength();i++){
				Node b = bookList.item(i);
				
				if (b.getNodeType() == Node.ELEMENT_NODE){
					Element book = (Element) b;
					String id = book.getAttribute("id");
					System.out.println("\nBook: "+ id);
					NodeList bookNames = book.getChildNodes();
					
					for(int j=0; j < bookNames.getLength();j++){
						Node n = bookNames.item(j);
						
						if (n.getNodeType() == Node.ELEMENT_NODE){
							Element name = (Element) n;
							System.out.println(name.getTagName()+ ":"+name.getTextContent());
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

