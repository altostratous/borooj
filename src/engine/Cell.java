package engine;

import java.awt.*;

public class Cell {
    private Point myPosition;

    public Cell(Point position) {
        myPosition = position;
    }

    public Point getPosition() {
        return myPosition;
    }
}