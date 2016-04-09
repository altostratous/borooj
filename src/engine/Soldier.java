package engine;

/**
 * Each soldier has a path that is asignned by gate that make it start moving
 */
public class Soldier extends AliveEnemyUnit {
    public Soldier(int fullHealth, int timerInterval, Gate entringGate, World world) {
        super(fullHealth, timerInterval, entringGate, world);
    }
//    public void startMove(Path path) {
//        this.path = path;
//        move();
//    }

}
