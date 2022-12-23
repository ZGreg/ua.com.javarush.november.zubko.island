package entity.location;


import java.util.ArrayList;
import java.util.List;

import static util.WorldSettings.ID_MAX_VALUE;
import static util.WorldSettings.ISLAND_LENGTH;

public class Island {
    private List<Location> island = new ArrayList<>(ID_MAX_VALUE);

    public Island() {
        initIsland();
    }

    private void initIsland() {
        int counter = 1;
        int row = 1;


        for (int i = 1; i < ID_MAX_VALUE; i++) {
            island.add(new Location(i, row));

            if (counter == ISLAND_LENGTH) {
                row++;
                counter = 0;
            }
            counter++;
        }

    }

    public List<Location> getIsland() {
        return island;
    }

    public Location getLocationById(Integer id) {
        return island.get(id - 1);
    }
}