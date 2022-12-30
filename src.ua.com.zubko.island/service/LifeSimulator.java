package service;

import entity.location.Island;
import entity.location.Location;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LifeSimulator {

    private final int coreSize = 4;
    private final int worldDuration;
    private final int periodOfAction;

    private final Island island;

    public LifeSimulator(Island island, int worldDuration, int periodOfAction) {
        this.worldDuration = worldDuration;
        this.periodOfAction =periodOfAction;
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

        List<Location> locations = island.getLocations();
        for (Location location : locations) {
            animalService.scheduleWithFixedDelay(new AnimalRunner(island,location),periodOfAction,periodOfAction, TimeUnit.MICROSECONDS);
            plantService.scheduleWithFixedDelay(new PlantRunner(location),periodOfAction,periodOfAction, TimeUnit.MICROSECONDS);
        }

        statisticService.scheduleAtFixedRate(new StatisticRunner(island),0,1,TimeUnit.SECONDS);
    }

}
