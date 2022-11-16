package priv.klochkov.island.model.island;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Island {
    private final int longIsland;
    private final int widthIsland;
    private List<List<Location>> locations;

    public Island(int widthIsland, int longIsland){
        this.longIsland = longIsland;
        this.widthIsland = widthIsland;
        locations = getLocationsIsland(widthIsland, longIsland);
    }

    private List<List<Location>> getLocationsIsland(int widthIsland, int longIsland){
        List<List<Location>> result = new ArrayList<>();
        for (int i = 0; i < longIsland; i++) {
            List<Location> locationList = new ArrayList<>();
            for (int j = 0; j < widthIsland; j++) {
                locationList.add(new Location(i,j));
            }
            result.add(locationList);
        }
        return result;
    }

    public int getLongIsland() {
        return longIsland;
    }

    public int getWidthIsland() {
        return widthIsland;
    }

    public List<List<Location>> getLocations() {
        return locations;
    }
    public void setLocations(List<List<Location>> locations){
        this.locations = locations;
    }


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Island island1 = new Island(3,3);
        for (List<Location> lo : island1.locations) {
            for (Location lo1 : lo){
                System.out.println(lo);
            }
        }
    }
}
