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
     * @param target the target that the missile goes to
     * @param power  the power of the missile
     */
    public Missile(Cell target, int power) {
        this.target = target;
        this.power = power;
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
