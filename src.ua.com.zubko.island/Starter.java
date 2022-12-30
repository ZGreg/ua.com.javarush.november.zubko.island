import entity.location.Island;
import service.LifeSimulator;


public class Starter {

    public static void main(String[] args) {
//         SettingReader sr = new SettingsReader();
//         sr.getSettings // return instance of WorldSettings class
        Island island = new Island();
        new LifeSimulator(island).runLocations();

    }
}
