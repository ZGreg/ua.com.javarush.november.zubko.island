package entity.location;

public final class Island {

    private static Island instance;
    private final Location[][] island = new Location[20][100];

    private Island() {
    }

    public static Island getInstance(){
        if(instance == null){
            instance = new Island();
        }
        return instance;
    }

    public Location [][] createIsland(){
        int id = 1;
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                island[i][j] = new Location(id,i,j);
                id++;
            }
        }
        return island;
    }

    public Location[][] getIsland() {
        return island;
    }
}
