package entity.creature.animal;

import entity.location.Island;
import entity.location.Location;
import repository.AnimalFabric;
import util.AnimalSpecies;
import util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static setings.WorldSettings.ID_MAX_VALUE;
import static setings.WorldSettings.ISLAND_LENGTH;

public abstract class Animal implements Mortal {

    private final double weight;
    private final double satiety;
    private double currentSatiety;
    private final int speed;

    public Animal(double weight, double satiety, int speed) {
        this.weight = weight;
        this.satiety = satiety;
        this.speed = speed;
    }

    public abstract void eat(Location location);

    public String retrieveName() {
        return this.getClass().getSimpleName();
    }

    public boolean isHungry() {
        return !(currentSatiety == satiety);
    }

    public void killAnimal(Location location) {
        safeKillAnimal(location);
    }

    private void safeKillAnimal(Location location) {
        location.getLock().lock();
        try {
            if (isDead(getCurrentSatiety())) {
                location.getAnimals().get(retrieveName()).remove(this);
            }
        } finally {
            location.getLock().unlock();
        }

    }

    public void reproduce(Location location) {
        safeReproduce(location);
    }

    private void safeReproduce(Location location) { //----------------------reproduce start
        location.getLock().lock();
        try {
            Map<String, Queue<Animal>> livingCreature = location.getAnimals();
            String key = this.retrieveName();

            if ((livingCreature.containsKey(key)) && (livingCreature.get(key).size() > 1) &&
                    (livingCreature.get(key).size() < AnimalSpecies.valueOf(key.toUpperCase()).getMaxAmountOfAnimal())) {

                boolean isMatingSuccessful = Randomizer.flipCoin();

                if (isMatingSuccessful) {
                    Animal newBorn = AnimalFabric.createAnimal(AnimalSpecies.valueOf(retrieveName().toUpperCase()));
                    livingCreature.get(key).add(newBorn);
                }
            }
        } finally {
            location.getLock().unlock();
        }

    }


    public void move(Location location, Island island) {
        safeMove(location, island);
    }

    private void safeMove(Location location, Island island) {
        location.getLock().lock();
        try {

            int speed = getSpeed();
            int newLocationId = choseDestination(location,island);

            Location destination = island.getLocationById(newLocationId);

            if (destination.getAnimals().get(retrieveName()).size() <
                    AnimalSpecies.valueOf(retrieveName().toUpperCase()).getMaxAmountOfAnimal()) {

                location.getAnimals().get(retrieveName()).remove(this);
                setCurrentSatiety(getCurrentSatiety() - (getCurrentSatiety() / 100 * (speed * 10)));
                destination.getAnimals().get(retrieveName()).add(this);
            }

        } finally {
            location.getLock().unlock();
        }

    }

    private int choseDestination(Location location, Island island) {

        boolean coordinateDirection = Randomizer.flipCoin();
        int id = location.getId();
        int speed = getSpeed();

        List<Integer> locationsToGo = new ArrayList<>();

            if (coordinateDirection) {
                // X coordinate
                int left = id - speed;
                int right = id + speed;

                //
                if ((left > 0) && (location.getRow() == island.getLocationById(left).getRow())) {
                    locationsToGo.add(left);
                }
                if ((right < ID_MAX_VALUE)&& (location.getRow() == island.getLocationById(right).getRow())) {
                    locationsToGo.add(right);
                }
            } else {
                // Y coordinate
                int up = id - (speed * ISLAND_LENGTH);
                int down = id + (speed * ISLAND_LENGTH);


                if (up > 0) {
                    locationsToGo.add(up);
                }

                if (down < ID_MAX_VALUE) {
                    locationsToGo.add(down);
                }
            }

        if (locationsToGo.size() == 1) {   //if size 0
            return locationsToGo.get(0);
        } else {
            return locationsToGo.get(Randomizer.getRndNum(0, 2));
        }
    }


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

    public int getSpeed() {
        return speed;
    }


}
