package engine;

/**
 * Each soldier has a path that is asignned by gate that make it start moving
 */
public class Soldier extends AliveEnemyUnit {
    public Soldier(int fullHealth, int timerInterval, Gate entringGate, World world) {
        super(fullHealth, timerInterval, entringGate, world);
    }


    /**
     * Moves the soldier to next cell
     */
    public void move() {
        stepCounter++;
        setCell(path.getCell(stepCounter));
        System.out.println("AliveEnemyUnit is on step " + stepCounter + " Position " + getCell().getPosition().toString());
        //Bug Here getCells is an ArrayList
        if (getCells().equals(path.getLastCell())) {
            if (getWorld().getCastle().getArea().containsValue(getCell())) {
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
        move();
    }

}
