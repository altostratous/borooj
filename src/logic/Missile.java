package logic;

import java.util.ArrayList;

/**
 * This class models the missile that will be shot from the tower
 */
public class Missile {
    // the target position
    private Cell target;
    // power of the
    private int power;

    /**
     * Constructs the missile
     *
     * @param power  the power of the missile
     * @param target the target of the missile
     */
    public Missile(int power, Cell target) {
        this.power = power;
        this.target = target;
    }

    /**
     * Sets the power of missile
     *
     * @param power the amount of damage this missile causes
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Sets the target of missile
     * @param target the target
     */
    public void setTarget(Cell target) {
        this.target = target;
    }

    /**
     * Gets the target
     * @return a Cell
     */
    public Cell getTarget() {
        return target;
    }

    /**
     * Gets the power
     * @return an int
     */
    public int getPower() {
        return power;
    }

    /**
     * Explodes in the target and causes damage for the entities in that cell
     */
    public void explode(){
        ArrayList<PhysicalEntity> entities= target.getEntities();
        for(int i=0;i<entities.size();i++){
            if(entities.get(i) instanceof AliveEnemyUnit){
                ((AliveEnemyUnit) entities.get(i)).damage(power);
            }
        }
    }

}
