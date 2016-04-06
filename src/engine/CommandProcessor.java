package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandProcessor {
    // TODO: 4/6/2016 Ali implement this class
    private PrintStream out;
    private World world;
    private Scanner scanner;
    private boolean pleaseCancel;

    public CommandProcessor(PrintStream out, World world, Scanner scanner) {
        this.out = out;
        this.world = world;
        this.scanner = scanner;
    }

    //private boolean pleaseStop;
    public CommandProcessor() {
        Scanner scanner = new Scanner(System.in);
    }

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

        }

    }

    private void tower() {
        throw new NotImplementedException();
    }

    private void setMap() {
        throw new NotImplementedException();
    }

    private void setConfig() {
        throw new NotImplementedException();
    }

    private void display() {
        Map map = world.getMap();
        char[][] table = new char[map.getHeight()][map.getWidth()];
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                table[j][i] = '#';

            }
        }

        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                out.print(table[j][i]);
            }
            out.println("");
        }

    }

    private void newGame() {
        throw new NotImplementedException();
    }

}
