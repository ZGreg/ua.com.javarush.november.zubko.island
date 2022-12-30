package entity.location;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Island {
    @Getter
    private int islandLength;
    @Getter
    private int locationsAmt;

    @Getter
    private final List<Location> locations = new ArrayList<>(locationsAmt);

    public Island(int islandLength, int locationsAmt) {
        this.islandLength = islandLength;
        this.locationsAmt = locationsAmt;
        initIsland();
    }

    private void initIsland() {
        int counter = 1;
        int row = 1;


        for (int i = 1; i < locationsAmt; i++) {
            locations.add(new Location(i, row));

            if (counter == islandLength) {
                row++;
                counter = 0;
            }
            counter++;
        }

    }

    public Location getLocationById(Integer id) {
        return locations.get(id - 1);
    }
}
