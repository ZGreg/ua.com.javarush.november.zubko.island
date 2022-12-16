package util;

import java.util.LinkedList;
import java.util.List;

public class DirectionCounter {                                                 // make non-static

     private static LinkedList<Integer> directions = new LinkedList<>();

    public static List<Integer> getAllDirections(int id, int speed){

            directions.add(countUp(id, speed));
            directions.add(countDown(id, speed));
            directions.add(countLeft(id, speed));
            directions.add(countRight(id, speed));

        return directions;
    }


    private static int countUp(int id, int speed){
        return id - (speed * 10);
    }

    private static int countDown(int id, int speed){
        return id + (speed * 10);
    }

    private static int countLeft(int id, int speed){
        return id - speed;
    }

    private static int countRight(int id, int speed){
        return id + speed;
    }

}
