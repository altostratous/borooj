package engine;

//COLOR CODE FROM: http://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

import java.awt.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
            run(command);
        }

    }

    private void startGame() {
        ValidationState validationState = world.start();
        if (validationState.equals(ValidationState.VALID)) {
            out.println("Game started successfully!");
        } else {
            out.println(validationState);
        }
        CommandProcessor th = this;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //cmdp.clear();
                th.printNewLine(10);
                th.display();

            }
        }, 0, 2000);
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
        boolean problem = true;
        while (problem) {
            try {
                String configPath = scanner.nextLine();
                ValidationState validationState = world.setConfig(configPath);
                if (validationState.equals(ValidationState.VALID)) {
                    out.println("Config set successfully!");
                } else {
                    out.println(validationState);
                }
            } catch (Exception ex) {
                problem = true;
            }
        }
    }

    /**
     * prints \n as many as count!
     *
     * @param count count of \n
     */
    public void printNewLine(int count) {
        for (int i = 0; i < count; i++) {
            out.print("\n");
        }
    }

    /**
     * To display the map
     */
    public void display() {
        Map map = world.getMap();
        out.println("Life: " + world.getCastle().getLife());
        String[][] table = new String[map.getHeight()][map.getWidth()];
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                table[j][i] = "" + '#';
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
                table[y][x] = "" + direction.charAt(i);
                if (table[y][x].equals("" + 'r'))
                    table[y][x] = "" + '>';
                if (table[y][x].equals("" + 'l'))
                    table[y][x] = "" + '<';
                if (table[y][x].equals("" + 'u'))
                    table[y][x] = "" + '^';
                if (table[y][x].equals("" + 'd'))
                    table[y][x] = "" + 'v';
            }
        }


        // Printing Gate
        for (Cell cell :
                world.getGate().getCells()) {
            int x = (int) cell.getPosition().getX();
            int y = (int) cell.getPosition().getY();
            table[y][x] = "" + 'G';
        }

        // Printing Units
        for (PhysicalEntity pe : world.getPhysicalEntities()
                ) {
            if (pe instanceof AliveEnemyUnit) {

                int x = (int) ((AliveEnemyUnit) pe).getCell().getPosition().getX();
                int y = (int) ((AliveEnemyUnit) pe).getCell().getPosition().getY();

                table[y][x] = "\u001B[31m" + table[y][x] + "\u001B[0m";
            } else if (pe instanceof Tower) {
                //ATTENTION: getCells.get(0) does NOT work with multi cell towers
                int x = (int) ((Tower) pe).getCells().get(0).getPosition().getX();
                int y = (int) ((Tower) pe).getCells().get(0).getPosition().getY();

                table[y][x] = "T";
            }
        }


        // Printing castle
        for (Cell cell :
                world.getCastle().getCells()) {
            int x = (int) cell.getPosition().getX();
            int y = (int) cell.getPosition().getY();
            table[y][x] = "" + 'C';
        }


        // Printing map
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
//                if (table[j][i].equals("" + '1'))
//                    out.print("\u001B[31m");
                out.print(table[j][i]);

//                if (table[j][i].equals("" + '1'))
//                    out.print("\u001B[0m");
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

    public void run(String command) {
        if (command.equals("exit")) {
            pleaseCancel = true;
            return;
        }

        if (command.equals("new-game")) {
            newGame();
            return;
        }
        if (command.equals("display")) {
            display();
            return;
        }
        if (command.equals("set-config")) {
            setConfig();
            return;
        }
        if (command.equals("set-map")) {
            setMap();
            return;
        }
        if (command.equals("tower")) {
            tower();
        }
        if (command.equals("start-game")) {
            startGame();
        }
    }
}
