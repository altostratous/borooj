package engine;

import java.awt.*;
import java.util.ArrayList;

public class Cell {
    private Point position;

    private ArrayList<PhysicalEntity> entities;

    public ArrayList<PhysicalEntity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<PhysicalEntity> entities) {
        this.entities = entities;
    }

    public Cell(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
}