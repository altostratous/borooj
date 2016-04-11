package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * The class to model the castle
 */
public class Castle extends PhysicalEntity {
    // life of the castle
    private int life;

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
    public Castle(ArrayList<Cell> cells, int life, World world) {
        super(world);
        setCells(cells);
        setLife(life);
    }

    /**
     * Decrease life by one unit
     */
    private void decreaseLife() {
        setLife(getLife() - 1);
        if (getLife() <= 0) {
            getWorld().onGameOver();
        }
    }

    /**
     * Simulates an attack to the castle by an AliveEnemyUnit
     * @param attacker
     */
    public void damage(AliveEnemyUnit attacker) {
        decreaseLife();
        attacker.destroy();
    }

    /**
     * The job that the castle does, may be some graphical actions later
     */
    @Override
    public void timerTick() {
        return;
    }
}
