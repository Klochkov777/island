package priv.klochkov.island.config;

import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.herbivores.*;
import priv.klochkov.island.model.animal.predators.*;
import priv.klochkov.island.model.plant.AbstractPlant;
import priv.klochkov.island.model.plant.Plant;
import priv.klochkov.island.utils.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InhabitantConfig {
    public static List<Class<?extends Animal>> classesAnimal;
    public static List<Class<? extends AbstractPlant>> classesPlants;
    public static final List<Class<? extends Inhabitant>> classesInhabitant;
    public static Map<Class<? extends Animal>, Map<Class<? extends Inhabitant>, Float>> dataProbability;
    public static Map<Class<? extends Inhabitant>, Integer> maxKids;
    public static Map<Class<? extends AbstractPlant>, Integer> qualityPlantEveryStep;
    public static final Map<Class<? extends Inhabitant>, Integer> maxQualityInhabitants;

    static {
        classesAnimal = initializationClassesAnimal();
        classesPlants = initializationClassesPlants();
        classesInhabitant = initializationClassesInhabitant();
        dataProbability = initializationDataProbability();
        maxKids = convertPropertiesKids("kids/count.properties");
        qualityPlantEveryStep = convertPropertiesPlants("location/plantStep.properties");
        maxQualityInhabitants = convertPropertiesMaxQuality("location/numberAnimal.properties");
    }

    private static List<Class<? extends Animal>> initializationClassesAnimal(){
        return List.of(Wolf.class,
                Bear.class,
                Boar.class,
                Buffalo.class,
                Caterpillar.class,
                Deer.class,
                Duck.class,
                Eagle.class,
                Fox.class,
                Goat.class,
                Hare.class,
                Horse.class,
                Mouse.class,
                Sheep.class,
                Snake.class);
    }

    private static List<Class<? extends AbstractPlant>> initializationClassesPlants(){
        return List.of(Plant.class);
    }

    private static List<Class<? extends Inhabitant>> initializationClassesInhabitant() {
        List<Class<? extends Inhabitant>> result = new ArrayList<>();
        result.addAll(classesAnimal);
        result.addAll(classesPlants);
        return result;
    }

    private static Map<Class<? extends Animal>, Map<Class<? extends Inhabitant>, Float>> initializationDataProbability(){
        Map<Class<? extends Animal>, Map<Class<? extends Inhabitant>, Float>> result = new HashMap<>();
        List<String> fileNamesProbability = classesAnimal.stream()
                .map(clazz -> "animals/probability/" + clazz.getSimpleName().toLowerCase() + ".properties").toList();
        for (int i = 0; i < fileNamesProbability.size(); i++) {
            result.put(classesAnimal.get(i), convertProperties(fileNamesProbability.get(i)));
        }
        return result;
    }


    private static Map<Class<? extends Inhabitant>, Float> convertProperties(String fileName){
        Map<String, Float> map = Converter.convertPropertiesToMap(fileName);
        Map<Class<? extends Inhabitant>, Float> map1 = Converter.convertKeysMapStringToClass1(map, classesInhabitant);
        return map1;
    }


    private static Map<Class<? extends Inhabitant>, Integer> convertPropertiesKids(String fileName){
        Map<Class<? extends Inhabitant>, Float> map = convertProperties(fileName);
        Map<Class<? extends Inhabitant>, Integer> result = new HashMap<>();
        for (Map.Entry<Class<? extends Inhabitant>, Float> entry : map.entrySet()) {
            result.put(entry.getKey(), (int) (float) entry.getValue());
        }
        return result;
    }

    private static Map<Class<? extends AbstractPlant>, Integer> convertPropertiesPlants(String fileName){
        Map<String, Float> map = Converter.convertPropertiesToMap(fileName);
        Map<Class<? extends AbstractPlant>, Integer> result = new HashMap<>();
        for (Class<? extends AbstractPlant> clazz : classesPlants) {
            result.put(clazz, (int) (float) map.get(clazz.getSimpleName()));
        };
        return result;
    }

    private static Map<Class<? extends Inhabitant>, Integer> convertPropertiesMaxQuality(String fileName){
        Map<String, Float> map = Converter.convertPropertiesToMap(fileName);
        Map<Class<? extends Inhabitant>, Integer> result = new HashMap<>();
        for (Class<? extends Inhabitant> clazz : classesInhabitant) {
            result.put(clazz, (int) (float) map.get(clazz.getSimpleName()));
        };
        return result;
    }
}
