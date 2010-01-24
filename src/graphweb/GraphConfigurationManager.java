package graphweb;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:24:55
 * To change this template use File | Settings | File Templates.
 */
public class GraphConfigurationManager {
    public static final String DATA_FILE = "security/graph.config.xml";
    //No generics
    GraphConfiguration configuration;
    Document dom;
    String file;

    public GraphConfigurationManager(String file) {
        //create a list to hold the data
        configuration = new GraphConfiguration();

        //Get a DOM object
        dom = createDocument();
        this.file = file;
    }


    public static Document createDocument() {

        //get an instance of factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //get an instance of builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //create an instance of DOM
            Document dom = db.newDocument();
            return dom;

        } catch (ParserConfigurationException pce) {

        }
        return null;
    }


    private Document createUserDocument() {
        Document newDomTree = createDocument();

        Element rootEle = newDomTree.createElement("configuration");
        newDomTree.appendChild(rootEle);

        createConfigurationChild(newDomTree, rootEle, configuration);


        return newDomTree;
    }

    private Element createElementConfig(Document document, String name, String value) {
        Element userEle = document.createElement(name);
        userEle.setTextContent(value);
        return userEle;
    }

    public static final String Prop_ColorOfAxis = "ColorOfAxis";
    public static final String Prop_ColorOfGraph = "ColorOfGraph";
    public static final String Prop_Height = "Height";
    public static final String Prop_MaxX = "MaxX";
    public static final String Prop_MinX = "MinX";
    public static final String Prop_RationOfCoordination = "RationOfCoordination";
    public static final String Prop_StepX = "StepX";
    public static final String Prop_Width = "Width";
//    public static final String ColorOfAxis="ColorOfAxis";

    private void createConfigurationChild(Document document, Element parent, GraphConfiguration gc) {
        Element ele1 = createElementConfig(document, Prop_ColorOfAxis, gc.getColorOfAxis() + "");
        parent.appendChild(ele1);

        Element ele2 = createElementConfig(document, Prop_ColorOfGraph, gc.getColorOfGraph() + "");
        parent.appendChild(ele2);
        Element ele3 = createElementConfig(document, Prop_Height, gc.getHeight() + "");
        parent.appendChild(ele3);
        Element ele4 = createElementConfig(document, Prop_MaxX, gc.getMaxX() + "");
        parent.appendChild(ele4);
        Element ele5 = createElementConfig(document, Prop_MinX, gc.getMinX() + "");
        parent.appendChild(ele5);
        Element ele6 = createElementConfig(document, Prop_RationOfCoordination, gc.getRationOfCoordination() + "");
        parent.appendChild(ele6);
        Element ele7 = createElementConfig(document, Prop_StepX, gc.getStepX() + "");
        parent.appendChild(ele7);
        Element ele8 = createElementConfig(document, Prop_Width, gc.getWidth() + "");
        parent.appendChild(ele8);


    }


    private void printToFile(Document documentTree) {

        try {
            //print
            OutputFormat format = new OutputFormat(documentTree);
            format.setIndenting(true);

            //to generate output to console use this serializer
            XMLSerializer serializer1 = new XMLSerializer(System.out, format);


            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File(file)), format);

            serializer.serialize(documentTree);
            serializer1.serialize(documentTree);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private GraphConfiguration getGraphConfiguration() {
        return configuration;
    }

    public static GraphConfiguration getGraphConfiguration(String file) {
        GraphConfigurationManager gcm = new GraphConfigurationManager(file);
        gcm.parseXmlFile();
        gcm.parseDocument();
        return gcm.getGraphConfiguration();
    }

    public static void saveConfiguration(GraphConfiguration configuration, String file) {
        GraphConfigurationManager gcm = new GraphConfigurationManager(file);
        /*gcm.parseXmlFile();
        gcm.parseDocument();*/
        gcm.configuration=configuration;
        Document doc = gcm.createUserDocument();
        gcm.printToFile(doc);


    }

    public static void main(String args[]) {
        try {
            String file = "/home/thuan/sandbox/graphweb/web/security/graph.config.xml";
            GraphConfiguration gc = GraphConfigurationManager.getGraphConfiguration(file);
            gc.setHeight(400);
            gc.setWidth(400);
            System.out.println(gc);
            GraphConfigurationManager.saveConfiguration(gc,file);
            //UserUtils.saveUser(new User("admin", CryptoUtils.encrypt("password", new File(filekey))), file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void parseXmlFile() {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse(file);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    private void parseDocument() {
        //get the root elememt
        Element docEle = dom.getDocumentElement();

        getConfiguration(docEle);
    }


    private void getConfiguration(Element configurationElement) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        configuration.setColorOfAxis(getTextValue(configurationElement, Prop_ColorOfAxis));
        configuration.setColorOfGraph(getTextValue(configurationElement, Prop_ColorOfGraph));
        configuration.setHeight(getIntValue(configurationElement, Prop_Height));
        configuration.setMaxX(getIntValue(configurationElement, Prop_MaxX));
        configuration.setMinX(getIntValue(configurationElement, Prop_MinX));
        configuration.setRationOfCoordination(getDoubleValue(configurationElement, Prop_RationOfCoordination));
        configuration.setStepX(getDoubleValue(configurationElement, Prop_StepX));
        configuration.setWidth(getIntValue(configurationElement, Prop_Width));

    }

    private String getAttTextValue(Element ele, String att) {
        return ele.getAttribute(att);
    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }


    private int getIntValue(Element ele, String tagName) {
        String a=getTextValue(ele, tagName);
        //in production application you would catch the exception
        if(a!=null)
            return Integer.parseInt(a);
        return -1;
    }

    private double getDoubleValue(Element ele, String tagName) {
        String a=getTextValue(ele, tagName);
        //in production application you would catch the exception
        if(a!=null)
            return Double.parseDouble(a);
        return -1;
    }

}
    
