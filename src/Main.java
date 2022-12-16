import entity.creature.Nature;
import entity.creature.animal.herbivorous.Goat;
import entity.creature.animal.predator.Wolf;
import entity.location.Island;
import entity.location.Location;
import service.JsonAnimalHuntChanceReader;
import util.AnimalSpecies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
//        String simpleName = Wolf.class.getSimpleName();
//        Map<String, Integer> wolf = JsonAnimalHuntChanceReader.getHuntingChanceMap(simpleName);
//        System.out.println(wolf);
//
//
//        List<String> strings = new ArrayList<>(wolf.keySet());
//        System.out.println(wolf.get(strings.get(1)));
//
        Location location = new Location(1, 1, 1);
        new Goat(1,1,1).move(location);
//
//
//
//        Wolf wolf1 = new Wolf(1, 8, 1);
//        wolf1.setCurrentSatiety(7);
//        System.out.println(wolf1.getCurrentSatiety() + "<-- satiety before eat");
//        wolf1.eat(new Location(1,1,1));
//        System.out.println(wolf1.getCurrentSatiety() + "<-- satiety after eat");
//        System.out.println(wolf1.getEatenThings());
//        System.out.println(wolf1.isDead(wolf1.getCurrentSatiety()));
//

//        Map<String, List<Nature>> livingCreature = new Location(1, 1, 1).getLivingCreature();
//        System.out.println(livingCreature);
//        System.out.println(livingCreature.size());
//        System.out.println(livingCreature.get("Mouse").size() + "<--MOUSE");
//        System.out.println(livingCreature.get("Wolf").size() + "<--WOLF");
//        System.out.println(livingCreature.get("Plant").size() + "<--Plant");


    }

}
