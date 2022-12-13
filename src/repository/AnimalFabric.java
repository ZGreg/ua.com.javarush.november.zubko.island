package repository;

import entity.creature.animal.Animal;
import entity.creature.animal.herbivorous.*;
import entity.creature.animal.predator.*;
import util.AnimalSpecies;

import java.util.HashMap;

public class AnimalFabric {

    private AnimalFabric() {
    }
    // add default error
    public static Animal createAnimal(AnimalSpecies species){
        Animal animal = null;
        switch (species){
            case FOX -> animal = new Fox(8,2,2,new HashMap<>());
            case BEAR -> animal = new Bear(500,80,2,new HashMap<>());
            case DEER -> animal = new Deer(300,50,4,new HashMap<>());
            case DUCK -> animal = new Duck(1,0.15,4,new HashMap<>());
            case GOAT -> animal = new Goat(60,10,3,new HashMap<>());
            case WOLF -> animal = new Wolf(50,8,3,new HashMap<>());
            case EAGLE -> animal = new Eagle(6,1,3,new HashMap<>());
            case HORSE -> animal = new Horse(400,60,4,new HashMap<>());
            case MOUSE -> animal = new Mouse(0.05,0.01,1,new HashMap<>());
            case SHEEP -> animal = new Sheep(70,15,3,new HashMap<>());
            case RABBIT -> animal = new Rabbit(2,0.45,2,new HashMap<>());
            case BUFFALO -> animal = new Buffalo(700,100,3,new HashMap<>());
            case WILDBOAR -> animal = new WildBoar(400,50,2,new HashMap<>());
            case CATERPILLAR -> animal = new Caterpillar(0.01,0,0,new HashMap<>());
            case BOA_CONSTRICTOR -> animal = new BoaConstrictor(15,3,1,new HashMap<>());
        }
        return animal;
    }
}
