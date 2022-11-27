package priv.klochkov.island.controller;

import priv.klochkov.island.constants.Direction;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;
import priv.klochkov.island.model.plant.AbstractPlant;
import priv.klochkov.island.view.ViewIsland;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static priv.klochkov.island.constants.Direction.*;
import static priv.klochkov.island.constants.Direction.UP_AND_RIGHT;

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

//    public void moveAllAnimals(){
//        locationController.setNewIslandForMove(new Island(island.getLongIsland(), island.getWidthIsland()));
//        island.field.forEach(locations -> {
//            locations.forEach(location -> {locationController.moveAllAnimalsOfLocation(location);});
//        });
//        island.field = locationController.getIslandAfterMove().field;
//    }

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



    public void moveAllAnimals() {
        Island newIsland = new Island(island.getLongIsland(), island.getWidthIsland());
        for (List<Location> listLocations : island.field) {
            for (Location oldLocation : listLocations) {
                List<Animal> animals = locationController.getAnimalsOfLocation(oldLocation);
                List<AbstractPlant> plants = locationController.getPlants(oldLocation);
                animals.forEach(animal -> {moveAnimal(animal, oldLocation, newIsland);});
                addInhabitantsToIsland(oldLocation.getX(), oldLocation.getY(), plants, newIsland);
            }
        }
        island.field = newIsland.field;
    }

    private void addInhabitantsToIsland(int finishX, int finishY, List<? extends Inhabitant> inhabitants, Island newIsland) {
        List<List<Location>> field = newIsland.field;
        for (List<Location> listLocation : field) {
            for (Location location : listLocation) {
                if (finishX == location.getX() && finishY == location.getY()) {
                    location.inhabitants.addAll(inhabitants);
                    return;
                }
            }
        }
    }

    private void moveAnimal(Animal animal, Location oldLocation, Island newIsland) {
        if (animal.getSpeedMovement() == 0) {addInhabitantToIsland(oldLocation.getX(), oldLocation.getY(), animal, newIsland);}
        int x = oldLocation.getX();
        int y = oldLocation.getY();
        for (int i = 0; i < animal.getSpeedMovement(); i++) {
            Direction direction = getRandomRightDirection(x, y, newIsland);
            switch (direction) {
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
        addInhabitantToIsland(x, y, animal, newIsland);
    }

    private void addInhabitantToIsland(int finishX, int finishY, Inhabitant inhabitant, Island newIsland) {
        List<List<Location>> field = newIsland.field;
        for (List<Location> listLocation : field) {
            Optional<Location> optional = listLocation.stream()
                    .filter(location -> (finishX == location.getX() && finishY == location.getY()))
                    .findFirst();
            optional.ifPresent(location -> location.inhabitants.add(inhabitant));
        }
    }




    private Direction getRandomRightDirection(int x, int y, Island newIsland) {
        Direction direction = null;
        boolean isRightDirection = false;
        while (!isRightDirection) {
            direction = getRandomDirection();
            isRightDirection = getIsCorrectDirection(direction, x, y, newIsland);
        }
        return direction;
    }

    private Direction getRandomDirection() {
        return Direction.randomDirection();
    }

    private boolean getIsCorrectDirection(Direction direction, int x, int y, Island newIsland) {
        return (checkConditionMaxUpX(direction, x) || checkConditionMaxDownX(direction, x, newIsland) ||
                checkConditionMaxLeftY(direction, y) || checkConditionMaxRightY(direction, y, newIsland));
    }

    private boolean checkConditionMaxUpX(Direction direction, int x) {
        return !(x == 0 && (direction == UP || direction == UP_AND_RIGHT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxDownX(Direction direction, int x, Island newIsland) {
        return !((x == newIsland.getLongIsland() - 1) &&
                (direction == DOWN || direction == DOWN_AND_RIGHT || direction == DOWN_AND_LEFT));
    }

    private boolean checkConditionMaxLeftY(Direction direction, int y) {
        return !(y == 0 && (direction == LEFT || direction == DOWN_AND_LEFT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxRightY(Direction direction, int y, Island newIsland) {
        return !(y == newIsland.getWidthIsland() - 1) &&
                (direction == RIGHT || direction == DOWN_AND_RIGHT || direction == UP_AND_RIGHT);
    }




}
