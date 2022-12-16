package entity.creature;

public interface Mortal {

    default boolean isDead(Double satiety){
        return satiety == 0.0;
    }  //"<----- delete an animal if satiety == 0 at the end of each loop/tact"
}
