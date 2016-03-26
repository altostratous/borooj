package engine;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Map class providing the map of the game, the cells etc.
 */
public class Map {
    /**
     * Private variables
     */
    private int width, height;
    private HashMap<Point, Cell> cells;
    private ArrayList<Path> paths;

    /**
     * Constructs a map based on a map xml file.
     * @param filePath: path to the map xml file.
     * @param world: the world in which the map is.
     * @throws Exception
     */
    public Map(String filePath, World world) throws Exception
    {
        // do some stuff to get document form the map file
        File file = new File(filePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        // get the map element in the file
        Element mapElement = document.getDocumentElement();

        // get width and height
        this.width = Integer.parseInt(mapElement.getAttribute("width"));
        this.height = Integer.parseInt(mapElement.getAttribute("height"));

        // create the hashmap
        cells = new HashMap<>();

        // generate free cells in the map
        generateCells();

        // get all path nodes
        NodeList pathsElements = document.getElementsByTagName("path");
        // init paths
        paths = new ArrayList<>();

        // for each path element in the map file
        for (int i = 0; i < pathsElements.getLength(); i++) {
            // current pathnode
            Node pathNode = pathsElements.item(i);
            // get directions from the map file, in u, l, r, d format
            String directions = pathNode.getTextContent();

            // set starting point of the path
            int x = Integer.parseInt(pathNode.getAttributes().getNamedItem("start_x").getTextContent());
            int y = Integer.parseInt(pathNode.getAttributes().getNamedItem("start_y").getTextContent());
            Point start = new Point(x, y);
            // temporary list for path cells
            ArrayList<Cell> pathCells = new ArrayList<>();
            // add the starting cell
            pathCells.add(cells.get(start));

            // for each direction
            for (int j = 0; j < directions.length(); j++) {

                // current direction
                char direction = directions.charAt(j);
                switch (direction) {
                    // left
                    case 'l':
                        x--;
                        break;
                    // right
                    case 'r':
                        x++;
                        break;
                    // up
                    case 'u':
                        y--;
                        break;
                    // down
                    case 'd':
                        y++;
                        break;
                }
                // add current cell to cells
                pathCells.add(cells.get(new Point(x, y)));
            }
            // create path from cells
            Path path = new Path(world, pathCells);
            // add the path to the paths
            paths.add(path);
        }
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

    /**
     * Set the paths manually
     * @param paths
     */
    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    /**
     * Get paths.
     * @return returns an array list containing the paths.
     */
    public ArrayList<Path> getPaths() {
        if (paths == null)
            throw new IllegalStateException("me: myPaths is not set yet");
        return paths;
    }
}
