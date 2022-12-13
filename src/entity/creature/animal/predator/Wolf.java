package entity.creature.animal.predator;

import java.util.Map;

public class Wolf extends Predator{


    public Wolf(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
