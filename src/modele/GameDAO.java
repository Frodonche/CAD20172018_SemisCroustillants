package modele;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.IOException;
import java.io.FileOutputStream;

public class GameDAO implements GameService{

	private String name;
	private String flotte1;
	private String flotte2;
	private String epoque;

	public GameDAO(String n){
		this.name = n;
	}

	public void serialize(Game g){
		this.flotte1 = g.getJoueur(1).toStringFlotte();
		this.flotte2 = g.getJoueur(2).toStringFlotte();
		this.epoque = g.getEpoque().toString();
	}

	public GameDAO deserialize(String s){
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
	        e = dom.createElement("flotte1");
	        e.appendChild(dom.createTextNode(flotte1));
	        rootEle.appendChild(e);

	        e = dom.createElement("flotte2");
	        e.appendChild(dom.createTextNode(flotte2));
	        rootEle.appendChild(e);

	        e = dom.createElement("epoque");
	        e.appendChild(dom.createTextNode(epoque));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);

	        StringBuilder xml = new StringBuilder();
	        xml.append(flotte1);
	        xml.append(flotte2);
	        xml.append(epoque);

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
}