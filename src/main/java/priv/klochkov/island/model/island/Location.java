package priv.klochkov.island.model.island;

import priv.klochkov.island.config.InhabitantConfig;
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

    public Map<Class<? extends Inhabitant>, Long> getQualityInhabitant() {
        Map<Class<? extends Inhabitant>, Long> result = new HashMap<>();
        InhabitantConfig.classesInhabitant.forEach(clazz -> this.inhabitants.stream().filter(inhabitant -> clazz.equals(inhabitant.getClass())).count());
        for (Class<? extends Inhabitant> clazz : InhabitantConfig.classesInhabitant) {
            long qualityInhabitant = this.inhabitants.stream().filter(inhabitant -> clazz.equals(inhabitant.getClass())).count();
            result.put(clazz, qualityInhabitant);
        }
        return result;
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