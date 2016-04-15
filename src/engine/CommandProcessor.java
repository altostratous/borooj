package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CommandProcessor {
    // TODO: 4/6/2016 Ali implement this class
    private PrintStream out;
    private World world;
    private Scanner scanner;
    private boolean pleaseCancel;


    public CommandProcessor(PrintStream out, World world, InputStream inputStream) {
        this.out = out;
        this.world = world;
        this.scanner = new Scanner(inputStream);
    }

    //private boolean pleaseStop

    public void start() {
        while (!pleaseCancel) {
            String command = scanner.next();
            if (command.equals("exit")) {
                pleaseCancel = true;
                continue;
            }

            if (command.equals("new-game")) {
                newGame();
                continue;
            }
            if (command.equals("display")) {
                display();
                continue;
            }
            if (command.equals("set-config")) {
                setConfig();
                continue;
            }
            if (command.equals("set-map")) {
                setMap();
                continue;
            }
            if (command.equals("tower")) {
                tower();
            }
            if (command.equals("start-game")) {
                startGame();
            }

        }

    }

    private void startGame() {
        ValidationState validationState = world.start();
        if (validationState.equals(ValidationState.VALID)) {
            out.println("Game started successfully!");
        } else {
            out.println(validationState);
        }
    }

    private void tower() {
        Point base = new Point(scanner.nextInt(), scanner.nextInt());
        ValidationState validationState = world.addTower(base);
        if (validationState.equals(ValidationState.VALID)) {
            out.println("Tower added successfully!");
        } else {
            out.println(validationState);
        }
    }

    private void setMap() {
        String configPath = scanner.nextLine();
        ValidationState validationState = world.setMap(configPath);
        if (validationState.equals(ValidationState.VALID)) {
            out.println("Map set successfully!");
        } else {
            out.println(validationState);
        }
    }

    private void setConfig() {
        String configPath = scanner.nextLine();
        ValidationState validationState = world.setConfig(configPath);
        if (validationState.equals(ValidationState.VALID)) {
            out.println("Config set successfully!");
        } else {
            out.println(validationState);
        }
    }

    /**
     * To display the map
     */
    public void display() {
        Map map = world.getMap();
        char[][] table = new char[map.getHeight()][map.getWidth()];
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                table[j][i] = '#';
            }
        }
        // Printing paths
        for (Path path :
                world.getGate().getPaths()) {
            String direction = path.getDirection() + "C";
            for (int i = 0; i < path.getCells().size(); i++) {
                Cell cell = path.getCells().get(i);
                int x = (int) cell.getPosition().getX();
                int y = (int) cell.getPosition().getY();
                table[y][x] = direction.charAt(i);
            }
        }


        // Printing Gate
        for (Cell cell :
                world.getGate().getCells()) {
            int x = (int) cell.getPosition().getX();
            int y = (int) cell.getPosition().getY();
            table[y][x] = 'G';
        }

        // Printing Units
        for (PhysicalEntity pe : world.getPhysicalEntities()
                ) {
            if (pe instanceof AliveEnemyUnit) {

                int x = (int) ((AliveEnemyUnit) pe).getCell().getPosition().getX();
                int y = (int) ((AliveEnemyUnit) pe).getCell().getPosition().getY();
                table[y][x] = '1';
            }
        }


        int test = 1;
        // Printing castle
        for (Cell cell :
                world.getCastle().getCells()) {
            int x = (int) cell.getPosition().getX();
            int y = (int) cell.getPosition().getY();
            table[y][x] = 'C';
        }


        // Printing map
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                out.print(table[j][i]);
            }
            out.println("");
        }

    }

    private void newGame() {
        ValidationState validationState = world.newGame();
        if (validationState.equals(ValidationState.VALID)) {
            out.println("New game started successfully!");
        } else {
            out.println(validationState);
        }
    }
}
