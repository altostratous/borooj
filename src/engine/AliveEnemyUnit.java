package engine;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/***
 * This class models alive enemy units
 */
public abstract class AliveEnemyUnit extends PhysicalEntity {
    // local vars
    protected int health;
    protected int fullHealth;
    protected Path path;

    public int getStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    private int stepCounter;


    /**
     * Constructs Alive Enemy Unit by given properties
     */
    public AliveEnemyUnit(int fullHealth, int timerInterval, Gate entringGate, World world) {
        super(world, timerInterval);

        this.fullHealth = fullHealth;
        this.health = fullHealth;
    }

    /**
     * Puts this in a map
     *
     * @param path the path to put this in
     */
    public void enterTheMap(Path path) {
        this.stepCounter = 1;
        this.path = path;
        setCell(path.getCell(stepCounter));
        getWorld().addPhysicalEntity(this);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                move();
//            }
//        }, timerInterval);
    }

    /**
     * If the enemy unit is remained in a single cell gets that
     * @return RETURN A cell
     */
    public synchronized Cell getCell() {
        if (getCells().size() != 1)
            throw new InvalidStateException("The Alive enemy unit is not remained in a single cell");
        return getCells().get(0);
    }


    /**
     * If the enemy unit is remained in a single cell sets it
     * @param cell the sell
     */
    public void setCell(Cell cell) {
//        if (getCells().size() != 1)
//            throw new InvalidStateException("The Alive enemy unit is not remained in a single cell");
        if (getCells() != null)
            getCell().getEntities().remove(this);
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(cell);
        cell.getEntities().add(this);
        setCells(cells);
    }


    /**
     * Decrease the health of the alive enemy unit
     * @param value the amount of damage to apply on the alive enemy unit
     */
    public void damage(int value) {
        if (value >= health) {
            health = 0;
            getWorld().getCmdp().print("\u001B[34m" + "AliveEnemyUnit at " + getCell().getPosition().toString() + " is destroyed" + "\u001B[0m");
            getWorld().getCmdp().printNewLine(1);
            getWorld().getPhysicalEntities().remove(this);
        } else {
            health -= value;
        }
    }

    /**
     * Get the health
     * @return returns the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the enemy unit
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Damages the alive enemy unit completely
     */
    public void destroy()
    {
        damage(getHealth());

    }

    /**
     * returns count of left cells to castle
     *
     * @return
     */
    public int distanceToCastle() {
        return path.getCells().size() - stepCounter;
    }

    /**
     * Get the full health
     * @return returns an int
     */
    public int getFullHealth() {
        return fullHealth;
    }
}
