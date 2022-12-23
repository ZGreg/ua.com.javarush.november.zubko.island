package entity.creature.animal;

public interface Mortal {

    default boolean isDead(Double satiety){      //use method before running animals
        return satiety == 0.0;
    }  //"<----- delete an animal if satiety == 0 at the end of each loop/tact"
}
