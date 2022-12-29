package entity.creature.animal.herbivorous;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import entity.location.Location;
import service.JsonAnimalHuntChanceReader;
import util.Randomizer;

import java.util.*;

import static setings.WorldSettings.PLANT;

public class Mouse extends Herbivorous {

    public Mouse(double weight, double satiety, int speed) {
        super(weight, satiety, speed);
    }

    @Override
    public void eat(Location location) {
        safeEat(location);
    }

    private void safeEat(Location location) {
        location.getLock().lock();
        try {
            if (this.isHungry()) {

                Queue<Plant> plants = location.getPlants();
                Map<String, Queue<Animal>> animals = location.getAnimals();

                Map<String, Integer> huntChance = getRndPray();
                String preyName = (String) huntChance.keySet().toArray()[0];
                int attempt = Randomizer.getRndNum(2, 5);

                if (preyName.equals(PLANT)) {
                    while ((attempt >= 0) && (isHungry())) {
                        if (plants.size() != 0) {
                            Plant eatenPlant = plants.remove();
                            eatPlant(this, eatenPlant);
                        }
                        attempt--;
                    }
                } else if ((animals.containsKey(preyName)) && animals.get(preyName).size() != 0) {

                    Animal prey = animals.get(preyName).peek();

                    while ((attempt >= 0) && (isHungry())) {
                        if (isHuntSuccessful(huntChance.get(preyName), prey)) {
                            animals.get(preyName).remove();
                        }
                        attempt--;
                    }
                }
            }
        } finally {
            location.getLock().unlock();
        }
    }

    private boolean isHuntSuccessful(Integer chance, Animal prey) {
        if (getHuntResult(chance)) {
            eatAnimal(this, prey);
            return true;
        }
        return false;
    }

    private boolean getHuntResult(Integer chance) {
        return Randomizer.getRndNum(100) <= chance;
    }

    private void eatAnimal(Animal hunter, Animal prey) {
        double newSatiety = hunter.getCurrentSatiety() + (prey.getWeight());
        hunter.setCurrentSatiety(Math.min(newSatiety, hunter.getSatiety()));
    }

    private void eatPlant(Animal hunter, Plant plant) {
        double newSatiety = hunter.getCurrentSatiety() + (plant.getWeight());
        hunter.setCurrentSatiety(Math.min(newSatiety, hunter.getSatiety()));
    }

    private Map<String, Integer> getRndPray() {
        Map<String, Integer> huntChance = JsonAnimalHuntChanceReader.getHuntingChanceMap(retrieveName());

        List<String> prayName = new ArrayList<>(huntChance.keySet());
        int rndNum = Randomizer.getRndNum(prayName.size());

        Map<String, Integer> result = new HashMap<>();

        String rndPrayName = prayName.get(rndNum);
        result.put(rndPrayName, huntChance.get(rndPrayName));

        return result;
    }
}
