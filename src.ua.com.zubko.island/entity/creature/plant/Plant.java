package entity.creature.plant;


import entity.creature.animal.herbivorous.Rabbit;
import entity.location.Location;
import util.Randomizer;

public class Plant {

    public static final int MAX_AMOUNT_OF_PLANT = 200;
    private final int weight;

    public Plant() {
        this.weight = 1;
    }

    public void growPlant(Location location){
        safeGrowPlant(location);
    }

    private void safeGrowPlant(Location location) {
        location.getLock().lock();
        try{int currentAmtPlants = location.getPlantsAmt();

            if (currentAmtPlants >= MAX_AMOUNT_OF_PLANT) {
                return;
            }

            int newPlants = Randomizer.getRndNum(1,5);

            for (int i = 0; i < newPlants; i++) {
                location.getPlants().add(new Plant());
            }
        }finally {
            location.getLock().unlock();
        }
    }

    public double getWeight() {
        return weight;
    }


}
