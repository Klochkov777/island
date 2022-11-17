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
    Island island;

    public ControllerMove(Animal animal, Island island) {
        this.animal = animal;
        this.island = island;
    }

    public void moveAnimal(){
        for (int i = 0; i < animal.getSpeedMovement(); i++) {
            Direction direction = getRandomRightDirection();
            animal.move(direction);
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
        return !((animal.getX() == island.getLongIsland() - 1) &&
                (direction == DOWN || direction == DOWN_AND_RIGHT || direction == DOWN_AND_LEFT));
    }

    private boolean checkConditionMaxLeftY(Direction direction){
        return !(animal.getY() == 0 && (direction == LEFT || direction == DOWN_AND_LEFT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxRightY(Direction direction){
        return !(animal.getY() == island.getWidthIsland() - 1) &&
                (direction == RIGHT || direction == DOWN_AND_RIGHT || direction == UP_AND_RIGHT);
    }

    public static void main(String[] args) {
        Island island = new Island(3,3);
        Wolf wolf1 = new Wolf(0,1);
        Location location = null;
        for (List<Location> list : island.getLocations()) {
            List<Location> list1 = list.stream().filter(location1 -> (location1.getX() == 0 && location1.getY() == 1)).toList();
            location = list1.get(0);
            if (location != null) break;
        }
        List<Inhabitant> list = new ArrayList<>();
        list.add(wolf1);
        location.setInhabitants(list);
        ControllerMove controllerMove = new ControllerMove(wolf1, island);
        controllerMove.moveAnimal();
        System.out.println(wolf1.getX() + " " + wolf1.getY());
    }
}
