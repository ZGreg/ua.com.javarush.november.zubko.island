package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import seting.IslandSettings;

import java.io.File;
import java.io.IOException;

public class SettingsReader {
    private static final String SETTINGS_FILE = "Settings.json";

    public static IslandSettings setSettings() {

        File file = new File(SETTINGS_FILE);

        ObjectMapper mapper = new ObjectMapper();

        IslandSettings islandSettings = null;
        try {
            islandSettings = mapper.readValue(file, IslandSettings.class);
        } catch (IOException e) {
            System.out.println("Something went wrong with " + SETTINGS_FILE + "file.");
        }
        return islandSettings;
    }
}
