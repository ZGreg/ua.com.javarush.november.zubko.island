package entity.location;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import repository.AnimalFabric;
import util.AnimalSpecies;
import util.Randomizer;

import java.util.*;

public class Location {

    private AnimalFabric animalFabric;
    private  int id;

    private int row;
   // private int x;                //will be i
   // private int y;                // will be j


    private Queue<Plant> plants = new LinkedList<>();     //set
    private Map<String,Deque<Animal>> animals = new HashMap<>();         //set

  //  private final Map<String,List<Nature>> livingCreature = new HashMap<>();    //stack or queue probably better then list

    public Location(int id, int row) {
        this.id = id;
        this.row = row;
        settleAnimals();
        settlePlants();
    }


    private void settlePlants(){
        int randomAmount = Randomizer.getRndNum(1, Plant.MAX_AMOUNT_OF_PLANT + 1);
        for (int i = 0; i < 6; i++) {
            plants.add(new Plant());
        }
    }

    private void settleAnimals(){
       Deque<Animal> container = new LinkedList<>();
        for(AnimalSpecies species : AnimalSpecies.values()){
            int maxAmtOfAnimal = Randomizer.getRndNum(1,species.getMaxAmountOfAnimal() + 1);
            while (maxAmtOfAnimal != 0 ) {
                container.add(AnimalFabric.createAnimal(species));
                maxAmtOfAnimal--;
            }
            animals.put(species.getProperName(), container);
            container = new LinkedList<>();
        }
    }

    public int getId() {
        return id;
    }  //delete

    public Map<String,Deque<Animal>> getAnimals() {
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
}
