package logic.physics;

import logic.controllers.World;
import logic.models.Cell;
import org.w3c.dom.Node;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

/**
 * The class to model the castle
 */
public class Castle extends PhysicalEntity {
    // life of the castle
    private int life;

    public Castle(Node castleElement, World world) {
        super(world);
        setInterval(1000);
        // set life based on attribute
        int life = Integer.parseInt(castleElement.getAttributes().getNamedItem("life").getTextContent());
        setLife(life);

        // get x and y of starting point of the castle
        int x = Integer.parseInt(castleElement.getAttributes().getNamedItem("x").getTextContent());
        int y = Integer.parseInt(castleElement.getAttributes().getNamedItem("y").getTextContent());

        // get width and height
        int width = Integer.parseInt(castleElement.getAttributes().getNamedItem("width").getTextContent());
        int height = Integer.parseInt(castleElement.getAttributes().getNamedItem("height").getTextContent());

        // create array list of cells
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells.add(world.getMap().getCells().get(new Point(x + i, y + j)));
            }
        }

        // set cells and the world
        setCells(cells);
        setMap(world.getMap());

    }

    /**
     * Get life
     */
    public int getLife() {
        return life;
    }

    /**
     * Set life
     *
     * @param life the life
     */
    public void setLife(int life) {
        this.life = life;
    }


    /***
     * Constructs a castle
     * @param cells cells on which the Castle is
     * @param life the initial life for the castle
     * @param world the world in which the castle is
     */
//    public Castle(ArrayList<Cell> cells, int life, World world) {
//        super(world);
//        setCells(cells);
//        setLife(life);
//    }

    /**
     * Decrease life by one unit
     */
    private void decreaseLife() {
        // TODO: 4/15/2016 Decreasing Life has BUGS Ali Asgari
        setLife(getLife() - 1);
        if (getLife() <= 0) {
            getWorld().onGameOver();
        }
    }

    /**
     * Simulates an attack to the castle by an AliveEnemyUnit
     *
     */
    public void damage(AliveEnemyUnit attacker) {
        decreaseLife();
        attacker.destroy();
        if (getWorld().getAliveEnemyUnits().size() == 0)
            getWorld().checkGameOver();
    }

    /**
     * The job that the castle does, may be some graphical actions later
     */
    @Override
    public void timerTick() {

    }
}
