package entity.creature.animal;

import entity.creature.Mortal;
import entity.creature.Nature;
import entity.creature.animal.predator.Predator;
import entity.creature.plant.Plant;
import entity.location.IslandArrayVersion;
import entity.location.Location;
import repository.AnimalFabric;
import service.JsonAnimalHuntChanceReader;
import util.AnimalSpecies;
import util.DirectionCounter;
import util.RandomMethodsHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Animal implements Mortal {

    private double weight;
    private double satiety;
    private double currentSatiety;
    private int speed;

    private Map<String, Integer> eatenThings;

    public Animal(double weight, double satiety, int speed) {
        this.weight = weight;
        this.satiety = satiety;
        this.speed = speed;
        this.eatenThings = new HashMap<>();
    }

    public boolean isHungry() {
        return !(currentSatiety == satiety);
    }


    public void move(Location location) {           //------------------------ move

     //   if(wantsMove()){
            int id = location.getId();
            IslandArrayVersion island = new IslandArrayVersion();

            int speed = getSpeed();

        List<Integer> allDirections = DirectionCounter.getAllDirections(id, speed);
            System.out.println(allDirections);
            if( (0) <= RandomMethodsHolder.getRndNum(allDirections.size())){

            }


      //  }





    }

    private boolean wantsMove(){
        int rndNum = RandomMethodsHolder.getRndNum(50 + 1);
        return rndNum > 50;
    }








    public void reproduce(Location location) { //----------------------reproduce    start
        Map<String, List<Nature>> livingCreature = location.getLivingCreature();
        String key = getClass().getSimpleName();
        if (livingCreature.containsKey(key) && !(livingCreature.get(key).isEmpty())) {
            if (isMatingSuccessful()) {
                Nature newBorn =  AnimalFabric.createAnimal(AnimalSpecies.valueOf(getClass().getSimpleName().toUpperCase()));
                livingCreature.get(key).add(newBorn);
                System.out.println("new animal " + key);
            }
        }

    }
    private boolean isMatingSuccessful() {    // live one method for 50 % chance probability
        int rndNum = RandomMethodsHolder.getRndNum(50);
        return rndNum <= 50; //------------------------------------------------reproduce  end
    }



    public void eat(Location loc) {  //---------------------------------------------------------eat  start
        Map<String, List<Nature>> livingCreature = loc.getLivingCreature();
        if (this.isHungry()) {

            Map<String, Integer> huntChance = getRndPray();
            String preyName = (String) huntChance.keySet().toArray()[0];
            Nature prey =  livingCreature.get(preyName).get(0);
            do {
                if ((livingCreature.get(preyName).size() != 0) && isHuntSuccessful(huntChance.get(preyName), prey)) {
                    livingCreature.get(preyName).remove(0);
                    addHuntInfo(preyName);

                }
            } while (isHungry() && !(this instanceof Predator));
        }
    }
    private boolean isHuntSuccessful(Integer chance, Nature prey) {
        double currentSatiety = this.getCurrentSatiety();
        this.setCurrentSatiety(currentSatiety - (currentSatiety / 100 * 10));
        if (getHuntResult(chance)) {
            this.setCurrentSatiety(updateWeight(this, prey));
            return true;
        }
        return false;
    }

    private boolean getHuntResult(Integer chance) {
        if(chance == 100){
            return true;
        }
        return RandomMethodsHolder.getRndNum(100 + 1) <= chance;
    }
    private double updateWeight(Animal hunter, Nature prey) {
        double s = hunter.getCurrentSatiety() + (prey.getWeight());
        return Math.min(s, hunter.getSatiety());
    }

    private Map<String, Integer> getRndPray() {
        Map<String, Integer> huntChance = JsonAnimalHuntChanceReader.getHuntingChanceMap(this.getClass().getSimpleName());

        if (huntChance.size() == 1 && huntChance.containsKey("Plant")) {  //add exception --not aprop smt
            return huntChance;
        }

        List<String> prayName = new ArrayList<>(huntChance.keySet());
        int rndNum = RandomMethodsHolder.getRndNum(prayName.size());

        Map<String, Integer> result = new HashMap<>();

        String rndPrayName = prayName.get(rndNum);
        result.put(rndPrayName, huntChance.get(rndPrayName));

        return result;
    }

    private void addHuntInfo(String prey) {                     // Class info ?

        if (getEatenThings().containsKey(prey)) {
            int value = getEatenThings().get(prey);
            getEatenThings().replace(prey, value + 1);
        }
        getEatenThings().put(prey, 1);
    }                                                   //-----------------------------------eat  end


    public double getCurrentSatiety() {
        return currentSatiety;
    }

    public void setCurrentSatiety(double currentSatiety) {
        this.currentSatiety = currentSatiety;
    }

    public double getWeight() {
        return weight;
    }

    public double getSatiety() {
        return satiety;
    }

    public Map<String, Integer> getEatenThings() {
        return eatenThings;
    }

    public int getSpeed() {
        return speed;
    }
}
