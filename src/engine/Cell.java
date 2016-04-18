package engine;

import java.awt.*;
import java.util.ArrayList;

/**
 * Models a cell in the map
 */
public class Cell {
    // obvious
    private Point position;

    // obvious
    private ArrayList<PhysicalEntity> entities;

    /**
     * Gets the entities on this cell
     *
     * @return an ArrayList<PhysicalEntity>
     */
    public ArrayList<PhysicalEntity> getEntities() {
        return entities;
    }

    /**
     * Sets the entities in the cell
     * @param entities
     */
    public void setEntities(ArrayList<PhysicalEntity> entities) {
        this.entities = entities;
    }

    /**
     * Creates new free cell from its position
     * @param position the postion of the cell
     */
    public Cell(Point position) {
        this.position = position;
        this.entities = new ArrayList<>();
    }

    public Cell() {
    }
    /**
     * Gets the position of the cell
     * @return a Point
     */
    public Point getPosition() {
        return position;
    }
}