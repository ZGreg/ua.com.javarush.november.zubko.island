package repository;

import entity.creature.Nature;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivorous.*;
import entity.creature.animal.predator.*;
import util.AnimalSpecies;

import java.util.HashMap;

public class AnimalFabric {

    private AnimalFabric() {
    }
    // add default error
    public static Nature createAnimal(AnimalSpecies species){
        Nature animal = null;
        switch (species){
            case FOX -> animal = new Fox(8,2,2);
            case BEAR -> animal = new Bear(500,80,2);
            case DEER -> animal = new Deer(300,50,4);
            case DUCK -> animal = new Duck(1,0.15,4);
            case GOAT -> animal = new Goat(60,10,3);
            case WOLF -> animal = new Wolf(50,8,3);
            case EAGLE -> animal = new Eagle(6,1,3);
            case HORSE -> animal = new Horse(400,60,4);
            case MOUSE -> animal = new Mouse(0.05,0.01,1);
            case SHEEP -> animal = new Sheep(70,15,3);
            case RABBIT -> animal = new Rabbit(2,0.45,2);
            case BUFFALO -> animal = new Buffalo(700,100,3);
            case WILDBOAR -> animal = new WildBoar(400,50,2);
            case CATERPILLAR -> animal = new Caterpillar(0.01,0,0);
            case BOA_CONSTRICTOR -> animal = new BoaConstrictor(15,3,1);
            default -> throw new RuntimeException();
        }
        return animal;
    }
}
