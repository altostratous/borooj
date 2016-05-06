package logic;

import java.awt.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
    // field for scaling the time in game
    private int timerScale;
    // It is needed for printing from world, like enemy is destroyed, etc;
    private CommandProcessor cmdp;

    /**
     * Get Timer
     *
     * @return gets the timer of world
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Get the castle
     *
     * @return gets the castle in the world
     */
    public Castle getCastle() {
        return castle;
    }

    public ArrayList<AliveEnemyUnit> getAliveEnemyUnits() {
        ArrayList<AliveEnemyUnit> listOfAlives = new ArrayList<>();
        ArrayList<PhysicalEntity> PEs = this.getPhysicalEntities();
        synchronized (PEs) {
            for (PhysicalEntity a : PEs
                    ) {
                if (a instanceof AliveEnemyUnit) {
                    listOfAlives.add((AliveEnemyUnit) a);
                }
            }
        }
        return listOfAlives;
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

    public ArrayList<PhysicalEntity> getPhysicalEntities() {
        return physicalEntities;
    }
    /**
     * Registers a physical entity in the world so that the entity starts acting
     * @param pe the physical entity to add
     */
    public void addPhysicalEntity(PhysicalEntity pe) {
        synchronized (physicalEntities) {
            physicalEntities.add(pe);
            timer.schedule(pe.getTimerTask(), 0, this.timerScale * pe.getInterval());
        }
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
     *
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


    public World(String mapFilePath, String configurationFilePath) throws Exception
    {
        generateControls(mapFilePath);
        setConfig(configurationFilePath);
        // TODO: 4/24/2016 load money from external resource 
        money = 22;
    }

    public void callWave() {
        // TODO: 4/13/2016 Check limit of waves 
        wavesCounter++;
        if (wavesCounter < waves.size())
            waves.get(wavesCounter).startWaving();
    }

    /**
     * It is used for printing from objects, like soldiers
     *
     * @return
     */
    public CommandProcessor getCmdp() {
        return cmdp;
    }

    /**
     * It is used once for associating active cmdp with world
     *
     * @param cmdp
     */
    public void setCmdp(CommandProcessor cmdp) {
        this.cmdp = cmdp;
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

        // Getting castle Element from XML
        Node castleElement = document.getElementsByTagName("castle").item(0);

        // TODO: 4/9/2016 Add physicalEntities to physicalEntities in these constructors.
        physicalEntities = new ArrayList<>();
        this.map = new Map(mapElement, this);
        // init gate
        this.gate = new Gate(pathsElements, this);
        // simple interval to test the functionality
        // TODO: 4/24/2016 Load from external resource 
        this.gate.setInterval(600);
        // register the gate as a physical entity
        this.physicalEntities.add(this.gate);

        //
        this.castle = new Castle(castleElement, this);
        this.physicalEntities.add(this.castle);

        //get all waves
        NodeList wavesNodeList = document.getElementsByTagName("wave");
        generateWaves(wavesNodeList);

        this.timer = new Timer();
        for (PhysicalEntity pe :
                physicalEntities) {
            //timer.schedule(pe.getTimerTask(), pe.getInterval());
        }
    }

    /**
     * This function is called when the game is over
     */
    public void onGameOver() {
        timer.cancel();
        if (getCastle().getLife() <= 0)
            getCmdp().print("\u001B[31m" + "GAME IS OVER, YOU LOST" + "\u001B[0m");
        else
            getCmdp().print("\u001B[34m" + "You WON!" + "\u001B[0m");

    }
    private void generateWaves(NodeList wavesNodeList) {
        wavesCounter = -1;
        waves = new ArrayList<>();
        for (int i = 0; i < wavesNodeList.getLength(); i++) {
            Node node = wavesNodeList.item(i);
            waves.add(new Wave(getGate(), node, this));
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
        //BUG: Multiple Towers can be added to one point
        if (map.getCells().get(base).getEntities().size() > 0)
            return ValidationState.INVALID_BASE;
        for (Path path :
                getGate().getPaths()) {
            if (path.getCells().contains(map.getCells().get(base)))
                return ValidationState.INVALID_BASE;
        }
        // set interval from data
        if (this.money >= Tower.getCost()) {
            this.money -= Tower.getCost();
            Tower tower = new Tower(this, base, 300, 7);

            addPhysicalEntity(tower);

            // set tower as the physical entity for the cell
            ArrayList<PhysicalEntity> physicalEntities = new ArrayList<>();
            physicalEntities.add(tower);
            map.getCells().get(base).setEntities(physicalEntities);

            return ValidationState.VALID;
        }
        else
        {
            return ValidationState.NOT_ENOUGH_MONEY;
        }
    }

    /**
     * Set config from file
     * @param configPath address to the config file
     * @return ValidationState representing the validation of the order
     */
    public ValidationState setConfig(String configPath) {
        // do some stuff to get document form the map file
        File file = new File(configPath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            // get the map element in the file
            Element mapElement = document.getDocumentElement();

            // get ftimer scale node
            this.timerScale = Integer.parseInt(mapElement.getElementsByTagName("timer-scale").item(0).getTextContent());
            return ValidationState.VALID;
        } catch (Exception ex) {
            return ValidationState.INVALID_FILE;
        }
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
        for (PhysicalEntity pe :
                physicalEntities) {
            //timer.schedule(pe.getTimerTask(), 0, this.timerScale * pe.getInterval());
        }
        //gate.start();
        timer.schedule(gate.getTimerTask(), 0, timerScale * gate.getInterval());
        return ValidationState.VALID;
    }

    /**
     * Checks is the game is over
     */
    public void checkGameOver() {
        if (getCastle().getLife() <= 0) {
            onGameOver();
            return;
        }
        if (wavesCounter == waves.size() - 1) {
            onGameOver();
            return;
        }
    }

    /**
     * Gets money
     *
     * @return an int
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets money
     *
     * @param money the money to be set
     */
    public void setMoney(int money) {
        this.money = money;
    }
}
