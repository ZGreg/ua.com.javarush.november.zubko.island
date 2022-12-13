package entity.creature.animal.predator;

import java.util.Map;

public class Fox extends Predator{

    public Fox(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
