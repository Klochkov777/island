package priv.klochkov.island.controller;

import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class IslandController {
    Island island;
    LocationController locationController;

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
            locations.forEach(locationController::moveAllAnimalsOfLocation);
        });
        island = locationController.getIslandAfterMove();
    }

    public void eat() {
        island.field.forEach(locations -> {
            locations.forEach(locationController::eatInhabitantsOfLocation);
        });
    }

    public void setIsland(Island island) {
        this.island = island;
    }
}
