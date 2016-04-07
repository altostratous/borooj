import engine.CommandProcessor;
import engine.World;

import java.util.Scanner;

/**
 * In the name of ALLAH
 * Va ma ramyta ez ramyt valakenna Allah rama
 */
public class Main {
    public static void main(String[] args){
        try {
            World world = new World("data/map.xml", "data/configuration.xml");
            CommandProcessor cmdp = new CommandProcessor(System.out, world, System.in);
            cmdp.start();
        }
        catch (Exception ex)
        {
            System.out.println("Problem reading map file, details:");
            ex.printStackTrace();
        }
    }
}
