package entity.creature.animal.herbivorous;

import entity.creature.animal.Animal;
import entity.location.Location;

import java.util.Map;

abstract public class Herbivorous extends Animal {


    public Herbivorous(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }

    @Override
    public void eat(Location loc) {         // add Animal as parameter?
        //  if(animal.isHungry)
        //  if(!location.getPlants.isEmpty()){
        //add hunger remove plant from list or as dead
        // }
    }
}
