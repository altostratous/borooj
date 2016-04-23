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

    public Tower(World world, Point base, int interval, int range) {
        // init the tower, may be later extended
        super(world, interval);
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(getMap().getCells().get(base));
        setCells(cells);
        this.range = range;
        getWorld().getTimer().schedule(Tower.super.getTimerTask(), 0, Tower.super.getInterval());
    }

    private Cell priorAttackingEnemy(ArrayList<AliveEnemyUnit> listOfAlives) {
        ArrayList<AliveEnemyUnit> listOfPriors = new ArrayList<AliveEnemyUnit>();
        int minHealth = Integer.MAX_VALUE;
        for (AliveEnemyUnit a : listOfAlives) {
            if (distance(a.getCell(), this.getCells()) > range)
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
            if (distance(a.getCell(), getWorld().getCastle().getCells()) < minDistance) {
                minDistance = distance(a.getCell(), getWorld().getCastle().getCells());
                answer = a.getCell();
            }
        }
        if (minDistance == Integer.MAX_VALUE)
            return null;
        return answer;
    }

    @Override
    public void timerTick() {
        Cell target = this.priorAttackingEnemy(getWorld().getAliveEnemyUnits());
        if (target != null) {
            Missile missile = new Missile(200, target);
            missile.explode();
        }
    }
}
