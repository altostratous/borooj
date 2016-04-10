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
import java.util.*;

/**
 * The class to model the environment of the game
 */
public class World {
    /**
     * Get the castle
     *
     * @return gets the castle in the world
     */
    public Castle getCastle() {
        return castle;
    }

    /**
     * Sets the castle in the world
     * @param castle the castle
     */
    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    /**
     * Gets the gate in the world
     * @return returns a gate
     */
    public Gate getGate() {
        return gate;
    }

    /**
     * Sets the gate of the world
     * @param gate the gate to be set
     */
    public void setGate(Gate gate) {
        this.gate = gate;
    }

    // the castle
    private Castle castle;
    // the gate
    private Gate gate;
    // the map
    private Map map;
    // the timer
    private Timer timer;
    private ArrayList<PhysicalEntity> physicalEntities;
    private ArrayList<Wave> waves;
    private int wavesCounter;

    /**
     * Registers a physical entity in the world so that the entity starts acting
     * @param pe the physical entity to add
     */
    public void addPhysicalEntity(PhysicalEntity pe) {
        physicalEntities.add(pe);
    }

    /**
     * Removes an entity from the world
     * @param pe the physical entity to remove
     */
    public void removePhysicalEntity(PhysicalEntity pe) {
        physicalEntities.remove(pe);
    }

    /**
     * Gets the map of the world
     * @return
     */
    public Map getMap() {
        return map;
    }

    /**
     * Sets the map of the world
     * @param map the map object to be set
     */
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

    public void callWave() {
        wavesCounter++;
        waves.get(wavesCounter).startWaving();
    }

    /**
     * internally reads the map file
     * @param mapFilePath address to the map file
     * @throws Exception
     */
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

        //get all waves
        NodeList wavesNodeList = document.getElementsByTagName("wave");
        generateWaves(wavesNodeList);

        physicalEntities = new HashSet<>();
        this.map = new Map(mapElement, this);
        this.gate = new Gate(pathsElements, this);
        // simple interval to test the functionality
        this.gate.setInterval(1000);
        // register the gate as a physical entity
        this.physicalEntities.add(this.gate);
        this.timer = new Timer();
    }

    /**
     * This function is called when the game is over
     */
    public void onGameOver() {
    private void generateWaves(NodeList wavesNodeList) {
        wavesCounter = -1;
        waves = new ArrayList<>();
        for (int i = 0; i < wavesNodeList.getLength(); i++) {
            Node node = wavesNodeList.item(i);
            waves.add(new Wave(getGate(), node));
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

    /**
     * tries to add tower
     * @param base the base to add tower on
     * @return ValidationState representing the validation of the order
     */
    public ValidationState addTower(Point base) {
        throw new NotImplementedException();
    }

    /**
     * Set config from file
     * @param configPath address to the config file
     * @return ValidationState representing the validation of the order
     */
    public ValidationState setConfig(String configPath) {
        throw new NotImplementedException();
    }

    /**
     * Sets the map firm map file
     * @param configPath address to the map xml file
     * @return ValidationState representing the validation of the order
     */
    public ValidationState setMap(String configPath) {
        throw new NotImplementedException();
    }

    /**
     * Creates new game based on previous configuration file
     *
     * @return ValidationState representing the validation of the order
     */
    public ValidationState newGame() {
        throw new NotImplementedException();
    }

    /***
     * Starts the game
     * @return ValidationState representing the validation of the order
     */
    public ValidationState start() {
        callWave();
        throw new NotImplementedException();
        for (PhysicalEntity pe :
                physicalEntities) {
            timer.schedule(pe.getTimerTask(), 0, pe.getInterval());
        }
        return ValidationState.VALID;
    }
}
