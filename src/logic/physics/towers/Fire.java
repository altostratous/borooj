package logic.physics.towers;

import logic.controllers.World;
import logic.physics.Tower;

import java.awt.*;

public class Fire extends Tower {
    private towerTypes HighPerformance = towerTypes.Tree;
    private towerTypes LowPerformance = towerTypes.Light;

    static {
        cost = 13;
        attackPower = 300;
        range = 5;
        staticInterval = 500;
    }

    public Fire(World world, Point base) {
        super(world, base, staticInterval);

    }


}
