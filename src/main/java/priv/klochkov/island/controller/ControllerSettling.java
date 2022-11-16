package priv.klochkov.island.controller;

import priv.klochkov.island.config.LocationConfig;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ControllerSettling {
    Island island;

    public ControllerSettling(Island island) {
        this.island = island;
    }

    public void settleAnimalsToIsland(){
        List<List<Location>> locations = island.getLocations();
        locations.stream().forEach(list -> list.stream().forEach(location -> {
            try {
                settleAnimalsToLocation(location);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }));
        island.setLocations(locations);
    }

    private void settleAnimalsToLocation(Location location) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Inhabitant> inhabitants = new ArrayList<>();
        for (Map.Entry<Class<? extends Inhabitant>, Float> entry : LocationConfig.maxQualityInhabitants.entrySet()) {
            Class<? extends Inhabitant> clazz = entry.getKey();
            addAnimalRandomQuality(inhabitants, clazz, (int) (float) entry.getValue());
        }
        location.setInhabitants(inhabitants);
    }

    private void addAnimalRandomQuality(List<Inhabitant> inhabitants, Class<? extends Inhabitant> clazz, int maxQuality)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Random random = new Random();
        int quality = random.nextInt(maxQuality);
        for (int i = 0; i < quality; i++) {
            Inhabitant inhabitant = clazz.getConstructor().newInstance();
            inhabitants.add(inhabitant);
        }
    }
}
