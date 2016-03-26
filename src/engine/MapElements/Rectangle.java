package engine.MapElements;

import engine.World;

import java.util.ArrayList;
import java.util.HashMap;

public class Rectangle {
    ArrayList<Cell> myCells;
    Point p1;
    Point p2;
    World world;
    public Rectangle(String RectangleString, World world) {
        this.world = world;
        String[] coordinates = RectangleString.split(" ");
        p1 = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        p2 = new Point(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]));
        isHorizontal(); //For validating data
        if (p1.equals(p2))
            throw new IllegalArgumentException("me: p1 and p2 are equal");
        generateArrayList();
    }
    public ArrayList<Cell> getCells() {
        return myCells;
    }
    public HashMap<String, Cell> getHashMapOfCells() {
        HashMap<String, Cell> result = new HashMap<>();
        for (int i = 0; i < myCells.size(); i++) {
            result.put(myCells.get(i).getPosition().toString(), myCells.get(i));
        }
        return result;
    }

    private void generateArrayList() {
        myCells = new ArrayList<>();
        if ((p1.getX() > p2.getX()) || (p1.getY() > p2.getY()))
            throw new IllegalArgumentException("me: It doesn't support p1X > p2X || p1Y > p2Y");
        if (isHorizontal()) {
            for (int x = p1.getX(); x <= p2.getX(); x++) {
                Point p = new Point(x, p1.getY());
                Cell currentCell = world.map.getCells().get(p);
                myCells.add(currentCell);
            }
        }
        else {
            for (int y = p1.getY(); y <= p2.getY(); y++) {
                Point p = new Point(p1.getX(), y);
                Cell currentCell = world.map.getCells().get(p.toString());
                myCells.add(currentCell);
            }
        }
    }

    public boolean isHorizontal() {
        if (p1.getX() == p2.getX()) {
            return false;
        }
        if (p1.getY() == p2.getY())
            return true;
        else
            throw new IllegalArgumentException("me: p1 and p2 are not in one line");
    }
}
