package entity.creature.animal.herbivorous;

import java.util.Map;

public class Sheep extends Herbivorous{

    public Sheep(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
