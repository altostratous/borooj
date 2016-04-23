package engine;

/**
 * Each soldier has a path that is asignned by gate that make it start moving
 */
public class Soldier extends AliveEnemyUnit {

    public Soldier(int fullHealth, int timerInterval, Gate entringGate, World world) {
        super(fullHealth, timerInterval, entringGate, world);
    }

    //public int getLimitedPath

    /**
     * Moves the soldier to next cell
     */
    public void move() {
        setStepCounter(getStepCounter() + 1);
        setCell(path.getCell(getStepCounter()));
//            if (path.getCells().size() == 8)
//        System.out.println(getStepCounter() + this.toString() + " "  + getCell().getPosition().toString());
        //Bug Here getCells is an ArrayList
        if (getCell().equals(path.getLastCell())) {
            if (getWorld().getCastle().getArea().containsKey(getCell().getPosition())) {
                getWorld().getCastle().damage(this);
            } else {
                throw new IllegalStateException("me: AliveEnemyUnit is in the last cell of path but it is not castle");
            }
        }

    }


    /**
     * Do the job
     */
    @Override
    public void timerTick() {
        // TODO: 4/15/2016 Resolve the overhead
        if (getHealth() > 0)
            move();
    }

}
