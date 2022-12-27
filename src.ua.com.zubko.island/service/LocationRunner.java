package service;

import entity.location.Island;
import entity.location.Location;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocationRunner {

    private final int coreSize = 4;
    private final int worldDuration = 7000;
    private final int period = 1000;

    private final Island island;

    public LocationRunner(Island island) {
        this.island = island;
    }

    public void runLocations() {

        ScheduledExecutorService animalService = Executors.newScheduledThreadPool(coreSize);
        ScheduledExecutorService plantService = Executors.newScheduledThreadPool(coreSize);
        ScheduledExecutorService statisticService = Executors.newSingleThreadScheduledExecutor();

        imitateLife(animalService,plantService,statisticService);

        try{
            Thread.sleep(worldDuration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        animalService.shutdownNow();
        plantService.shutdownNow();
        statisticService.shutdownNow();


    }


    private void imitateLife(ScheduledExecutorService animalService,ScheduledExecutorService plantService,
                             ScheduledExecutorService statisticService){

        List<Location> locations = island.getIsland();
        for (Location location : locations) {
            animalService.scheduleWithFixedDelay(new AnimalRunner(island,location),period,period, TimeUnit.MICROSECONDS);
            plantService.scheduleWithFixedDelay(new PlantRunner(location),period,period, TimeUnit.MICROSECONDS);
        }

        statisticService.scheduleAtFixedRate(new StatisticRunner(island),2,2,TimeUnit.SECONDS);
    }

}
