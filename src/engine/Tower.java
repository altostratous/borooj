package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by HP PC on 4/17/2016.
 */
public class Tower extends PhysicalEntity {
    // TODO: 4/17/2016 Hamid implement this class please
    private int range;
    private Missile missile;

    private int distance(Cell a, ArrayList<Cell> arr) {
        int minDistance = Integer.MAX_VALUE;
        int tmp;
        Point s = a.getPosition();
        Point t;
        for (Cell b : arr) {
            t = b.getPosition();
            tmp = Math.abs((int) s.getX() - (int) t.getX()) + Math.abs((int) s.getY() - (int) t.getY());
            if (tmp < minDistance)
                minDistance = tmp;
        }
        return minDistance;
    }

    public Tower(World world, int interval, int range, Missile missile) {
        super(world, interval);
        this.range = range;
        this.missile = missile;
    }

    public Tower(World world, Point base, int interval, int range, Missile missile) {
        // init the tower, may be later extended
        super(world, interval);
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(getMap().getCells().get(base));
        setCells(cells);
        this.range = range;
        this.missile = missile;
    }

    public Cell priorAttackingEnemy(ArrayList<AliveEnemyUnit> listOfAlives) {
        ArrayList<AliveEnemyUnit> listOfPriors = new ArrayList<AliveEnemyUnit>();
        int minHp = Integer.MAX_VALUE;
        for (AliveEnemyUnit a : listOfAlives) {
            if (distance(a.getCell(), this.getCells()) > range)
                continue;
            ;
            if (a.getHealth() == minHp)
                listOfPriors.add(a);
            else if (a.getHealth() < minHp) {
                minHp = a.getHealth();
                listOfPriors.clear();
                listOfPriors.add(a);
            }
        }
        int minDistance = Integer.MAX_VALUE;
        Cell answer = new Cell();
        for (AliveEnemyUnit a : listOfPriors) {
            if (distance(a.getCell(), castle.getCells()) < minDistance) {
                minDistance = distance(a.getCell(), castle.getCells());
                answer = a.getCell();
            }
        }
        return answer;
    }

    public int getMissilePower() {
        return this.missile.getPower();
    }

    public void fire(Cell target) {
        this.missile.setTarget(target);
        this.missile.explode();
    }

    @Override
    public void timerTick() {
        throw new NotImplementedException();
    }
}
