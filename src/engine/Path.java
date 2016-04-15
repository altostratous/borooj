package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;


/**
 * Path is used for determining which cells should be passes by soldiers
 */
public class Path extends PhysicalEntity{
    // local vars
    private static int idCounter = 1;
    private int myId;

    public String getDirection() {
        return direction;
    }

    private String direction;

    public Path(World world, ArrayList<Cell> cells, String direction) {
        super(world);
        this.direction = direction;
        setCells(cells);
        myId = idCounter;
        idCounter++;
    }

    /**
     * Adds a cell to the cells of the path
     *
     * @param input the cell to be added to the path
     */
    public void addCell(Cell input) {
        getCells().add(input);
    }

    /**
     * Gets the nth cell
     * @param n one based the index of the cell
     * @return a cell
     */
    public Cell getCell(int n) {
        return getCells().get(n - 1);
    }

    /**
     * Gets the last cell of the path
     * @return a cell
     */
    public Cell getLastCell() {
        return getCells().get(getCells().size() - 1);
    }

    /**
     * if the path will do something, nothing yet
     */
    @Override
    public void timerTick() {

    }

    /**
     * Checks if the entrance is free
     * @return a boolean
     */
    public boolean isEntranceFree() {
        return getCells().get(0).getEntities().size() == 0;
    }
}
