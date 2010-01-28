package graphweb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 24 janv. 2010
 * Time: 15:43:42
 * To change this template use File | Settings | File Templates.
 */
public class UserUtils {
    public static final String DATA_FILE = "security/users.xml";
    //No generics
    ArrayList<User> userList;
    Document dom;
    String file;

    public UserUtils(String file) {
        //create a list to hold the data
        userList = new ArrayList();

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
//        if (!newDomTree.hasChildNodes()) {
        Element rootEle = newDomTree.createElement("users");
        newDomTree.appendChild(rootEle);

//        }
        //No enhanced for
        Iterator it = userList.iterator();
        while (it.hasNext()) {
            User b = (User) it.next();
            Element userEle = createUserElement(newDomTree, b);
            rootEle.appendChild(userEle);
        }

        return newDomTree;
    }


    private Element createUserElement(Document document, User b) {

        Element userEle = document.createElement("user");
        userEle.setAttribute("username", b.getUsername());
        userEle.setAttribute("password", b.getPassword());

        return userEle;
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

    private User getUserByUserName(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).username.compareToIgnoreCase(username) == 0)
                return userList.get(i);
        }
        return null;
    }

    public static User getUserByUserName(String username, String file) {
        UserUtils uu = new UserUtils(file);
        uu.parseXmlFile();
        uu.parseDocument();
        User user = uu.getUserByUserName(username);
        return user;
    }

    public static void saveUser(User user, String file) {
        UserUtils uu = new UserUtils(file);
        uu.parseXmlFile();
        uu.parseDocument();
        User newUser = uu.getUserByUserName(user.getUsername());
        newUser.copy(user);
        if (newUser != null) {
            Document doc = uu.createUserDocument();
            uu.printToFile(doc);
        }

    }

    public static void main(String args[]) {
        try {
            String file = "/home/thuan/sandbox/graphweb/web/security/users.xml";
            String filekey = "/home/thuan/sandbox/graphweb/web/security/key";
            UserUtils.saveUser(new User("admin", CryptoUtils.encrypt("password", new File(filekey))), file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*UserUtils uu=new UserUtils();
        uu.parseXmlFile(file);
        uu.parseDocument();
        for(int i=0; i<uu.userList.size(); i++){
            System.out.println(uu.userList.get(i));
        }
        */
        /*UserUtils uu = new UserUtils();
        uu.parseXmlFile(file);
        uu.parseDocument();
        User admin = uu.getUserByUserName("admin");
        if (admin != null) {
            admin.setPassword("xin chao");
            Document doc = uu.createUserDocument();
            uu.printToFile(doc,file);
        }
        for (int i = 0; i < uu.userList.size(); i++) {
            System.out.println(uu.userList.get(i));
        }*/

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

        //get a nodelist of <employee> elements
        NodeList nl = docEle.getElementsByTagName("user");

        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {

                Element el = (Element) nl.item(i);

                User user = getUser(el);

                userList.add(user);
            }
        }
    }


    private User getUser(Element userElement) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        String name = getAttTextValue(userElement, "username");
        String pass = getAttTextValue(userElement, "password");

        //Create a new Employee with the value read from the xml nodes
        User e = new User(name, pass);

        return e;
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
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }


}
