import entity.location.Island;
import service.LocationRunner;

public class Main {

    public static void main(String[] args)  {

        Island island = new Island();
        new LocationRunner(island).runLocations();

    }

}
