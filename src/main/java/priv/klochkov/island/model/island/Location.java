package priv.klochkov.island.model.island;

import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.plant.Plant;

import java.util.*;

public class Location {

    public List<Inhabitant> inhabitants;
    public List<Animal> animals;
    public List<Plant> plants;
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