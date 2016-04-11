package engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

/**
 * The class to model any Time and/or space based object in this Project
 */
public abstract class PhysicalEntity {
    // the map in which the entity is
    private Map map;
    // the cells on which the entity remain
    private ArrayList<Cell> cells;
    // the bounds of the entity
    private Rectangle bounds;
    // the local time for this instance
    private int innerTime;
    // the interval for the time based entity
    private int interval;
    // the world in which the entity is
    private World world;
    // a map to access cells by their position
    private HashMap<Point, Cell> area;

    /**
     * +
     * Creates
     *
     * @param world    the world in which the entity is
     * @param interval the interval of the entity to do job
     */
    public PhysicalEntity(World world, int interval) {
        setWorld(world);
        setInterval(interval);
        setMap(map);
    }

    /**
     * Creates the physical entity only from the containing  world
     * @param world
     */
    public PhysicalEntity(World world) {
        setWorld(world);
    }

    /**
     * The job to do. this method should be override to implement the job of the physical entity
     */
    public abstract void timerTick();

    /**
     * Get the world of the entity
     * @return returns a world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the interval of the physical entity
     * @return an integer
     */
    public int getInterval() {
        return interval;
    }

    /**
     * Sets the interval
     * @param interval the interval
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * set the world
     * @param world the world
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /***
     * Gets local time of the entity
     * @return returns an integer
     */
    public int getInnerTime() {

        return innerTime;
    }

    /**
     * Sets the lcoal time of teh entity
     * @param innerTime the time
     */
    public void setInnerTime(int innerTime) {
        this.innerTime = innerTime;
    }

    /**
     * Gets the bounds of the entity
     * @return returns a rectangle
     */
    public Rectangle getBounds() {

        return bounds;
    }

    /**
     * Sets the bounds of the entity
     * @param bounds the rectangle
     */
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     * Gets the cells
     * @return ArrayList<Cell>
     */
    public ArrayList<Cell> getCells() {

        return cells;
    }

    /**
     * sets the cells
     * @param cells the cells
     */
    public void setCells(ArrayList<Cell> cells) {
        area = new HashMap<>();
        this.cells = cells;
        for (Cell cell : cells) {
            area.put(cell.getPosition(), cell);
        }
    }

    /**
     * Gets the map
     * @return a map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Sets the map of the entity
     * @param map the value
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Gets a map of point to Cell from the cells of the entity
     * @return
     */
    public HashMap<Point, Cell> getArea() {
        return area;
    }

    /**
     * Gets the timer task to add it to the schedule of a timer
     * @return a TimerTask
     */
    public TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                timerTick();
            }
        };
    }
}
