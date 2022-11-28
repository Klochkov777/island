package priv.klochkov.island.controller;

import priv.klochkov.island.Factory.FactoryInhabitant;
import priv.klochkov.island.config.InhabitantConfig;
import priv.klochkov.island.constants.Direction;
import priv.klochkov.island.constants.Gender;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.herbivores.Boar;
import priv.klochkov.island.model.animal.herbivores.Goat;
import priv.klochkov.island.model.animal.herbivores.Herbivore;
import priv.klochkov.island.model.animal.herbivores.Mouse;
import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;
import priv.klochkov.island.model.animal.interfaces.IEatablePlant;
import priv.klochkov.island.model.animal.predators.Fox;
import priv.klochkov.island.model.animal.predators.Predator;
import priv.klochkov.island.model.animal.predators.Wolf;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;
import priv.klochkov.island.model.plant.AbstractPlant;
import priv.klochkov.island.model.plant.Plant;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static priv.klochkov.island.constants.Direction.*;
import static priv.klochkov.island.constants.Direction.UP_AND_RIGHT;
import static priv.klochkov.island.constants.Gender.FEMALE;
import static priv.klochkov.island.constants.Gender.MALE;

public class LocationController {

    Island newIslandForMove;
    private Location location;
    private Random random = new Random();


    public void settleInhabitantsToLocation(Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        location.setInhabitants(FactoryInhabitant.createInhabitants());
    }

    public Island getIslandAfterMove() {
        return newIslandForMove;
    }

    public void moveAllAnimalsOfLocation(Location location) {
        List<Animal> animals = location.inhabitants.stream()
                .filter(inhabitant -> inhabitant instanceof Animal)
                .map(inhabitant -> (Animal) inhabitant).toList();
        animals.forEach(animal -> {moveAnimal(animal, location);});


        //moveAnimal(animal1, location);
    }

