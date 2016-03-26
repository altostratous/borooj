package engine;

import java.util.ArrayList;

public class Castle extends PhysicalEntity {
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    private int life;

    public Castle(ArrayList<Cell> cells, int life, World world) {
        super(world);
        setCells(cells);
        setLife(life);
    }

    private void decreaseLife() {
        setLife(getLife() - 1);
        if (getLife() <= 0) {
            getWorld().gameOver();
        }
    }
    public void damage(AliveEnemyUnit attacker) {
        decreaseLife();
        attacker.destroyMe();
    }
}