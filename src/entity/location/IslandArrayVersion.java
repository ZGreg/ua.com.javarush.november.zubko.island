package entity.location;

import util.WorldSettings;

import java.util.ArrayList;
import java.util.List;

public class IslandArrayVersion {


    private List<Location> island = new ArrayList<>(WorldSettings.ID_MAX_VALUE);

    public IslandArrayVersion() {
        initIsland();
    }

    private void initIsland(){
        for (int i = 0; i < WorldSettings.ID_MAX_VALUE; i++) {
            island.add(new Location(i,1,1));
        }
    }

    public List<Location> getIsland() {
        return island;
    }

    public Location getLocationById(Integer id){
        return island.get(id);
    }
}
