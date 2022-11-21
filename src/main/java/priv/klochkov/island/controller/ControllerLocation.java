package priv.klochkov.island.controller;

import priv.klochkov.island.Factory.FactoryInhabitant;
import priv.klochkov.island.config.AnimalConfig;
import priv.klochkov.island.constants.Direction;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static priv.klochkov.island.constants.Direction.*;
import static priv.klochkov.island.constants.Direction.UP_AND_RIGHT;

public class ControllerLocation {
    Location location;
    Island oldIsland;
    Island newIsland;

    public ControllerLocation(Location location, Island newIsland, Island oldIsland) {
        this.location = location;
        this.newIsland = newIsland;
    }

    public void settleInhabitantsToLocation(Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        location.setInhabitants(FactoryInhabitant.createInhabitants());
    }

    public void moveAllAnimalsOfLocation(Location location){
        location.inhabitants.stream().filter(inhabitant -> inhabitant instanceof Animal).forEach(inhabitant -> {
            Animal animal1 = (Animal) inhabitant; moveAnimal(location, animal1);});
    }

    private void moveAnimal(Location location, Animal animal){
        location.inhabitants.remove(animal);
        int x = location.getX();
        int y = location.getY();
        for (int i = 0; i < animal.getSpeedMovement(); i++) {
            Direction direction = getRandomRightDirection(x, y);
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
                if (finishX == location.getX() && finishY == location.getY()) {destinationLocation.inhabitants.add(animal); return;}//&&&&&&&&&&&&&&&&&&&&&&&&
            }
        }
    }

    private Direction getRandomRightDirection(int x, int y){
        Direction direction = null;
        boolean isRightDirection = false;
        while (!isRightDirection){
            direction = getRandomDirection();
            isRightDirection = getIsCorrectDirection(direction, x, y);
        }
        return direction;
    }

    private Direction getRandomDirection(){
        return Direction.randomDirection();
    }

    private boolean getIsCorrectDirection(Direction direction, int x, int y){
        return (checkConditionMaxUpX(direction, x) || checkConditionMaxDownX(direction, x) ||
                checkConditionMaxLeftY(direction, y) || checkConditionMaxRightY(direction, y));
    }

    private boolean checkConditionMaxUpX(Direction direction ,int x){
        return !(x == 0 && (direction == UP || direction == UP_AND_RIGHT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxDownX(Direction direction, int x){
        return !((x == oldIsland.getLongIsland() - 1) &&
                (direction == DOWN || direction == DOWN_AND_RIGHT || direction == DOWN_AND_LEFT));
    }

    private boolean checkConditionMaxLeftY(Direction direction, int y){
        return !(y == 0 && (direction == LEFT || direction == DOWN_AND_LEFT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxRightY(Direction direction, int y){
        return !(y == oldIsland.getWidthIsland() - 1) &&
                (direction == RIGHT || direction == DOWN_AND_RIGHT || direction == UP_AND_RIGHT);
    }




    public boolean eatAnimal(){
        Map<Class<? extends Animal>, Map<Class<? extends Animal>, Float>> dataProbability = AnimalConfig.dataProbability;
        Random random = new Random();
        List<Animal> animals = location.inhabitants.stream().
                filter(inhabitant -> inhabitant instanceof Animal).
                map(inhabitant -> (Animal) inhabitant).toList();
        for (Animal animal : animals) {
            Map<Class<? extends Animal>, Float> probabilityAttack = dataProbability.get(animal);
            for (Animal animalByAttack : animals) {
                if (animal.getMaxSatiety() <= animal.getSatiety()) {break;}
                int probability = random.nextInt();
                if ()

            }
        }

    }
}
