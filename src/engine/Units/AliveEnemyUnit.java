package engine.Units;

import engine.Gate;
import engine.MapElements.Cell;
import engine.MapElements.Path;
import engine.World;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AliveEnemyUnit implements IContainsTimer, IEnemyUnit {
    protected int health;
    protected int fullHealth;
    protected int timerInterval;
    protected Cell cell;
    protected Gate enteringGate;
    protected Path myPath;
    protected int stepCounter;
    protected World world;
    protected Timer timer;

    public AliveEnemyUnit(int fullHealth, int timerInterval, Gate entringGate, World world) {
        this.world = world;
        this.fullHealth = fullHealth;
        this.health = fullHealth;
        this.timerInterval = timerInterval;
        this.enteringGate = entringGate;
    }

    public void enterTheMap(Path Path) {
        this.stepCounter = 1;
        this.myPath = Path;
        cell = myPath.getNthCell(stepCounter);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                move();
            }
        }, timerInterval);
    }

    public void move() {
        stepCounter++;
        cell = myPath.getNthCell(stepCounter);
        System.out.println("AliveEnemyUnit is on step " + stepCounter + " Position " + cell.getPosition().toString());
        if (cell.equals(myPath.getLastCell())) {
            if (world.castle.getArea().containsValue(cell)) {
                world.castle.damage(this);
            } else {
                throw new IllegalStateException("me: AliveEnemyUnit is in the last cell of path but it is not castle");
            }
        }

    }

    public void damageMe(int value) {
        if (value >= health) {
            health = 0;
            destroyMe();
        } else {
            health -= value;
        }
    }

    public void destroyMe() {
        health = 0;
        timer.cancel();
        System.out.println("AliveEnemyUnit is destroyed");
    }

    @Override
    public int getFullHealth() {
        return fullHealth;
    }
}
