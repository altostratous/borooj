package engine;

import java.awt.*;
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
