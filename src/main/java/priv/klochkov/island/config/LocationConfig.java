package priv.klochkov.island.config;

import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.plant.Plant;
import priv.klochkov.island.utils.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LocationConfig {

    public static final List<Class<? extends Inhabitant>> classesInhabitant;
    public static final Map<Class<? extends Inhabitant>, Integer> maxQualityInhabitants;

    static {
        classesInhabitant = initializationListInhabitant();
        maxQualityInhabitants = convertProperties("location/numberAnimal.properties");
    }

    private static List<Class<? extends Inhabitant>> initializationListInhabitant(){
        List<Class<? extends Inhabitant>> result = new ArrayList<>(AnimalConfig.classesAnimal);
        result.addAll(PlantConfig.classesPlants);
        return result;
    }

    private static Map<Class<? extends Inhabitant>, Integer> convertProperties(String fileName){
        Map<String, Float> map = Converter.convertPropertiesToMap(fileName);
        Map<Class<? extends Inhabitant>, Integer> result = new HashMap<>();
        for (Class<? extends Inhabitant> clazz : classesInhabitant) {
            result.put(clazz, (int) (float) map.get(clazz.getSimpleName()));
        };
        return result;
    }

    public static void main(String[] args) {
        for (Map.Entry<Class<? extends Inhabitant>, Integer> entry : maxQualityInhabitants.entrySet()) {
            System.out.println(entry);
        }
    }
}
