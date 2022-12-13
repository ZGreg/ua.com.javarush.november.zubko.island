package entity.creature.animal.predator;

import java.util.Map;

public class Bear extends Predator{

    public Bear(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
