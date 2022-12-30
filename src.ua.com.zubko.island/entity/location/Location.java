package entity.location;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import repository.AnimalFactory;
import util.AnimalSpecies;
import util.Randomizer;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Location {

    private final int id;
    private final int row;

    private final ReentrantLock lock = new ReentrantLock(true);

    private final Queue<Plant> plants = new LinkedList<>();
    private final Map<String,Queue<Animal>> animals = new HashMap<>(); //list

    public Location(int id, int row) {
        this.id = id;
        this.row = row;
        settleAnimals();
        settlePlants();
    }


    private void settlePlants(){
        int randomAmount = Randomizer.getRndNum(2, Plant.MAX_AMOUNT_OF_PLANT);
        for (int i = 0; i < randomAmount; i++) {
            plants.add(new Plant());
        }
    }

    private void settleAnimals(){
       Queue<Animal> container = new LinkedList<>();
        for(AnimalSpecies species : AnimalSpecies.values()){
            int maxAmtOfAnimal = Randomizer.getRndNum(2,species.getMaxAmountOfAnimal() + 1);
            while (maxAmtOfAnimal != 0 ) {
                container.add(AnimalFactory.createAnimal(species));
                maxAmtOfAnimal--;
            }
            animals.put(species.getProperName(), container);
            container = new LinkedList<>();
        }
    }

    public int getId() {
        return id;
    }

    public Map<String,Queue<Animal>> getAnimals() {
        return animals;
    }

    public int getRow() {
        return row;
    }

    public Queue<Plant> getPlants() {
        return plants;
    }

    public int getPlantsAmt(){
        return plants.size();
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
