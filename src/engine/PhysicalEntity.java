package engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class PhysicalEntity {
    private Map map;
    private ArrayList<Cell> cells;
    private Rectangle bounds;
    private int innerTime;
    private int interval;
    private World world;
    private HashMap<Point, Cell> area;

    public PhysicalEntity(World world, int interval) {
        setWorld(world);
        setInterval(interval);
        setMap(map);
    }

    public PhysicalEntity(World world) {
        setWorld(world);
    }

    public World getWorld() {
        return world;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getInnerTime() {

        return innerTime;
    }

    public void setInnerTime(int innerTime) {
        this.innerTime = innerTime;
    }

    public Rectangle getBounds() {

        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public ArrayList<Cell> getCells() {

        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        area = new HashMap<>();
        this.cells = cells;
        for (Cell cell : cells) {
            area.put(cell.getPosition(), cell);
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public HashMap<Point, Cell> getArea() {
        return area;
    }
}
