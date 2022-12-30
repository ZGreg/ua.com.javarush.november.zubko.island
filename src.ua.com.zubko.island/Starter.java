import entity.location.Island;
import service.LifeSimulator;
import service.SettingsReader;
import seting.IslandSettings;


public class Starter {

    public static void main(String[] args) {

        IslandSettings settings = SettingsReader.setSettings();

        Island island = new Island(settings.getIslandLength(),settings.getLocationsAmt());
        new LifeSimulator(island, settings.getWorldDuration(), settings.getPeriodOfAction()).runLocations();

    }
}
