package entity.creature.animal;

import entity.location.Location;

import java.util.Map;

public abstract class Animal {

    private double weight;
    private double satiety;
    private double currentSatiety;
    private int speed;

    private Map<String,Integer> eatenThings;


    //maxSatiety               boolean isHungry(){if(maxSatiety == currentSatiety ? false : true;
    //currentSatiety      create getter and use in eat method

    public Animal(double weight, double satiety, int speed, Map<String, Integer> eatenThings) {
        this.weight = weight;
        this.satiety = satiety;
        this.speed = speed;
        this.eatenThings = eatenThings;
    }

    public boolean isHungry(){
        return !(currentSatiety == satiety);
    }

    public void eat(Location loc){};

    public void move(){}

    public void reproduce(){};

    public double getCurrentSatiety() {
        return currentSatiety;
    }

    public void setCurrentSatiety(double currentSatiety) {
        this.currentSatiety = currentSatiety;
    }

    public double getWeight() {
        return weight;
    }

    public double getSatiety() {
        return satiety;
    }

    public Map<String, Integer> getEatenThings() {
        return eatenThings;
    }
}
