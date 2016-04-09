package engine;

import java.util.ArrayList;

/**
 * Each Wave is a group of enemies that enter the map but
 */
public class Wave {
    private Gate gate;
    private ArrayList<AliveEnemyUnit> units;
    private String XMLunits;

    public Wave(Gate gate, String XMLunits) {
        this.gate = gate;
        this.XMLunits = XMLunits;
    }

    private void generateEnemies() {
        units = new ArrayList<>();
        //TODO: Generate Enemy Objects, According to the XML
    }

    public void addEnemiesToGate() {
        for (AliveEnemyUnit unit : units) {
            gate.addEnemy(unit);
        }

        //First call of pathGotFree
        for (Path p : gate.getPaths()) {
            gate.pathEntranceGotFree(p);
        }
    }
}
