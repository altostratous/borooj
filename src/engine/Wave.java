package engine;

import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Each Wave is a group of enemies that enter the map but
 */
public class Wave {
    private Gate gate;
    private World world;
    private ArrayList<AliveEnemyUnit> units;
    private String XMLunits;

    public Wave(Gate gate, Node XMLnode, World world) {
        this.gate = gate;
        this.world = world;
        generateEnemies(XMLnode);
    }


    private void generateEnemies(Node node) {
        units = new ArrayList<>();
        Node XMLNode = node.getChildNodes().item(1);
        //get units
        String enemyType = XMLNode.getAttributes().getNamedItem("type").getTextContent();
        // TODO: 4/13/2016 Validate the input
        int count = Integer.parseInt(XMLNode.getAttributes().getNamedItem("count").getTextContent());
        for (int i = 0; i < count; i++) {

            //foreach unit in units construct alive enemy unit according to count and type of unit
            AliveEnemyUnit enemy;
            switch (enemyType) {
                case "Soldier":
                    enemy = new Soldier(200, 300, gate, world);
                    break;
                default:
                    throw new IllegalStateException("Enemy type is not defined.");
            }
            //add them to units ArrayList
            units.add(enemy);
        }
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

        // commented out by ali because the gate itself Does put enemies in the paths
//        //First call of pathGotFree
//        for (Path p : gate.getPaths()) {
//            gate.pathEntranceGotFree(p);
//        }
    }

    public void waveEnded() {
        gate.getWorld().callWave();
    }
}
