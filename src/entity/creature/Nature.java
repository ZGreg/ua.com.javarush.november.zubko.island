package entity.creature;

import entity.location.Location;

public interface Nature {

    //default reproduce method
    void reproduce(Location location);
    double getWeight();
}
