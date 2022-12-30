package entity.creature.animal;

import entity.location.Island;
import entity.location.Location;
import lombok.Getter;
import lombok.Setter;
import repository.AnimalFactory;
import util.AnimalSpecies;
import util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static seting.WorldSettings.ID_MAX_VALUE;
import static seting.WorldSettings.ISLAND_LENGTH;

public abstract class Animal implements Mortal {
    @Getter
    private final double weight;
    @Getter
    private final double satiety;
    @Getter
    @Setter
    private double currentSatiety;
    @Getter
    private final int speed;

    public Animal(double weight, double satiety, int speed) {
        this.weight = weight;
        this.satiety = satiety;
        this.speed = speed;
    }

    public abstract void eat(Location location);

    public String getName() {
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
                location.getAnimals().get(getName()).remove(this);
            }
        } finally {
            location.getLock().unlock();
        }

    }

    public void reproduce(Location location) {
        safeReproduce(location);
    }

    private void safeReproduce(Location location) {
        location.getLock().lock();
        try {
            Map<String, Queue<Animal>> livingCreature = location.getAnimals();
            String key = this.getName();

            if ((livingCreature.containsKey(key)) && (livingCreature.get(key).size() > 1) &&
                    (livingCreature.get(key).size() < AnimalSpecies.valueOf(key.toUpperCase()).getMaxAmountOfAnimal())) {

                boolean isMatingSuccessful = Randomizer.flipCoin();

                if (isMatingSuccessful) {
                    Animal newBorn = AnimalFactory.createAnimal(AnimalSpecies.valueOf(getName().toUpperCase()));
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

            int animalSpeed = getSpeed();
            int newLocationId = choseDestination(location, island);

            Location destination = island.getLocationById(newLocationId);

            if (destination.getAnimals().get(getName()).size() <
                    AnimalSpecies.valueOf(getName().toUpperCase()).getMaxAmountOfAnimal()) {

                location.getAnimals().get(getName()).remove(this);
                setCurrentSatiety(getCurrentSatiety() - (getCurrentSatiety() / 100 * (animalSpeed * 10)));
                destination.getAnimals().get(getName()).add(this);
            }
        } finally {
            location.getLock().unlock();
        }

    }

    private int choseDestination(Location location, Island island) {

        boolean coordinateDirection = Randomizer.flipCoin();
        int id = location.getId();
        int animalSpeed = getSpeed();


        List<Integer> locationsToGo = new ArrayList<>();

        while (locationsToGo.isEmpty()) {
            if (coordinateDirection) {
                // X coordinate
                int leftDirection = id - animalSpeed;
                int rightDirection = id + animalSpeed;

                //
                if ((leftDirection > 0) && (location.getRow() == island.getLocationById(leftDirection).getRow())) {
                    locationsToGo.add(leftDirection);
                }
                if ((rightDirection < ID_MAX_VALUE) && (location.getRow() == island.getLocationById(rightDirection).getRow())) {
                    locationsToGo.add(rightDirection);
                }
            } else {
                // Y coordinate
                int upDirection = id - (animalSpeed * ISLAND_LENGTH);
                int downDirection = id + (animalSpeed * ISLAND_LENGTH);


                if (upDirection > 0) {
                    locationsToGo.add(upDirection);
                }

                if (downDirection < ID_MAX_VALUE) {
                    locationsToGo.add(downDirection);
                }
            }
            animalSpeed--;
        }

        if (locationsToGo.size() == 1) {
            return locationsToGo.get(0);
        } else {
            return locationsToGo.get(Randomizer.getRndNum(0, 2));
        }
    }

}
