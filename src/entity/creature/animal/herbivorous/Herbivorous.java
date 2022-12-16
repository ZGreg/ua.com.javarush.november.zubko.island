package entity.creature.animal.herbivorous;

import entity.creature.Nature;
import entity.creature.animal.Animal;
import entity.location.Location;
import service.JsonAnimalHuntChanceReader;

import java.util.Map;

abstract public class Herbivorous extends Animal implements Nature {


    public Herbivorous(double weight, double satiety, int speed) {
        super(weight, satiety, speed);
    }

    @Override
    public boolean isDead(Double satiety) {
        return super.isDead(satiety);
    }

}
