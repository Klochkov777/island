package priv.klochkov.island.model.island;

public class Island {
    private int longIsland;
    private int weightIsland;
    public Location[][] island;

    public Island(int longIsland, int weightIsland) {
        this.longIsland = longIsland;
        this.weightIsland = weightIsland;
        island = new Location[longIsland][weightIsland];
    }

    public int getLongIsland() {
        return longIsland;
    }

    public int getWeightIsland() {
        return weightIsland;
    }

    public Location[][] getIsland() {
        return island;
    }

    public static void main(String[] args) {
        Island island1 = new Island(3,3);
        for (Location[] lo :
                island1.island) {
            for (Location lo1 :
                    lo){
                System.out.println(lo);
            }
        }
    }
}
