package engine;

import java.util.Scanner;

public class CommandProcessor {
    // TODO: 4/6/2016 Ali implement this class 
    private String currentCommand;
    private Scanner scanner;
    //private boolean pleaseStop;
    public CommandProcessor() {
        currentCommand = "";
        Scanner scanner = new Scanner(System.in);
    }
    public void start() {
        while (true) {
            currentCommand = scanner.next();
            if (currentCommand.equals("make")) {
                make();
                continue;
            }
        }

    }
    private void make() {
        String objName = scanner.next();
        if (objName.equals("world")) {

        }
    }
}
