import engine.CommandProcessor;
import engine.ValidationState;
import engine.World;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * In the name of ALLAH
 * Va ma ramyta ez ramyt valakenna Allah rama
 -*/
public class Main {
    public static void main(String[] args) {
        try {
            Timer timer = new Timer();
            World world = new World("data/map.xml", "data/configuration.xml");
            CommandProcessor cmdp = new CommandProcessor(System.out, world, System.in);
            world.setCmdp(cmdp);
            ArrayList<String> commands = getCommands("data/configuration.xml");

            for (String command :
                    commands) {
                cmdp.run(command);
            }
            cmdp.start();
        }
        catch (Exception ex)
        {
            System.out.println("Problem reading map file, details:");
            ex.printStackTrace();
        }
    }

    /**
     * Gets preset commands from an XML file
     *
     * @param path the path to the xml file
     * @return an array list of strings representing the commands
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private static ArrayList<String> getCommands(String path) throws ParserConfigurationException, IOException, SAXException {
        // do some stuff to get document form the map file
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        // get the config element in the file
        Element configurationElement = document.getDocumentElement();

        // get commands
        NodeList list = configurationElement.getElementsByTagName("command");

        // result
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node command = list.item(i);
            result.add(command.getTextContent());
        }
        return result;
    }
}