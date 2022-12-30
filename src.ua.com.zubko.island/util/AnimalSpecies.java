package util;

import entity.creature.animal.herbivorous.*;
import entity.creature.animal.predator.*;

public enum AnimalSpecies {
    BEAR(5, Bear.class.getSimpleName(),"\uD83D\uDC3B"),
    BOACONSTRICTOR(30, BoaConstrictor.class.getSimpleName(),"\uD83D\uDC0D"),
    EAGLE(20, Eagle.class.getSimpleName(),"\uD83E\uDD85"),
    FOX(30, Fox.class.getSimpleName(),"\uD83E\uDD8A"),
    WOLF(30, Wolf.class.getSimpleName(),"\uD83D\uDC3A"),
    BUFFALO(10, Buffalo.class.getSimpleName(),"\uD83D\uDC03"),
    CATERPILLAR(1000, Caterpillar.class.getSimpleName(),"\uD83D\uDC1B"),
    DEER(20, Deer.class.getSimpleName(),"\uD83E\uDD8C"),
    DUCK(200, Duck.class.getSimpleName(),"\uD83E\uDD86"),
    GOAT(140, Goat.class.getSimpleName(),"\uD83D\uDC10"),
    HORSE(20,Horse.class.getSimpleName(),"\uD83D\uDC0E"),
    MOUSE(500,Mouse.class.getSimpleName(),"\uD83D\uDC01"),
    RABBIT(150,Rabbit.class.getSimpleName(),"\uD83D\uDC07"),
    SHEEP(140,Sheep.class.getSimpleName(),"\uD83D\uDC11"),
    WILDBOAR(50,WildBoar.class.getSimpleName(),"\uD83D\uDC17");

    private final int maxAmtOfAnimal;
    private final String name;

    private final String icon;
    AnimalSpecies(int maxAmtOfAnimal, String name,String icon){
        this.maxAmtOfAnimal = maxAmtOfAnimal;
        this.name = name;
        this.icon = icon;
    }

    public int getMaxAmountOfAnimal() {
        return maxAmtOfAnimal;
    }

    public String getProperName(){
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
