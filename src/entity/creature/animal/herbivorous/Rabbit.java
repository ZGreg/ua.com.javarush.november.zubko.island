package entity.creature.animal.herbivorous;

import java.util.Map;

public class Rabbit extends Herbivorous{

    public Rabbit(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
