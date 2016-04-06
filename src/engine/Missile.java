package engine;

import java.util.ArrayList;

/**
 * Created by Hamid on 4/6/2016.
 */
public class Missile {
    private Cell target;
    private int power;

    public Missile(Cell target, int power) {
        this.target = target;
        this.power = power;
    }
    public void explode(){
        ArrayList<PhysicalEntity> entities= target.getEntities();
        for(int i=0;i<entities.size();i++){
            if(entities.get(i) instanceof AliveEnemyUnit){
                ((AliveEnemyUnit) entities.get(i)).damage(power);
            }
        }
    }

}
