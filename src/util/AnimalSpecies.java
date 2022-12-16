package util;

import entity.creature.animal.herbivorous.*;
import entity.creature.animal.predator.*;

public enum AnimalSpecies {
    BEAR(5, Bear.class.getSimpleName()),
    BOA_CONSTRICTOR(30, BoaConstrictor.class.getSimpleName()),
    EAGLE(20, Eagle.class.getSimpleName()),
    FOX(30, Fox.class.getSimpleName()),
    WOLF(30, Wolf.class.getSimpleName()),
    BUFFALO(10, Buffalo.class.getSimpleName()),
    CATERPILLAR(1000, Caterpillar.class.getSimpleName()),
    DEER(20, Deer.class.getSimpleName()),
    DUCK(200, Duck.class.getSimpleName()),
    GOAT(140, Goat.class.getSimpleName()),
    HORSE(20,Horse.class.getSimpleName()),
    MOUSE(500,Mouse.class.getSimpleName()),
    RABBIT(150,Rabbit.class.getSimpleName()),
    SHEEP(140,Sheep.class.getSimpleName()),
    WILDBOAR(50,WildBoar.class.getSimpleName());

    private int maxAmtOfAnimal;
    private String name;
    AnimalSpecies(int maxAmtOfAnimal, String name){
        this.maxAmtOfAnimal = maxAmtOfAnimal;
        this.name = name;
    }

    public int getMaxAmountOfAnimal() {
        return maxAmtOfAnimal;
    }

    public String getProperName(){
        return name;
    }
}
