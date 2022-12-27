package service;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import entity.location.Island;
import entity.location.Location;
import util.AnimalSpecies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static setings.WorldSettings.PLANT;

public class StatisticRunner implements Runnable {
    private final Island island;

    public StatisticRunner(Island island) {
        this.island = island;
    }

    private Map<String, Integer> quantityOfLivingBeings = new HashMap<>();


    @Override
    public void run() {
        List<Location> locations = island.getIsland();
        for (Location location : locations) {
            collectInfo(location);
        }
        displayInfo();
    }

    private void collectInfo(Location location) {   //rethink method

        Map<String, Queue<Animal>> animals = location.getAnimals();
        Queue<Plant> plants = location.getPlants();

        location.getLock().lock();

        try {
            for (AnimalSpecies value : AnimalSpecies.values()) {
                String typeAnimal = value.getProperName();
                int amtOfAnimals = animals.get(typeAnimal).size();
                if (!quantityOfLivingBeings.containsKey(typeAnimal)) {
                    quantityOfLivingBeings.put(typeAnimal, amtOfAnimals);
                } else {
                    quantityOfLivingBeings.replace(typeAnimal, quantityOfLivingBeings.get(typeAnimal) + amtOfAnimals);
                }
            }

            if (!quantityOfLivingBeings.containsKey(PLANT)) {
                quantityOfLivingBeings.put(PLANT, plants.size());
            } else {
                quantityOfLivingBeings.replace(PLANT, quantityOfLivingBeings.get(PLANT) + plants.size());
            }
        } finally {
            location.getLock().unlock();
        }
    }

    private void displayInfo() { // update days counter

        for (Map.Entry<String, Integer> entry : quantityOfLivingBeings.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.print(key + " " + value + ", ");
        }
        System.out.println();
        quantityOfLivingBeings.clear();

    }
}
