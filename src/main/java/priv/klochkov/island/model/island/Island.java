package priv.klochkov.island.model.island;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Island {
    private final int longIsland;
    private final int widthIsland;
    public List<List<Location>> field;

    public Island(int widthIsland, int longIsland){
        this.longIsland = longIsland;
        this.widthIsland = widthIsland;
        field = getLocationsIsland(widthIsland, longIsland);
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

    public List<List<Location>> getField() {
        return field;
    }
    public void setField(List<List<Location>> field){
        this.field = field;
    }


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Island island1 = new Island(3,3);
        for (List<Location> lo : island1.field) {
            for (Location lo1 : lo){
                System.out.println(lo);
            }
        }
    }
}
