package entity.creature.animal.predator;

import entity.creature.animal.Animal;
import entity.location.Location;
import service.JsonAnimalHuntChanceReader;
import util.Randomizer;

import java.util.*;


abstract public class Predator extends Animal {

    public Predator(double weight, double satiety, int speed) {
        super(weight, satiety, speed);
    }
   @Override
    public void eat(Location location){
        safeEat(location);
    }
    private void safeEat(Location location) {
        location.getLock().lock();
        try{
            if (this.isHungry()) {

                Map<String, Queue<Animal>> animals = location.getAnimals();

                Map<String, Integer> huntChance = getRndPray();
                String preyName = (String) huntChance.keySet().toArray()[0];

                if ((animals.get(preyName).size() != 0)) {
                    Animal prey = animals.get(preyName).peek();

                    if (isHuntSuccessful(huntChance.get(preyName), prey)) {
                        animals.get(preyName).remove();
                    }
                }
            }
        }finally {
            location.getLock().unlock();
        }

    }

    private boolean isHuntSuccessful(Integer chance, Animal prey) {
        double currentSatiety = this.getCurrentSatiety();
        this.setCurrentSatiety(currentSatiety - (currentSatiety / 100 * 10));
        if (getHuntResult(chance)) {
            this.setCurrentSatiety(updateWeight(this, prey));
            return true;
        }
        return false;
    }

    private boolean getHuntResult(Integer chance) {
        return Randomizer.getRndNum(100) <= chance;
    }

    private double updateWeight(Animal hunter, Animal prey) {
        double newSatiety = hunter.getCurrentSatiety() + (prey.getWeight());
        return Math.min(newSatiety, hunter.getSatiety());
    }

    private Map<String, Integer> getRndPray() {
        Map<String, Integer> huntChance = JsonAnimalHuntChanceReader.getHuntingChanceMap(getName());

        List<String> prayName = new ArrayList<>(huntChance.keySet());
        int rndNum = Randomizer.getRndNum(prayName.size());

        Map<String, Integer> result = new HashMap<>();

        String rndPrayName = prayName.get(rndNum);
        result.put(rndPrayName, huntChance.get(rndPrayName));

        return result;
    }

}


