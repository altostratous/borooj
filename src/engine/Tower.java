package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by HP PC on 4/17/2016.
 */
public class Tower extends PhysicalEntity {
    // TODO: 4/17/2016 Hamid implement this class please
    public Tower(World world, int interval) {
        super(world, interval);
        // do the stuff here
    }

    public Tower(World world, Point base, int interval) {
        // init the tower, may be later extended
        this(world, interval);
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(getMap().getCells().get(base));
        setCells(cells);
    }

    @Override
    public void timerTick() {
        throw new NotImplementedException();
    }
}
