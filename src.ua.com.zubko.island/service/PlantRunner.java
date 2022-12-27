package service;

import entity.creature.plant.Plant;
import entity.location.Island;
import entity.location.Location;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class PlantRunner implements Runnable{

    private final Location location;

    private final Queue<PlantTask> plantTasks = new ConcurrentLinkedQueue<>();

    public PlantRunner(Location location) {
        this.location = location;
    }


    @Override
    public void run() {
        createTask(location);
    }


    private void createTask(Location location) {
        Queue<Plant> plants = location.getPlants();

        if (plants != null) {
            location.getLock().lock();
            try{
                for (Plant plant : plants) {
                    plantTasks.add(new PlantTask(plant,location));
                }
            }finally {
                location.getLock().unlock();
            }
        }

        plantTasks.forEach(PlantTask::simulatePlantLife);
        plantTasks.clear();
    }
}
