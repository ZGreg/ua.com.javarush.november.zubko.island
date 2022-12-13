package util;

public enum AnimalSpecies {
    BEAR(5),
    BOA_CONSTRICTOR(30),
    EAGLE(20),
    FOX(30),
    WOLF(30),
    BUFFALO(10),
    CATERPILLAR(1000),
    DEER(20),
    DUCK(200),
    GOAT(140),
    HORSE(20),
    MOUSE(500),
    RABBIT(150),
    SHEEP(140),
    WILDBOAR(50);

    private int maxAmtOfAnimal;
    AnimalSpecies(int maxAmtOfAnimal){
        this.maxAmtOfAnimal = maxAmtOfAnimal;
    }

    public int getMaxAmountOfAnimal() {
        return maxAmtOfAnimal;
    }
}
