package engine;

import engine.MapElements.Cell;
import engine.MapElements.Path;
import engine.MapElements.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    int width, height;
    public HashMap<String, Cell> myCells;
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
                Point p = new Point(x, y);
                Cell c = new Cell(p);
                myCells.put(p.toString(), c);
            }
        }
    }

    public void setPaths(ArrayList<Path> input) {
        myPaths = input;
    }

    public HashMap<String, Cell> getCells() {
        return myCells;
    }

    public ArrayList<Path> getPaths() {
        if (myPaths == null)
            throw new IllegalStateException("me: myPaths is not set yet");
        return myPaths;
    }
}
