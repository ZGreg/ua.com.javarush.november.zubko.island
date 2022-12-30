package entity.location;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import lombok.Getter;
import repository.AnimalFactory;
import util.AnimalSpecies;
import util.Randomizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Location {
    @Getter
    private final int id;
    @Getter
    private final int row;
    @Getter
    private final ReentrantLock lock = new ReentrantLock(true);
    @Getter
    private final Queue<Plant> plants = new LinkedList<>();
    @Getter
    private final Map<String, Queue<Animal>> animals = new HashMap<>();

    public Location(int id, int row) {
        this.id = id;
        this.row = row;
        settleAnimals();
        settlePlants();
    }


    private void settlePlants() {
        int randomAmount = Randomizer.getRndNum(2, Plant.MAX_AMOUNT_OF_PLANT);
        for (int i = 0; i < randomAmount; i++) {
            plants.add(new Plant());
        }
    }

    private void settleAnimals() {
        Queue<Animal> container = new LinkedList<>();
        for (AnimalSpecies species : AnimalSpecies.values()) {
            int maxAmtOfAnimal = Randomizer.getRndNum(2, species.getMaxAmountOfAnimal() + 1);
            while (maxAmtOfAnimal != 0) {
                container.add(AnimalFactory.createAnimal(species));
                maxAmtOfAnimal--;
            }
            animals.put(species.getProperName(), container);
            container = new LinkedList<>();
        }
    }
    public int getPlantsAmt() {
        return plants.size();
    }
}
