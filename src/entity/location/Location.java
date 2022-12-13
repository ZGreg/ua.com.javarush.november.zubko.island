package entity.location;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import repository.AnimalFabric;
import util.AnimalSpecies;
import util.RandomMethodsHolder;

import java.util.*;

public class Location {
    private final long id;
    private int x;                //will be i
    private int y;                // will be j

    private final List<Plant> plants = new LinkedList<>();     //getter plantAmt -> plant.size();   //LinkedList probably better
    private final List<Animal> animals = new LinkedList<>();        // Map<String<List<Animal>>

    public Location(long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        settleAnimals();
        settlePlants();
    }


    private void settlePlants(){
        int randomAmount = RandomMethodsHolder.getRndNum(1, Plant.MAX_AMOUNT_OF_PLANT + 1);
        for (int i = 0; i < randomAmount; i++) {
            plants.add(new Plant());
        }
    }

    private void settleAnimals(){  //take values from json through Setting class
        for(AnimalSpecies species : AnimalSpecies.values()){
            int maxAmtOfAnimal = RandomMethodsHolder.getRndNum(1,species.getMaxAmountOfAnimal() + 1);
            while (maxAmtOfAnimal != 0 ) {
                animals.add(AnimalFabric.createAnimal(species));
                maxAmtOfAnimal--;
            }
        }
    }

    public long getId() {
        return id;
    }
    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }
    public int getPlantsAmt() {
        return plants.size();
    }
}
