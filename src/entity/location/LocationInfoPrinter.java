package entity.location;

import entity.creature.animal.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocationInfoPrinter {

    //  private int maxId = умножить константы

    public static void printLocationInfo(Location location){

        printCurrentAmtOfPlants(location);
        printCurrentAmtOfAnimals(location);
        //printHuntedAnimals
        //printEatenPlants
    }

    private static void printCurrentAmtOfPlants(Location location) {
        System.out.printf("Location %d contains %d plants.\n", location.getId(), location.getPlantsAmt());
    }

    private static void printCurrentAmtOfAnimals(Location location) {
        Map<String, List<Animal>> groupedAnimalMap = location.getAnimals().stream()
                .collect(Collectors.groupingBy(Animal -> Animal.getClass().getSimpleName()));


        for (Map.Entry<String, List<Animal>> entry : groupedAnimalMap.entrySet()) {
            String animal = entry.getKey();
            int amtOfAnimal = entry.getValue().size();
            System.out.printf("On location %d lives : ", location.getId());
            System.out.printf("Animal species %s, currently population is %d\n",animal,amtOfAnimal);
        }
    }
}
