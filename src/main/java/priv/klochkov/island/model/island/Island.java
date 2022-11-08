package priv.klochkov.island.model.island;

public class Island {
    int longIsland;
    int weightIsland;
    Location[][] island;

    public Island(int longIsland, int weightIsland) {
        this.longIsland = longIsland;
        this.weightIsland = weightIsland;
        island = new Location[longIsland][weightIsland];
    }
}
