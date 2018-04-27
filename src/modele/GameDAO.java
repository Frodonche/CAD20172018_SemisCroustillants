package modele;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import bateaux.Case;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

import epoques.*;

public class GameDAO implements GameService{

	private String name;
	private String Casesflotte1;
	private String Casesflotte2;
	private String epoque;

	public GameDAO(String name){
		this.name = name;	
	}

	public void serialize(Game g){
		this.Casesflotte1 = g.getJoueur(1).toStringFlotte();
		this.Casesflotte2 = g.getJoueur(2).toStringFlotte();
		this.epoque = g.getEpoque().toString();
	}

	@Override
	public GameDAO deserialize(String s) {
		return null;
	}

	public Game create(){
		//Game game = new Game(this.epoque);
		
		return null;
	}


	public void saveToXML() {

	    Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("game");

	        // create data elements and place them under root
            // for each player
			for(int i = 0; i <= 1; i++) {
			    int player = i+1;
                Node subRoot = createNode(dom,"flotte","flotte","joueur",""+player);

                String str;
                if(i == 1){
                    str = Casesflotte1;
                }else{
                    str = Casesflotte2;
                }
                String[] s = str.split("_");

                //for each boat
				for(int j = 0; j <= 4; j++){

                    String[] data = s[j].split(":");

                    Node shipRoot = createNode(dom, "bateau", "bateau","taille",""+(data.length-1));


					Node munRoot = createNode(dom, "munition", data[0],null,null);
					shipRoot.appendChild(munRoot);

					//for each case
                    for(int k = 1; k < data.length; k++){
                        Node caseRoot = createNode(dom,"case",data[k],null, null);
                        shipRoot.appendChild(caseRoot);
                    }
                    subRoot.appendChild(shipRoot);

                }
                rootEle.appendChild(subRoot);

			}
	        e = dom.createElement("epoque");
	        e.appendChild(dom.createTextNode(epoque));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);

//	        StringBuilder xml = new StringBuilder();
//	        xml.append(flotte1);
//	        xml.append(flotte2);
//	        xml.append(epoque);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "game.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(name)));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}

	public Node createNode(Document document, String name, String data, String attribute, String attrValue){

	    Element subNode = document.createElement(name);
	    subNode.appendChild(document.createTextNode(data));

	    if(attribute != null) {
            subNode.setAttribute(attribute,attrValue);
        }

        return subNode;
    }

//	public boolean readXML(String xml) {
//        ArrayList rolev = new ArrayList<String>();
//        Document dom;
//        // Make an  instance of the DocumentBuilderFactory
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        try {
//            // use the factory to take an instance of the document builder
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            // parse using the builder to get the DOM mapping of the
//            // XML file
//            dom = db.parse(xml);
//
//            Element doc = dom.getDocumentElement();
//
//            flotte1 = getTextValue(flotte1, doc, "flotte1");
//            if (flotte1 != null) {
//                if (!flotte1.isEmpty())
//                    rolev.add(flotte1);
//            }
//            flotte2 = getTextValue(flotte2, doc, "flotte2");
//            if (flotte2 != null) {
//                if (!flotte2.isEmpty())
//                    rolev.add(flotte2);
//            }
//            epoque = getTextValue(epoque, doc, "epoque");
//            if (epoque != null) {
//                if (!epoque.isEmpty())
//                    rolev.add(epoque);
//            }
//            return true;
//
//        } catch (ParserConfigurationException pce) {
//            System.out.println(pce.getMessage());
//        } catch (SAXException se) {
//            System.out.println(se.getMessage());
//        } catch (IOException ioe) {
//            System.err.println(ioe.getMessage());
//        }
//
//        return false;
//    }

    private String getTextValue(String def, Element doc, String tag) {
    String value = def;
    NodeList nl;
    nl = doc.getElementsByTagName(tag);
    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
        value = nl.item(0).getFirstChild().getNodeValue();
    }
    return value;
}
}