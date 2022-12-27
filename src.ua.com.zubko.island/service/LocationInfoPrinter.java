package service;

import entity.creature.animal.Animal;
import entity.location.Location;
import util.AnimalSpecies;

import java.util.Deque;
import java.util.Map;
import java.util.Queue;

public class LocationInfoPrinter {


    public void printLocationInfo(Location location) {
        printCurrentAmtOfPlants(location);
        System.out.println("<=>".repeat(25));
        printCurrentAmtOfAnimals(location);
    }

    private void printCurrentAmtOfPlants(Location location) {
        System.out.printf("Location %d contains %d plants.\n", location.getId(), location.getPlantsAmt());
    }

    private void printCurrentAmtOfAnimals(Location location) {

        Map<String, Queue<Animal>> animals = location.getAnimals();

        for (AnimalSpecies value : AnimalSpecies.values()) {
            String typeAnimal = value.getProperName();
            if (animals.containsKey(typeAnimal)) {
                int amtOfAnimal = animals.get(typeAnimal).size();
                System.out.printf("On location ID - %d lives : ", location.getId());
                System.out.printf("Animal species %s, currently population is %d\n", typeAnimal, amtOfAnimal);
            }
        }
    }
}
