package entity.creature.animal.herbivorous;

import java.util.Map;

public class Mouse extends Herbivorous{

    public Mouse(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }
}
