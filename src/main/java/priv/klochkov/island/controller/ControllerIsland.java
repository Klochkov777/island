package priv.klochkov.island.controller;

import priv.klochkov.island.constants.Direction;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ControllerIsland {
    Island island;
    ControllerLocation controllerLocation;

    public ControllerIsland(Island island, ControllerLocation controllerLocation) {
        this.island = island;
        this.controllerLocation = controllerLocation;
    }

    public void settleAnimalsToIsland(){
        List<List<Location>> locations = island.getField();
        locations.stream().forEach(list -> list.stream().forEach(location -> {
            try {
                controllerLocation.settleInhabitantsToLocation(location);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }));
        island.setField(locations);
    }

    private void move(Location location, Direction direction){
        int x = location.getX();
        int y = location.getY();
        switch (direction){
            case UP -> x++;
            case DOWN -> x--;
            case LEFT -> y--;
            case RIGHT -> y++;
            case UP_AND_RIGHT -> {x++; y++;}
            case UP_AND_LEFT -> {x++; y--;}
            case DOWN_AND_LEFT -> {x--; y--;}
            case DOWN_AND_RIGHT -> {x--; y++;}
        }
    }
}
