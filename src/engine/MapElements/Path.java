package engine.MapElements;

import java.util.ArrayList;
//import java.util.HashMap;

public class Path {
    ArrayList<Cell> containingCells;

    private static int idCounter = 1;
    int myId;
    public boolean isEntranceFree;

    public Path() {
        containingCells = new ArrayList<>();
        myId = idCounter;
        idCounter++;
        isEntranceFree = true;
    }

    public void addCell(Cell input) {
        containingCells.add(input);
    }

    public ArrayList<Cell> getContainingCells() {
        return containingCells;
    }

    public Cell getNthCell(int n) {
        return containingCells.get(n - 1);
    }

    public Cell getLastCell() {
        return containingCells.get(containingCells.size() - 1);
    }
}
