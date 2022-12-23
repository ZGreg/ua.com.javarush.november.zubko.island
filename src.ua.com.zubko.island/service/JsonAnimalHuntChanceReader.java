package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import util.AnimalSpecies;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonAnimalHuntChanceReader {

    public static Map<String,Integer> getHuntingChanceMap(String animalClassName)  {  //add exception wrong file name
        Map<String, Object> json = null;
        try {
            json = readJson();
            Map <String,Integer> map = (Map<String, Integer>) json.get(animalClassName);
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> readJson() throws IOException {

        Map<String, Object> animals;

        File file = new File("AnimalsRation.json");
        ObjectMapper mapper = new ObjectMapper();
        animals = mapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
        return animals;
    }
}
