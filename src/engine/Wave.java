package engine;

import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Each Wave is a group of enemies that enter the map but
 */
public class Wave {
    private Gate gate;
    private ArrayList<AliveEnemyUnit> units;
    private String XMLunits;

    public Wave(Gate gate, Node XMLnode) {
        this.gate = gate;
        generateEnemies(XMLnode);
    }


    private void generateEnemies(Node XMLnode) {
        units = new ArrayList<>();
        //TODO: Generate Enemy Objects, According to the XML

        //get units
        //foreach unit in units construct alive enemy unit according to count and type of unit
        //add them to units ArrayList
    }

    /**
     * This method starts the wave for the player and add generated enemy Objects to the gate
     */
    public void startWaving() {
        addEnemiesToGate();
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

    public void waveEnded() {
        gate.getWorld().callWave();
    }
}
