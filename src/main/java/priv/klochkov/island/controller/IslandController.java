package priv.klochkov.island.controller;

import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;
import priv.klochkov.island.view.ViewIsland;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class IslandController {
    private Island island;
    private LocationController locationController;

    public IslandController() {
        this.locationController = new LocationController();
    }

    public void settleAnimalsToIsland(){
        List<List<Location>> locations = island.getField();
        locations.forEach(list -> list.forEach(location -> {
            try {
                locationController.settleInhabitantsToLocation(location);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }));
        island.setField(locations);
    }

    public void moveAllAnimals(){
        locationController.setNewIslandForMove(new Island(island.getLongIsland(), island.getWidthIsland()));
        island.field.forEach(locations -> {
            locations.forEach(location -> {locationController.moveAllAnimalsOfLocation(location);});
        });
        island = locationController.getIslandAfterMove();
    }

    public void eat() {
        island.field.forEach(locations -> {
            locations.forEach(location -> {locationController.eatInhabitantsOfLocation(location);});
        });
    }

    public void mate() {
        island.field.forEach(locations -> {
            locations.forEach(location -> {
                try {
                    locationController.mateAnimals(location);
                } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    public void growPlant() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (List<Location> list : island.field) {
            for (Location location : list) {
                locationController.growPlant(location);
            }
        }
    }

    public void printIsland() {
        ViewIsland viewIsland = new ViewIsland(island);
        viewIsland.printIsland();
        viewIsland.separate();
    }

    public void setIsland(Island island) {
        this.island = island;
    }
}
