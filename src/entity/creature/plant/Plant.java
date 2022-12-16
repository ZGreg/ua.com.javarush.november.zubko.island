package entity.creature.plant;


import entity.creature.Nature;
import entity.location.Location;

import java.util.LinkedList;

public class Plant implements Nature {

    public static final int MAX_AMOUNT_OF_PLANT = 200;
    private final int weight;

    public Plant() {
        this.weight = 1;
    }

    @Override
    public void reproduce(Location location) {
//        LinkedList<Nature> container = new LinkedList<>();
//    //    for (int i = 0; i <  rndNam; i++) {
//            container.add(new Plant());
//        }
//        location.getLivingCreature().get("Plant").addAll(container);
    }

    public double getWeight() {
        return weight;
    }


}
