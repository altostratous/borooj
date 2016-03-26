package engine;

import java.awt.*;

public class Cell {
    private Point position;

    public Cell(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
}