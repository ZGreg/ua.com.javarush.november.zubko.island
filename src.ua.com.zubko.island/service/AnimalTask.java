package service;

import entity.creature.animal.Animal;
import entity.location.Island;
import entity.location.Location;

public class AnimalTask {

    private final Animal animal;

    private final Island island;

    private final Location location;

    public AnimalTask(Animal animal, Island island, Location location) {
        this.animal = animal;
        this.island = island;
        this.location = location;
    }

    public void simulateAnimalLife() {
        animal.move(location, island);
        animal.eat(location);
        animal.reproduce(location);
        animal.killAnimal(location);
    }
}
