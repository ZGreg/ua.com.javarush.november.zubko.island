package entity.creature.animal.herbivorous;


import entity.location.Island;
import entity.location.Location;

public class Caterpillar extends Herbivorous{

    public Caterpillar(double weight, double satiety, int speed) {
        super(weight, satiety, speed);
    }
    @Override
    public void move(Location location, Island island){}
}
