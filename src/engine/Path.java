package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;


/**
 * Path is used for determining which cells should be passes by soldiers
 */
public class Path extends PhysicalEntity{
    private static int idCounter = 1;
    private int myId;
    private boolean isEntranceFree;

    public Path(World world, ArrayList<Cell> cells) {
        super(world);
        setCells(cells);
        myId = idCounter;
        idCounter++;
        isEntranceFree = true;
    }

    public void addCell(Cell input) {
        getCells().add(input);
    }

    public Cell getCell(int n) {
        return getCells().get(n - 1);
    }

    public Cell getLastCell() {
        return getCells().get(getCells().size() - 1);
    }

    @Override
    public void timerTick() {
        throw new NotImplementedException();
    }

    public boolean isEntranceFree() {
        return isEntranceFree;
    }
}
