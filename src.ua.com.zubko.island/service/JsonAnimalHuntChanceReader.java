package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonAnimalHuntChanceReader {

    private static final String RATION_FILE = "AnimalsRation.json";

    private JsonAnimalHuntChanceReader() {
    }

    public static Map<String, Integer> getHuntingChanceMap(String animalClassName) {
        Map<String, Object> json = null;
        Map<String, Integer> map = null;
        try {
            json = readJson();
            map = (Map<String, Integer>) json.get(animalClassName);
        } catch (IOException e) {
            System.out.println("Something went wrong with " + RATION_FILE + "file.");
        }
        return map;
    }

    private static Map<String, Object> readJson() throws IOException {

        Map<String, Object> animals;

        File file = new File(RATION_FILE);
        ObjectMapper mapper = new ObjectMapper();
        animals = mapper.readValue(file, new TypeReference<>() {
        });
        return animals;
    }
}
