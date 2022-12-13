package entity.creature.animal.herbivorous;

import java.util.Map;

public class Horse extends Herbivorous{


    public Horse(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
