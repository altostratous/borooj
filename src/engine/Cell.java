package engine;

import java.awt.*;

public class Cell {
    private java.awt.Point myPosition;

    public Cell(java.awt.Point position) {
        myPosition = position;
    }

    public java.awt.Point getPosition() {
        return myPosition;
    }
}