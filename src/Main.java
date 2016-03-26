import engine.World;

/**
 * In the name of ALLAH
 * Va ma ramyta ez ramyt valakenna Allah rama
 */
public class Main {
    public static void main(String[] args){
        try {
            World world = new World("data/map.xml", "data/configuration.xml");
        }
        catch (Exception ex)
        {
            System.out.println("Problem reading map file, details:");
            ex.printStackTrace();
        }
    }
}
