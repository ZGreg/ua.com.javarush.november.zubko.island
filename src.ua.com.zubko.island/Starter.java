import entity.location.Island;
import service.LifeSimulator;


public class Starter {

    public static void main(String[] args) {

        Island island = new Island();
        new LifeSimulator(island).runLocations();

    }
}
