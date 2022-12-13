package entity.creature.animal.herbivorous;

import java.util.Map;

public class Duck extends Herbivorous{


    public Duck(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
