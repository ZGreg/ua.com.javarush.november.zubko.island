package entity.location;




import seting.WorldSettings;

import java.util.ArrayList;
import java.util.List;

import static seting.WorldSettings.ISLAND_LENGTH;

public class Island {

    private final List<Location> locations = new ArrayList<>(WorldSettings.ID_MAX_VALUE);

    public Island() {
        initIsland();
    }

    private void initIsland() {
        int counter = 1;
        int row = 1;


        for (int i = 1; i < WorldSettings.ID_MAX_VALUE; i++) {
            locations.add(new Location(i, row));

            if (counter == ISLAND_LENGTH) {
                row++;
                counter = 0;
            }
            counter++;
        }

    }

    public List<Location> getLocations() {
        return locations;
    }

    public Location getLocationById(Integer id) {
        return locations.get(id - 1);
    }
}
