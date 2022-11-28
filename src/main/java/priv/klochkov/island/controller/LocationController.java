package priv.klochkov.island.controller;

import priv.klochkov.island.Factory.FactoryInhabitant;
import priv.klochkov.island.config.InhabitantConfig;
import priv.klochkov.island.constants.Direction;
import priv.klochkov.island.constants.Gender;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.herbivores.Herbivore;
import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;
import priv.klochkov.island.model.animal.interfaces.IEatablePlant;
import priv.klochkov.island.model.animal.predators.Predator;
import priv.klochkov.island.model.island.Location;
import priv.klochkov.island.model.plant.AbstractPlant;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static priv.klochkov.island.constants.Gender.FEMALE;
import static priv.klochkov.island.constants.Gender.MALE;

public class LocationController {

    private Location location;
    private Random random = new Random();


    public void settleInhabitantsToLocation(Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        FactoryInhabitant factoryInhabitant = new FactoryInhabitant();
        location.setInhabitants(factoryInhabitant.createInhabitants());
    }

    public void eatInhabitantsOfLocation() {
        eatByPredatorsOfLocation();
        eatByOmnivoresOfLocation();
        eatByHerbivoreOfLocation();
    }

    private void eatByPredatorsOfLocation() {
        List<Predator> predators = getPredators(location);
        eatByAnimals(predators);
    }

    private void eatByOmnivoresOfLocation() {
        List<Animal> omnivores = getOmnivores();
        eatByAnimals(omnivores);
    }

    private void eatByHerbivoreOfLocation() {
        List<Herbivore> herbivores = getHerbivores();
        eatByAnimals(herbivores);
    }

    private List<Predator> getPredators(Location location) {
        return location.inhabitants.stream().
                filter(inhabitant -> inhabitant instanceof Predator).
                map(inhabitant -> (Predator) inhabitant).toList();
    }

    private List<Animal> getOmnivores() {
        return location.inhabitants.stream().
                filter(inhabitant -> ((inhabitant instanceof IEatableAnimal) && (inhabitant instanceof IEatablePlant))).
                map(inhabitant -> (Animal) inhabitant).toList();
    }

    private List<Herbivore> getHerbivores() {
        return location.inhabitants.stream().
                filter(inhabitant -> (!(inhabitant instanceof IEatableAnimal) && (inhabitant instanceof IEatablePlant))).
                map(inhabitant -> (Herbivore) inhabitant).toList();
    }

    private void eatByAnimals(List<? extends Animal> animals) {
        Iterator<? extends Animal> iteratorAnimal = animals.iterator();
        while (iteratorAnimal.hasNext()) {
            Animal animal = iteratorAnimal.next();
            if (!location.inhabitants.contains(animal)){continue;}         //уже само было съедено
            Map<Class<? extends Inhabitant>, Float> probabilityAttack = InhabitantConfig.dataProbability.get(animal.getClass());
            huntAnimal(animal, probabilityAttack);
        }
    }

    private void huntAnimal(Animal animal, Map<Class<? extends Inhabitant>, Float> probabilityAttack) {
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

    public void mateAnimals() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<Class<? extends Animal>, List<Animal>> males = getAnimalsByGender(MALE);
        Map<Class<? extends Animal>, List<Animal>> females = getAnimalsByGender(FEMALE);
        for (Map.Entry<Class<? extends Animal>, List<Animal>> entry : males.entrySet()) {
            List<Animal> listMales = entry.getValue();
            List<Animal> listFemales = females.get(entry.getKey());
            listMales = new ArrayList<>(listMales);
            listFemales = new ArrayList<>(listFemales);
            findCoupleForMating(listMales, listFemales);
        }
    }

    private Map<Class<? extends Animal>, List<Animal>> getAnimalsByGender(Gender gender) {
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

    private void findCoupleForMating(List<Animal> males, List<Animal> females) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
                giveBirth(male.getClass());
            }
        }
    }

    private void giveBirth(Class<? extends Animal> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int maxCountChildren = InhabitantConfig.maxKids.get(clazz);
        int countChild = random.nextInt(maxCountChildren);
        Constructor<? extends Animal> constructor = clazz.getConstructor();
        for (int i = 0; i < countChild; i++) {
            Animal animal = constructor.newInstance();
            addInhabitant(animal);
        }
    }

    public void growPlant(Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Class<? extends AbstractPlant> clazz : InhabitantConfig.classesPlants) {
            int qualityPlant = InhabitantConfig.qualityPlantEveryStep.get(clazz);
            List<AbstractPlant> plants = FactoryInhabitant.createPlants(qualityPlant, clazz);
                location.inhabitants.addAll(plants);
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void removeInhabitant(Inhabitant inhabitant) {
        location.inhabitants.remove(inhabitant);
    }

    public void addInhabitant(Inhabitant inhabitant) {
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
}
