package engine;

import java.awt.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

//import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class World {
    public Castle castle;
    public Gate gate;
    public Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    //public HashMap<Point, Cell> cells;
    private Scanner scanner;
    private int money;

    public World() {
        scanner = new Scanner(System.in);
        System.out.println("Enter money:");
        money = scanner.nextInt();
        //generateControls();
    }

    public World(String mapFilePath, String configurationFilePath) throws Exception
    {
        generateControls(mapFilePath);
    }
    private void generateControls(String mapFilePath) throws Exception {

        // do some stuff to get document form the map file
        File file = new File(mapFilePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        // get the map element in the file
        Element mapElement = document.getDocumentElement();

        // get all path nodes
        NodeList pathsElements = document.getElementsByTagName("path");


        this.map = new Map(mapElement, this);
        this.gate = new Gate(pathsElements, this);
    }

//    private void genCastle() {
//        System.out.println("Enter Castle Rectangle (4 numbers with space between for 2 points of rectangle):");
//        scanner.nextLine();
//        // Rectangle rec = new Rectangle(scanner.nextLine(), this);
//        //castle = new Castle(rec.getHashMapOfCells(), 30, this);
//    }
//    public void generateControls() {
//        //generate map
//        System.out.println("Enter width: ");
//        int width = scanner.nextInt();
//        System.out.println("Enter height: ");
//        int height = scanner.nextInt();
//
//        map = new Map(width, height);
//
//
//        //generate castle
//        genCastle();
//
//        //generate gate
//    }
    public void gameOver() {
        System.out.println("gameOver!");
        throw new NotImplementedException();
    }
}
