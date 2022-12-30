package entity.creature.animal.herbivorous;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import entity.location.Location;

abstract public class Herbivorous extends Animal {


    public Herbivorous(double weight, double satiety, int speed) {
        super(weight, satiety, speed);
    }

    @Override
    public void eat(Location location) {
        safeEat(location);
    }

    private void safeEat(Location location) {
        location.getLock().lock();
        try {
            while (isHungry() && location.getPlantsAmt() != 0) {
                Plant plant = location.getPlants().remove();
                setCurrentSatiety(updateWeight(plant));
            }
        }finally {
            location.getLock().unlock();
        }
    }

    private double updateWeight(Plant plant) {
        double newSatiety = getCurrentSatiety() + plant.getWeight();
        return Math.min(newSatiety, getSatiety());
    }
}
