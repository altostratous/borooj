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

public class Map {
    int width, height;

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    private HashMap<Point, Cell> cells;
    private ArrayList<Path> paths;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new HashMap<>();
        generateCells();
    }

    public Map(String filePath, World world)
    {
        File file = new File("userdata.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            Element mapElement = document.getDocumentElement();
            this.width = Integer.parseInt(mapElement.getAttribute("width"));
            this.height = Integer.parseInt(mapElement.getAttribute("height"));
            cells = new HashMap<>();
            generateCells();

            NodeList pathsElements = document.getElementsByTagName("path");
            paths = new ArrayList<>();

            for (int i = 0; i < pathsElements.getLength(); i++) {
                Node pathNode = pathsElements.item(i);
                String directions = pathNode.getTextContent();
                int x = Integer.parseInt(pathNode.getAttributes().getNamedItem("start_x").toString());
                int y = Integer.parseInt(pathNode.getAttributes().getNamedItem("start_y").toString());
                Point start = new Point(x, y);
                ArrayList<Cell> pathCells = new ArrayList<>();
                pathCells.add(cells.get(start));
                for (int j = 0; j < directions.length(); j++) {
                    char direction = directions.charAt(j);
                    switch (direction) {
                        case 'l':
                            x--;
                            break;
                        case 'r':
                            x++;
                            break;
                        case 'u':
                            y--;
                            break;
                        case 'd':
                            y++;
                            break;
                    }
                    pathCells.add(cells.get(new Point(x, y)));
                }
                Path path = new Path(world, pathCells);
                paths.add(path);
            }
        }
        catch (Exception ex)
        {

        }
    }

    private void generateCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point p = new java.awt.Point(x, y);
                Cell c = new Cell(p);
                cells.put(p, c);
            }
        }
    }

    public HashMap<Point, Cell> getCells() {
        return cells;
    }

    public void setCells(HashMap<Point, Cell> cells) {
        this.cells = cells;
    }

    public ArrayList<Path> getPaths() {
        if (paths == null)
            throw new IllegalStateException("me: myPaths is not set yet");
        return paths;
    }
}
