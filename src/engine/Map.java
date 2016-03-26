package engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    int width, height;
    public HashMap<Point, Cell> myCells;
    public ArrayList<Path> myPaths;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        myCells = new HashMap<>();
        generateCells();
    }

    private void generateCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point p = new java.awt.Point(x, y);
                Cell c = new Cell(p);
                myCells.put(p, c);
            }
        }
    }

    public void setPaths(ArrayList<Path> input) {
        myPaths = input;
    }

    public HashMap<Point, Cell> getCells() {
        return myCells;
    }

    public ArrayList<Path> getPaths() {
        if (myPaths == null)
            throw new IllegalStateException("me: myPaths is not set yet");
        return myPaths;
    }
}
