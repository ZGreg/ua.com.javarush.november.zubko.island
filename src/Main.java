import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import entity.creature.animal.predator.Wolf;
import entity.location.Location;
import service.JsonAnimalHuntChanceReader;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        String simpleName = Wolf.class.getSimpleName();
        Map<String, Integer> wolf = JsonAnimalHuntChanceReader.getHuntingChanceMap(simpleName);
        System.out.println(wolf);


        List<String> strings = new ArrayList<>(wolf.keySet());
        System.out.println(wolf.get(strings.get(1)));

        Location location = new Location(1, 1, 1);
        List<Animal> animals = location.getAnimals();
        Map<String, Integer> huntChance = JsonAnimalHuntChanceReader.getHuntingChanceMap(Wolf.class.getSimpleName());



        Wolf wolf1 = new Wolf(1, 8, 1,new HashMap<>());
        wolf1.setCurrentSatiety(7);
        System.out.println(wolf1.getCurrentSatiety() + "<-- satiety before eat");
        wolf1.eat(new Location(1,1,1));
        System.out.println(wolf1.getCurrentSatiety() + "<-- satiety after eat");
        System.out.println(wolf1.getEatenThings());


    }

}
