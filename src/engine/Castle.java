package engine;

import engine.MapElements.Cell;
import engine.Units.AliveEnemyUnit;

import java.util.HashMap;

public class Castle {
    HashMap<String, Cell> area;
    int Life;
    World world;

    public Castle(HashMap<String, Cell> area, int Life, World world) {
        this.area = area;
        this.Life = Life;
        this.world = world;
    }

    public HashMap<String, Cell> getArea() {
        return area;
    }

    private void decreaseLife() {
        Life -= 1;
        if (Life <= 0) {
            world.gameOver();
        }
    }
    public void damage(AliveEnemyUnit attacker) {
        decreaseLife();
        attacker.destroyMe();
    }
}
