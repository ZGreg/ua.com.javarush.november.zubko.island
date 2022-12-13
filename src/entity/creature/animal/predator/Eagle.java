package entity.creature.animal.predator;

import java.util.Map;

public class Eagle extends Predator{

    public Eagle(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
