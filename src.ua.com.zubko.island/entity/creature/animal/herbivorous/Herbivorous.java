package entity.creature.animal.herbivorous;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import entity.location.Location;
import service.JsonAnimalHuntChanceReader;

import java.util.Map;

abstract public class Herbivorous extends Animal {


    public Herbivorous(double weight, double satiety, int speed) {
        super(weight, satiety, speed);
    }


    @Override
    public void eat(Location location) {     //throws exception if collection is empty

        while (isHungry() && location.getPlantsAmt() != 0) {
            Plant plant = location.getPlants().remove();
            setCurrentSatiety(updateWeight(plant));
        }
    }
    private double updateWeight(Plant plant) {
        double newSatiety = getCurrentSatiety() + plant.getWeight();
        return Math.min(newSatiety, getSatiety());
    }
    private Map<String, Integer> getPray() {
        return JsonAnimalHuntChanceReader.getHuntingChanceMap(this.getClass().getSimpleName());
    }
}
