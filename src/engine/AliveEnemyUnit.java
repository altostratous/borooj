package engine;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AliveEnemyUnit extends PhysicalEntity {
    protected int health;
    protected int fullHealth;
    protected Path path;
    protected int stepCounter;

    // TODO: 4/6/2016 Mohammad complete the documentation on this file 
    public AliveEnemyUnit(int fullHealth, int timerInterval, Gate entringGate, World world) {
        super(world, timerInterval);

        this.fullHealth = fullHealth;
        this.health = fullHealth;
    }

    public void enterTheMap(Path path) {
        this.stepCounter = 1;
        this.path = path;
        setCell(path.getCell(stepCounter));
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                move();
//            }
//        }, timerInterval);
    }

    public void timerTick()
    {
        move();
    }

    public Cell getCell()
    {
        return getCells().get(0);
    }

    public void setCell(Cell cell)
    {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(cell);
        setCells(cells);
    }

    public void move() {
        stepCounter++;
        setCell(path.getCell(stepCounter));
        System.out.println("AliveEnemyUnit is on step " + stepCounter + " Position " + getCell().getPosition().toString());
        if (getCells().equals(path.getLastCell())) {
            if (getWorld().castle.getArea().containsValue(getCell())) {
                getWorld().castle.damage(this);
            } else {
                throw new IllegalStateException("me: AliveEnemyUnit is in the last cell of path but it is not castle");
            }
        }

    }

    public void damage(int value) {
        if (value >= health) {
            health = 0;
            destroyMe();
        } else {
            health -= value;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void destroy()
    {
        damage(getHealth());

    }

    public void destroyMe() {
        health = 0;
        // timer.cancel();
        System.out.println("AliveEnemyUnit is destroyed");
    }

    public int getFullHealth() {
        return fullHealth;
    }
}
