package modele;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;


import epoques.*;

public class GameDAO implements GameService {

    private String name;
    private String Casesflotte1;
    private String Casesflotte2;
    private String grille1;
    private String grille2;
    private String epoque;

    public GameDAO(String name) {
        this.name = name;
        this.Casesflotte1 = "";
        this.Casesflotte2 = "";
        this.grille1 = "";
        this.grille2 = "";
    }

    public void serialize(Game g) {
        this.Casesflotte1 = g.getJoueur(1).toStringFlotte();
        this.Casesflotte2 = g.getJoueur(2).toStringFlotte();
        this.grille1 = g.getJoueur(1).getGrilleToString();
        this.grille2 = g.getJoueur(2).getGrilleToString();
        this.epoque = g.getEpoque().toString();
    }

    @Override
    public GameDAO deserialize(String s) {
        return null;
    }

    public Game create(String xml) {
        Game game;
        this.readXML(xml);

	    if(this.epoque == "XVIe siecle") {
            game = new Game(new EpoqueXVIe(this.epoque));
        }else{
            game = new Game(new EpoqueXXe(this.epoque));
        }

        String[] bateaux1 = Casesflotte1.split("_");

	    for(int i=0;i<bateaux1.length;i++){
	        game.getJoueur(1).chargerBateau(bateaux1[i]);
        }
        String[] bateaux2 = Casesflotte2.split("_");

        for(int i=0;i<bateaux2.length;i++){
            game.getJoueur(2).chargerBateau(bateaux2[i]);
        }

        game.getJoueur(1).chargerGrille(grille1);
        game.getJoueur(2).chargerGrille(grille2);

        return game;
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
            for (int i = 1; i <= 2; i++) {
                int player = i;
                Node subRoot = createNode(dom, "flotte", null, "joueur", "" + player);

                String str;
                String grille;
                if (i == 1) {
                    str = Casesflotte1;
                    grille = grille1;
                } else {
                    str = Casesflotte2;
                    grille = grille2;
                }
                String[] s = str.split("_");

                //for each boat
                for (int j = 0; j <= 4; j++) {

                    String[] data = s[j].split(":");

                    int taille = data.length - 1;

                    Node shipRoot = createNode(dom, "bateau", null, "taille", "" + taille);


                    Node munRoot = createNode(dom, "munition", data[0], null, null);
                    shipRoot.appendChild(munRoot);

                    //for each case
                    for (int k = 1; k < data.length; k++) {
                        Node caseRoot = createNode(dom, "case", ":"+data[k], null, null);
                        shipRoot.appendChild(caseRoot);
                    }
                    subRoot.appendChild(shipRoot);

                }


                Node grilleRoot = createNode(dom, "grille", grille, null, null);
                subRoot.appendChild(grilleRoot);

                rootEle.appendChild(subRoot);


            }
            e = dom.createElement("epoque");
            e.appendChild(dom.createTextNode(epoque));
            rootEle.appendChild(e);


            dom.appendChild(rootEle);

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

    public Node createNode(Document document, String name, String data, String attribute, String attrValue) {

        Element subNode = document.createElement(name);
        if (data != null) {
            subNode.appendChild(document.createTextNode(data));
        }

        if (attribute != null) {
            subNode.setAttribute(attribute, attrValue);
        }

        return subNode;
    }

    public boolean readXML(String xml) {
        try {

            File fXmlFile = new File(xml);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("game");

            System.out.println("----------------------------");

            if(doc.hasChildNodes()) {
                this.setClasse(nList,0);
            }

//            System.out.println(this.Casesflotte1);
//            System.out.println(this.Casesflotte2);
//            System.out.println(this.epoque);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    private void setClasse(NodeList nodeList, int joueur) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                String temp = tempNode.getNodeName();
                if (temp.equals("flotte")) {
                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();
                    if ((nodeMap.item(0).getNodeValue()).equals("1")) {
                        setClasse(tempNode.getChildNodes(), 1);
                    } else if ((nodeMap.item(0).getNodeValue()).equals("2")){
                        setClasse(tempNode.getChildNodes(), 2);
                    }
                }


                if(temp.equals("bateau")){
                    if(joueur == 1){
                        this.concatCases1(tempNode.getTextContent()+"_");
                    }else if(joueur == 2){
                        this.concatCases2(tempNode.getTextContent()+"_");
                    }
                }

                if(temp.equals("grille")){
                    if(joueur == 1){
                        this.grille1 = tempNode.getTextContent();
                    }else if(joueur == 2){
                        this.grille2 = tempNode.getTextContent();
                    }
                }


                if(temp.equals("epoque")){
                    this.setEpoque(tempNode.getTextContent());
                }

            }

            if (tempNode.hasChildNodes()) {
                // loop again if has child nodes
                setClasse(tempNode.getChildNodes(),joueur);
            }
        }
    }

    public void concatCases1(String cases) {
        this.Casesflotte1 += cases;
    }

    public void concatCases2(String cases) {
        this.Casesflotte2 += cases;
    }

    public void setEpoque(String epoque) {
        this.epoque = epoque;
    }
}


