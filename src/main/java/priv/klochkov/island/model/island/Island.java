package priv.klochkov.island.model.island;

import java.lang.reflect.InvocationTargetException;

public class Island {
    private int longIsland;
    private int weightIsland;
    public Location[][] locations;

    public Island(int longIsland, int weightIsland) {
        this.longIsland = longIsland;
        this.weightIsland = weightIsland;
        locations = new Location[longIsland][weightIsland];
    }

    public int getLongIsland() {
        return longIsland;
    }

    public int getWeightIsland() {
        return weightIsland;
    }

    public Location[][] getIsland() {
        return locations;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Island island1 = new Island(3,3);
        for (Location[] lo :
                island1.locations) {
            for (Location lo1 : lo){
                lo1.createAnimalsOnLocation();
                System.out.println(lo);
            }
        }
    }
}