    private void moveAnimal(Animal animal, Location location) {
        location.inhabitants.remove(animal);
        int x = location.getX();
        int y = location.getY();
        for (int i = 0; i < animal.getSpeedMovement(); i++) {
            Direction direction = getRandomRightDirection(x, y);
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
        addAnimalToLocation(x, y, animal);
    }

    private void addAnimalToLocation(int finishX, int finishY, Animal animal) {
        List<List<Location>> field = newIslandForMove.field;
        for (List<Location> listLocation : field) {
            for (Location location : listLocation) {
                if (finishX == location.getX() && finishY == location.getY()) {
                    location.inhabitants.add(animal);
                    return;
                }
            }
        }
    }

    private Direction getRandomRightDirection(int x, int y) {
        Direction direction = null;
        boolean isRightDirection = false;
        while (!isRightDirection) {
            direction = getRandomDirection();
            isRightDirection = getIsCorrectDirection(direction, x, y);
        }
        return direction;
    }

    private Direction getRandomDirection() {
        return Direction.randomDirection();
    }

    private boolean getIsCorrectDirection(Direction direction, int x, int y) {
        return (checkConditionMaxUpX(direction, x) || checkConditionMaxDownX(direction, x) ||
                checkConditionMaxLeftY(direction, y) || checkConditionMaxRightY(direction, y));
    }

    private boolean checkConditionMaxUpX(Direction direction, int x) {
        return !(x == 0 && (direction == UP || direction == UP_AND_RIGHT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxDownX(Direction direction, int x) {
        return !((x == newIslandForMove.getLongIsland() - 1) &&
                (direction == DOWN || direction == DOWN_AND_RIGHT || direction == DOWN_AND_LEFT));
    }

    private boolean checkConditionMaxLeftY(Direction direction, int y) {
        return !(y == 0 && (direction == LEFT || direction == DOWN_AND_LEFT || direction == UP_AND_LEFT));
    }

    private boolean checkConditionMaxRightY(Direction direction, int y) {
        return !(y == newIslandForMove.getWidthIsland() - 1) &&
                (direction == RIGHT || direction == DOWN_AND_RIGHT || direction == UP_AND_RIGHT);
    }


    public void eatInhabitantsOfLocation(Location location) {
        eatByPredatorsOfLocation(location);
        eatByOmnivoresOfLocation(location);
        eatByHerbivoreOfLocation(location);
    }

    private void eatByPredatorsOfLocation(Location location) {
        List<Predator> predators = getPredators(location);
        eatByAnimals(predators, location);
    }

    private void eatByOmnivoresOfLocation(Location location) {
        List<Animal> omnivores = getOmnivores(location);
        eatByAnimals(omnivores, location);
    }

    private void eatByHerbivoreOfLocation(Location location) {
        List<Herbivore> herbivores = getHerbivores(location);
        eatByAnimals(herbivores, location);
    }

    private List<Predator> getPredators(Location location) {
        return location.inhabitants.stream().
                filter(inhabitant -> inhabitant instanceof Predator).
                map(inhabitant -> (Predator) inhabitant).toList();
    }

    private List<Animal> getOmnivores(Location location) {
        return location.inhabitants.stream().
                filter(inhabitant -> ((inhabitant instanceof IEatableAnimal) && (inhabitant instanceof IEatablePlant))).
                map(inhabitant -> (Animal) inhabitant).toList();
    }

    private List<Herbivore> getHerbivores(Location location) {
        return location.inhabitants.stream().
                filter(inhabitant -> (!(inhabitant instanceof IEatableAnimal) && (inhabitant instanceof IEatablePlant))).
                map(inhabitant -> (Herbivore) inhabitant).toList();
    }

    private void eatByAnimals(List<? extends Animal> animals, Location location) {
        Iterator<? extends Animal> iteratorAnimal = animals.iterator();
        while (iteratorAnimal.hasNext()) {
            Animal animal = iteratorAnimal.next();
            if (!location.inhabitants.contains(animal)){continue;}         //уже само было съедено
            Map<Class<? extends Inhabitant>, Float> probabilityAttack = InhabitantConfig.dataProbability.get(animal.getClass());
            huntAnimal(animal, probabilityAttack, location);
        }
    }

    private void huntAnimal(Animal animal, Map<Class<? extends Inhabitant>, Float> probabilityAttack, Location location) {
        List<Inhabitant> inhabitantsDeath = new ArrayList<>();
        for (Inhabitant inhabitantUnderAttack: location.inhabitants) {
            if (animal.isFullSatiety()) {break;}
            if (animal.getClass().equals(inhabitantUnderAttack.getClass())){continue;}
            int probability = random.nextInt(100);
            float needProbability = probabilityAttack.get(inhabitantUnderAttack.getClass());
            if (needProbability == 0) {continue;}
            if (probability <= needProbability) {
                animal.eat(inhabitantUnderAttack);
                inhabitantsDeath.add(inhabitantUnderAttack);
            }
        }
        location.inhabitants.removeAll(inhabitantsDeath);
    }

    public void deathHungryAnimals(Location location) {
        Iterator<Inhabitant> iteratorInhabitant = location.inhabitants.iterator();
        while (iteratorInhabitant.hasNext()) {
            Inhabitant inhabitant = iteratorInhabitant.next();
            if (inhabitant instanceof Animal animal) {
                if (animal.getSatiety() < animal.getMinSatiety()) {iteratorInhabitant.remove();}
            }
        }
    }

    // I must filter class of Animal
    public void mateAnimals(Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<Class<? extends Animal>, List<Animal>> males = getAnimalsByGender(location, MALE);
        Map<Class<? extends Animal>, List<Animal>> females = getAnimalsByGender(location, FEMALE);
        for (Map.Entry<Class<? extends Animal>, List<Animal>> entry : males.entrySet()) {
            List<Animal> listMales = entry.getValue();
            List<Animal> listFemales = females.get(entry.getKey());
            listMales = new ArrayList<>(listMales);
            listFemales = new ArrayList<>(listFemales);
            findCoupleForMating(listMales, listFemales, location);
        }
    }

    private Map<Class<? extends Animal>, List<Animal>> getAnimalsByGender(Location location, Gender gender) {
        Map<Class<? extends Animal>, List<Animal>> result = new HashMap<>();
        for (Class<? extends Animal> clazz : InhabitantConfig.classesAnimal) {
            List<Animal> listMales = location.inhabitants.stream().
                    filter(inhabitant -> (inhabitant.getClass().equals(clazz))).
                    map(inhabitant -> (Animal) inhabitant).
                    filter(animal -> animal.getGender() == gender).toList();
            result.put(clazz, listMales);
        }
        return result;
    }

    private void findCoupleForMating(List<Animal> males, List<Animal> females, Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Iterator<Animal> iteratorMale = males.iterator();
        Iterator<Animal> iteratorFemale = females.iterator();
        while (iteratorMale.hasNext()) {
            Animal male = iteratorMale.next();
            if (females.size() == 0) {
                return;
            }
            while (iteratorFemale.hasNext()) {
                iteratorFemale.next();
                iteratorFemale.remove();
                giveBirth(male.getClass(), location);
            }
        }
    }

    private void giveBirth(Class<? extends Animal> clazz, Location location) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int maxCountChildren = InhabitantConfig.maxKids.get(clazz);
        int countChild = random.nextInt(maxCountChildren);
        Constructor<? extends Animal> constructor = clazz.getConstructor();
        for (int i = 0; i < countChild; i++) {
            Animal animal = constructor.newInstance();
            location.inhabitants.add(animal);
        }
    }

    public void growPlant(Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Class<? extends AbstractPlant> clazz : InhabitantConfig.classesPlants) {
            int qualityPlant = InhabitantConfig.qualityPlantEveryStep.get(clazz);
            List<AbstractPlant> plants = FactoryInhabitant.createPlants(qualityPlant, clazz);
            synchronized (location.inhabitants) {
                location.inhabitants.addAll(plants);
            }
        }
    }

    public void setNewIslandForMove(Island island) {
        this.newIslandForMove = island;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void removeInhabitant(Location location, Inhabitant inhabitant) {
        location.inhabitants.remove(inhabitant);
    }

    public void addInhabitant(Location location, Inhabitant inhabitant) {
        location.inhabitants.add(inhabitant);
    }

    public List<Animal> getAnimalsOfLocation(Location location) {
        return location.inhabitants.stream()
                .filter(inhabitant -> inhabitant instanceof Animal)
                .map(inhabitant -> (Animal) inhabitant).toList();
    }

    public List<AbstractPlant> getPlants(Location location) {
        return location.inhabitants.stream()
                .filter(inhabitant -> inhabitant instanceof AbstractPlant)
                .map(inhabitant -> (AbstractPlant) inhabitant).toList();
    }

    public static void main(String[] args) {
        Location location1 = new Location(0,0);
        location1.inhabitants.addAll(List.of(new Wolf(), new Wolf(), new Goat(), new Fox(), new Boar(), new Plant(), new Plant(), new Mouse(), new Mouse(), new Mouse()));
        location1.inhabitants.forEach(System.out::println);
        new LocationController().eatInhabitantsOfLocation(location1);
        System.out.println();
        location1.inhabitants.forEach(System.out::println);;
    }
}
