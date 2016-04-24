package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class models the tower which the player build for attacking enemies
 */
public class Tower extends PhysicalEntity {
    private int range;

    public static int getCost() {
        // TODO: 4/24/2016 Load from external resource 
        return 11;
    }

    /**
     * finds the distance between a cell and the tower
     *
     * @param a the Cell for calculating the distance to tower
     * @return the distance
     */
    private int distanceToTower(Cell a) {
        int minDistance = Integer.MAX_VALUE;
        int tmp;
        Point s = a.getPosition();
        Point t;
        for (Cell b : this.getCells()) {
            t = b.getPosition();
            tmp = Math.abs((int) s.getX() - (int) t.getX()) + Math.abs((int) s.getY() - (int) t.getY());
            if (tmp < minDistance)
                minDistance = tmp;
        }
        return minDistance;
    }

    /**
     * Constructs the tower
     *
     * @param world  the environment of the game
     * @param base  the coordinate of the tower
     * @param interval the fire rate of the tower
     * @param range the range of the tower
     */
    public Tower(World world, Point base, int interval, int range) {
        // init the tower, may be later extended
        super(world, interval);
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(getMap().getCells().get(base));
        setCells(cells);
        this.range = range;
        getWorld().getTimer().schedule(Tower.super.getTimerTask(), 0, Tower.super.getInterval());
    }

    /**
     * A private method for found the Cell of the enemy with lowest Health and nearer to castle
     * @param listOfAlives the list of  Alive enemies
     * @return a Cell
     */
    private Cell priorAttackingEnemy(ArrayList<AliveEnemyUnit> listOfAlives) {
        ArrayList<AliveEnemyUnit> listOfPriors = new ArrayList<>();
        int minHealth = Integer.MAX_VALUE;
        for (AliveEnemyUnit a : listOfAlives) {
            if (distanceToTower(a.getCell()) > range)
                continue;
            ;
            if (a.getHealth() == minHealth)
                listOfPriors.add(a);
            else if (a.getHealth() < minHealth) {
                minHealth = a.getHealth();
                listOfPriors.clear();
                listOfPriors.add(a);
            }
        }
        int minDistance = Integer.MAX_VALUE;
        Cell answer = new Cell();
        for (AliveEnemyUnit a : listOfPriors) {
            if (a.distanceToCastle() < minDistance) {
                minDistance = a.distanceToCastle();
                answer = a.getCell();
            }
        }
        if (minDistance == Integer.MAX_VALUE)
            return null;
        return answer;
    }

    /**
     * Do the job
     * Checks if we have a soldier in the range of the tower and then shoot a missile to the prior enemy
     */
    @Override
    public void timerTick() {
        Cell target = this.priorAttackingEnemy(getWorld().getAliveEnemyUnits());
        if (target != null) {
            Missile missile = new Missile(200, target);
            missile.explode();
        }
    }
}
