package engine;

import java.util.ArrayList;

public class Gate extends PhysicalEntity{
    public ArrayList<Path> getPaths() {
        return getWorld().getMap().getPaths();
    }

    public void setPaths(ArrayList<Path> paths) {
        getWorld().getMap().setPaths(paths);
    }

    public Gate(World world, ArrayList<Path> paths)
    {
        super(world);
        setPaths(paths);
    }
}
