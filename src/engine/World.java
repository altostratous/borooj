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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;

public class World {
    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    private Castle castle;
    private Gate gate;
    private Map map;
    private Timer timer;
    private ArrayList<PhysicalEntity> physicalEntities;

    public ArrayList<PhysicalEntity> getPhysicalEntities() {
        return physicalEntities;
    }

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


        // TODO: 4/9/2016 Add physicalEntities to physicalEntities in these constructors.
        physicalEntities = new ArrayList<>();
        this.map = new Map(mapElement, this);
        this.gate = new Gate(pathsElements, this);
        this.timer = new Timer();
        for (PhysicalEntity pe :
                physicalEntities) {
            timer.schedule(pe.getTimerTask(), pe.getInterval());
        }
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

    public ValidationState addTower(Point base) {
        throw new NotImplementedException();
    }

    public ValidationState setConfig(String configPath) {
        throw new NotImplementedException();
    }

    public ValidationState setMap(String configPath) {
        throw new NotImplementedException();
    }

    public ValidationState newGame(String configPath) {
        throw new NotImplementedException();
    }

    public ValidationState start() {
        throw new NotImplementedException();
    }
}
