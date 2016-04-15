import engine.CommandProcessor;
import engine.World;

import java.util.Timer;
import java.util.TimerTask;

/**
 * In the name of ALLAH
 * Va ma ramyta ez ramyt valakenna Allah rama
 -*/
public class Main {
    public static void main(String[] args) {
        try {
            Timer timer = new Timer();
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