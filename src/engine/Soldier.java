package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Each soldier has a path that is asignned by gate that make it start moving
 */
public class Soldier extends AliveEnemyUnit {
    public Soldier(int fullHealth, int timerInterval, Gate entringGate, World world) {
        super(fullHealth, timerInterval, entringGate, world);
    }

    @Override
    public void timerTick() {
        throw new NotImplementedException();
    }
//    public void startMove(Path path) {
//        this.path = path;
//        move();
//    }

}
