package engine;

import java.util.ArrayList;

/**
 * Created by Hamid on 4/6/2016.
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
     */
    public Missile(int power, Cell target) {
        this.power = power;
        this.target = target;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setTarget(Cell target) {
        this.target = target;
    }

    public Cell getTarget() {
        return target;
    }

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
