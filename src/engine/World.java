package engine;

import java.awt.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

//import java.util.HashMap;
import java.util.Scanner;

public class World {
    public Castle castle;
    public Map map;
    //public HashMap<Point, Cell> cells;
    private Scanner scanner;
    private int money;

    public World() {
        scanner = new Scanner(System.in);
        System.out.println("Enter money:");
        money = scanner.nextInt();
        generateControls();
    }


    private void genCastle() {
        System.out.println("Enter Castle Rectangle (4 numbers with space between for 2 points of rectangle):");
        scanner.nextLine();
        // Rectangle rec = new Rectangle(scanner.nextLine(), this);
        //castle = new Castle(rec.getHashMapOfCells(), 30, this);
    }
    public void generateControls() {
        //generate map
        System.out.println("Enter width: ");
        int width = scanner.nextInt();
        System.out.println("Enter height: ");
        int height = scanner.nextInt();

        map = new Map(width, height);


        //generate castle
        genCastle();

        //generate gate
    }
    public void gameOver() {
        System.out.println("gameOver!");
        throw new NotImplementedException();
    }
}
