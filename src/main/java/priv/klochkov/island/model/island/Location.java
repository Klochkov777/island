package priv.klochkov.island.model.island;

import priv.klochkov.island.model.Inhabitant;
import java.util.*;

public class Location {

    public List<Inhabitant> inhabitants;
    private int x;
    private  int y;

    public Location(int x, int y) {
        this.inhabitants = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public List<Inhabitant> getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(List<Inhabitant> inhabitants) {
        this.inhabitants = inhabitants;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}