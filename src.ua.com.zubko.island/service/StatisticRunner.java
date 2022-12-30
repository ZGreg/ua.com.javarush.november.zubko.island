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

import static entity.creature.plant.Plant.*;

public class StatisticRunner implements Runnable {
    private final Island island;

    public StatisticRunner(Island island) {
        this.island = island;
    }

    private Map<String, Integer> quantityOfLivingBeings = new HashMap<>();


    @Override
    public void run() {
        List<Location> locations = island.getLocations();
        for (Location location : locations) {
            collectInfo(location);
        }
        displayInfo();
    }

    private void collectInfo(Location location) {

        Map<String, Queue<Animal>> animals = location.getAnimals();
        Queue<Plant> plants = location.getPlants();

        location.getLock().lock();
        try {
            for (AnimalSpecies value : AnimalSpecies.values()) {
                String typeAnimal = value.getProperName();
                int amtOfAnimals = animals.get(typeAnimal).size();
                String icon = value.getIcon();
                if (!quantityOfLivingBeings.containsKey(icon)) {
                    quantityOfLivingBeings.put(icon, amtOfAnimals);
                } else {
                    quantityOfLivingBeings.replace(icon, quantityOfLivingBeings.get(icon) + amtOfAnimals);
                }
            }

            if (!quantityOfLivingBeings.containsKey(PLANT_ICON)) {
                quantityOfLivingBeings.put(PLANT_ICON, plants.size());
            } else {
                quantityOfLivingBeings.replace(PLANT_ICON, quantityOfLivingBeings.get(PLANT_ICON) + plants.size());
            }
        } finally {
            location.getLock().unlock();
        }
    }

    private void displayInfo() {

        for (Map.Entry<String, Integer> entry : quantityOfLivingBeings.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.print( key + " " + value + " ");
        }
        System.out.println();
        quantityOfLivingBeings.clear();

    }
}
