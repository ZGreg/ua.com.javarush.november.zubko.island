package service;

import entity.creature.animal.Animal;
import entity.location.Island;
import entity.location.Location;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AnimalRunner implements Runnable {

    private final Location location;
    private final Island island;

    private final Queue<AnimalTask> animalTasks = new ConcurrentLinkedQueue<>();


    public AnimalRunner(Island island,Location location) {
        this.island = island;
        this.location = location;
    }

    @Override
    public void run() {
        createTask(location, island);
    }

    private void createTask(Location location, Island island) {

        Map<String, Queue<Animal>> animals = location.getAnimals();

        if (animals != null) {
            location.getLock().lock();
            try{
                for (Map.Entry<String, Queue<Animal>> a : animals.entrySet()) {
                    for (Animal animal : a.getValue()) {
                        animalTasks.add(new AnimalTask(animal, island, location));
                    }
                }
            }finally {
                location.getLock().unlock();
            }
        }

        animalTasks.forEach(AnimalTask::simulateAnimalLife);
        animalTasks.clear();
    }

}
