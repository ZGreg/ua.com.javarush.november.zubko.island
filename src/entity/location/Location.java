package entity.location;

import entity.creature.Nature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import repository.AnimalFabric;
import util.AnimalSpecies;
import util.RandomMethodsHolder;

import java.util.*;

public class Location {
    private final int id;
    private int x;                //will be i
    private int y;                // will be j
    private final List<Animal> animals = new LinkedList<>();        // Map<String<List<Animal>>
    private final Map<String,List<Nature>> livingCreature = new HashMap<>();    //stack or queue probably better then list

    public Location(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        settleAnimals();
        settlePlants();
    }


    private void settlePlants(){
        List<Nature> container = new LinkedList<>();
        int randomAmount = RandomMethodsHolder.getRndNum(1, Plant.MAX_AMOUNT_OF_PLANT + 1);
        for (int i = 0; i < randomAmount; i++) {
            container.add(new Plant());
        }
        livingCreature.put("Plant", container);
    }

    private void settleAnimals(){  //take values from json through Setting class
        List<Nature> container = new LinkedList<>();
        for(AnimalSpecies species : AnimalSpecies.values()){
            int maxAmtOfAnimal = RandomMethodsHolder.getRndNum(1,species.getMaxAmountOfAnimal() + 1);
            while (maxAmtOfAnimal != 0 ) {
                container.add(AnimalFabric.createAnimal(species));
                maxAmtOfAnimal--;
            }
            livingCreature.put(species.getProperName(),container);
            container = new LinkedList<>();
        }
    }

    public int getId() {
        return id;
    }


    public List<Animal> getAnimals() {
        return animals;
    }

    public int getPlantsAmt() {
        return livingCreature.get("Plant").size();
    }

    public Map<String, List<Nature>> getLivingCreature() {
        return livingCreature;
    }
}
