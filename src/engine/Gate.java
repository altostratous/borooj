package engine;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.ArrayList;

public class Gate extends PhysicalEntity{
    private ArrayList<Path> paths;
    public ArrayList<Path> getPaths() {
        return paths;
    }

//    public void setPaths(ArrayList<Path> paths) {
//        getWorld().getMap().setPaths(paths);
//    }

//    public Gate(World world, ArrayList<Path> paths)
//    {
//        super(world);
//        setPaths(paths);
//    }

    public Gate(NodeList pathNode, World world) throws Exception {
        super(world);
        //SET GATE CELLS
        generatePaths(pathNode);
    }

    private void generatePaths(NodeList pathsElements) {
        // init paths
        paths = new ArrayList<>();

        // for each path element in the map file
        for (int i = 0; i < pathsElements.getLength(); i++) {
            // current pathnode
            Node pathNode = pathsElements.item(i);
            // get directions from the map file, in u, l, r, d format
            String directions = pathNode.getTextContent();

            // set starting point of the path
            int x = Integer.parseInt(pathNode.getAttributes().getNamedItem("start_x").getTextContent());
            int y = Integer.parseInt(pathNode.getAttributes().getNamedItem("start_y").getTextContent());
            Point start = new Point(x, y);
            // temporary list for path cells
            ArrayList<Cell> pathCells = new ArrayList<>();
            // add the starting cell
            pathCells.add(getWorld().map.getCells().get(start));

            // for each direction
            for (int j = 0; j < directions.length(); j++) {

                // current direction
                char direction = directions.charAt(j);
                switch (direction) {
                    // left
                    case 'l':
                        x--;
                        break;
                    // right
                    case 'r':
                        x++;
                        break;
                    // up
                    case 'u':
                        y--;
                        break;
                    // down
                    case 'd':
                        y++;
                        break;
                }
                // add current cell to cells
                pathCells.add(getWorld().map.getCells().get(new Point(x, y)));
            }
            // create path from cells
            Path path = new Path(getWorld(), pathCells);
            // add the path to the paths
            paths.add(path);
        }
    }
}
