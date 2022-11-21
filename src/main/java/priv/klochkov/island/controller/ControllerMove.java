package priv.klochkov.island.controller;

import priv.klochkov.island.constants.Direction;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.predators.Wolf;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.util.ArrayList;
import java.util.List;

import static priv.klochkov.island.constants.Direction.*;

public class ControllerMove {

    public Animal animal;
    Island oldIsland;
    Island newIsland;

    public ControllerMove(Animal animal, Island island) {
        this.animal = animal;
        this.oldIsland = island;
        newIsland = new Island(oldIsland.getWidthIsland(), oldIsland.getLongIsland());
    }


    private void moveAnimal(Location location, Animal animal){
        location.inhabitants.remove(animal);
        int x = location.getX();
        int y = location.getY();
        for (int i = 0; i < animal.getSpeedMovement(); i++) {
            Direction direction = getRandomRightDirection();
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
        moveAnimalToLocation(x, y, animal);
    }

    private void moveAnimalToLocation(int finishX, int finishY, Animal animal){
        List<List<Location>> field = newIsland.field;
        Location destinationLocation = null;
        for (List<Location> listLocation: field) {
            for (Location location : listLocation) {
                if (finishX == location.getX() && finishY == location.getY()) {destinationLocation.inhabitants.add(animal); return;}
            }
        }
    }

    private Direction getRandomRightDirection(){
        Direction direction = null;
        boolean isRightDirection = false;
        while (!isRightDirection){
            direction = getRandomDirection();
            isRightDirection = getIsCorrectDirection(direction);
        }
        return direction;
    }

    private Direction getRandomDirection(){
        return Direction.randomDirection();
    }

    private boolean getIsCorrectDirection(Direction direction){
       return (checkConditionMaxUpX(direction) || checkConditionMaxDownX(direction) ||
               checkConditionMaxLeftY(direction) || checkConditionMaxRightY(direction));
    }

    private boolean checkConditionMaxUpX(Direction direction){
       return !(animal.getX()== 0 && (direction == UP || direction == UP_AND_RIGHT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxDownX(Direction direction){
        return !((animal.getX() == oldIsland.getLongIsland() - 1) &&
                (direction == DOWN || direction == DOWN_AND_RIGHT || direction == DOWN_AND_LEFT));
    }

    private boolean checkConditionMaxLeftY(Direction direction){
        return !(animal.getY() == 0 && (direction == LEFT || direction == DOWN_AND_LEFT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxRightY(Direction direction){
        return !(animal.getY() == oldIsland.getWidthIsland() - 1) &&
                (direction == RIGHT || direction == DOWN_AND_RIGHT || direction == UP_AND_RIGHT);
    }

    public static void main(String[] args) {
        Island island = new Island(3,3);
        Wolf wolf1 = new Wolf();
        Location location = null;
        for (List<Location> list : island.getField()) {
            List<Location> list1 = list.stream().filter(location1 -> (location1.getX() == 0 && location1.getY() == 1)).toList();
            location = list1.get(0);
            if (location != null) break;
        }
        List<Inhabitant> list = new ArrayList<>();
        list.add(wolf1);
        location.setInhabitants(list);
        ControllerMove controllerMove = new ControllerMove(wolf1, island);
//        controllerMove.moveAnimal();
        System.out.println(wolf1.getX() + " " + wolf1.getY());
    }
}
