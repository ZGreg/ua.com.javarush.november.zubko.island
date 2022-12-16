package entity.creature.animal.predator;

import entity.creature.Nature;
import entity.creature.animal.Animal;
import entity.location.Location;
import repository.AnimalFabric;
import service.JsonAnimalHuntChanceReader;
import util.AnimalSpecies;
import util.RandomMethodsHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



abstract public class Predator extends Animal implements Nature {

    public Predator(double weight, double satiety, int speed) {
        super(weight, satiety, speed);
    }


    @Override
    public boolean isDead(Double satiety) {
        return super.isDead(satiety);
    }


}


