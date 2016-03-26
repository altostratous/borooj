package engine.MapElements;

import java.util.Scanner;

public class Point {
    int x;
    int y;

    public Point(int _x, int _y) {
        x = _x;
        y = _y;
    }

    /**
     *
     * @param PointString is used fot representing like: x y
     */
    public Point(String PointString) {
        Scanner scanner = new Scanner(PointString);
        if (scanner.hasNextInt()){
            x = scanner.nextInt();
            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
            }
            else
                throw new IllegalArgumentException("me: Point String doesn't have int for y, PointString: " + PointString);
        }
        else
            throw new IllegalArgumentException("me: Point String doesn't have int for x, PointString: " + PointString);
        if (scanner.hasNextInt())
            throw new IllegalArgumentException("me: PointString data is more than needed, PointString: " + PointString);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}
