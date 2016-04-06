package engine;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Map class providing the map of the game, the cells etc.
 */
public class Map {
    // TODO: 4/6/2016 Ali undust this class 
    /**
     * Private variables
     */
    private int width, height;
    private HashMap<Point, Cell> cells;
//    private ArrayList<Path> paths;

    /**
     * Constructs a map based on a map xml file.
     * @param world: the world in which the map is.
     * @throws Exception
     */
    public Map(Element mapElement, World world) throws Exception
    {


        // get width and height
        this.width = Integer.parseInt(mapElement.getAttribute("width"));
        this.height = Integer.parseInt(mapElement.getAttribute("height"));

        // create the hashmap
        cells = new HashMap<>();

        // generate free cells in the map
        generateCells();

    }

    /**
      * this will generate empty cells according to the width and height
      */
    private void generateCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point p = new java.awt.Point(x, y);
                Cell c = new Cell(p);
                cells.put(p, c);
            }
        }
    }

    /**
     * Get the cells.
     * @return returns a hashmap in which the key is the coordinates of the cell and the value is the cell
     */
    public HashMap<Point, Cell> getCells() {
        return cells;
    }

//    /**
//     * Set the paths manually
//     * @param paths
//     */
//    public void setPaths(ArrayList<Path> paths) {
//        this.paths = paths;
//    }

//    /**
//     * Get paths.
//     * @return returns an array list containing the paths.
//     */
//    public ArrayList<Path> getPaths() {
//        if (paths == null)
//            throw new IllegalStateException("me: myPaths is not set yet");
//        return paths;
//    }
}
