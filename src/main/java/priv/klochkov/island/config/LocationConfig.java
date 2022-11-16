package priv.klochkov.island.config;

import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.plant.Plant;
import priv.klochkov.island.utils.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LocationConfig {

    public static final List<Class<? extends Inhabitant>> listInhabitant;
    public static final Map<Class<? extends Inhabitant>, Float> maxQualityInhabitants;

    static {
        listInhabitant = initializationListInhabitant();
        maxQualityInhabitants = convertProperties("location/numberAnimal.properties");
    }

    private static List<Class<? extends Inhabitant>> initializationListInhabitant(){
        List<Class<? extends Inhabitant>> result = new ArrayList<>(AnimalConfig.classesAnimal);
        result.add(Plant.class);
        return result;
    }

    private static Map<Class<? extends Inhabitant>, Float> convertProperties(String fileName){
        Map<String, Float> map = Converter.convertPropertiesToMap(fileName);
        Map<Class<? extends Inhabitant>, Float> result = new HashMap<>();
        for (Class<? extends Inhabitant> clazz : listInhabitant) {
            result.put(clazz, map.get(clazz.getSimpleName()));
        };
        return result;
    }

    public static void main(String[] args) {
        for (Map.Entry<Class<? extends Inhabitant>, Float> entry : maxQualityInhabitants.entrySet()) {
            System.out.println(entry);
        }
    }
}
