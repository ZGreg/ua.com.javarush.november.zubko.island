package entity.creature.animal.predator;

import entity.creature.animal.Animal;
import entity.location.Location;
import service.JsonAnimalHuntChanceReader;
import util.RandomMethodsHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO: Exceptions
abstract public class Predator extends Animal {

    public Predator(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        super(weight, satiety, speed, eatenThings);
    }

    @Override
    public void eat(Location loc) {
        List<Animal> livingBeings = loc.getAnimals();
        Map<String, List<Animal>> groupedCreatures = getGroupedLocationCreatures(livingBeings);
        if (this.isHungry()) {

            Map<String, Integer> huntChance = getRndPray();
            String preyName = (String) huntChance.keySet().toArray()[0];
            Animal prey = groupedCreatures.get(preyName).get(0);

            if ((groupedCreatures.get(preyName).size() != 0) && isHuntSuccessful(huntChance.get(preyName), prey)) {
                Animal huntedAnimal = groupedCreatures.get(preyName).remove(0);
                livingBeings.remove(huntedAnimal);
                addInfoAboutHunt(preyName);
            }
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

    private boolean getHuntResult(Integer chance) {             //needed?
        return RandomMethodsHolder.getRndNum(100) <= chance;
    }


    private double updateWeight(Animal hunter, Animal prey) {
        double s = hunter.getCurrentSatiety() + (prey.getWeight() / 2);
        return Math.min(s, hunter.getSatiety());
    }


    private Map<String, Integer> getRndPray() {
        Map<String, Integer> huntChance = JsonAnimalHuntChanceReader.getHuntingChanceMap(this.getClass().getSimpleName());

        List<String> prayName = new ArrayList<>(huntChance.keySet());
        int rndNum = RandomMethodsHolder.getRndNum(prayName.size());

        Map<String, Integer> result = new HashMap<>();

        String rndPrayName = prayName.get(rndNum);
        result.put(rndPrayName, huntChance.get(rndPrayName));

        return result;
    }


    private Map<String, List<Animal>> getGroupedLocationCreatures(List<? extends Animal> animals) {

        return animals.stream()
                .collect(Collectors.groupingBy(Animal -> Animal.getClass().getSimpleName()));
    }

    private void addInfoAboutHunt(String prey) {

        if (getEatenThings().containsKey(prey)) {
            int value = getEatenThings().get(prey);
            getEatenThings().replace(prey, value + 1);
        }
        getEatenThings().put(prey, 1);
    }
}


