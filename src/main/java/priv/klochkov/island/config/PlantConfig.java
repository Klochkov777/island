package priv.klochkov.island.config;
import priv.klochkov.island.model.plant.AbstractPlant;
import priv.klochkov.island.model.plant.Plant;
import priv.klochkov.island.utils.Converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantConfig {
//    public static List<Class<? extends AbstractPlant>> classesPlants;
//    public static Map<Class<? extends AbstractPlant>, Integer> qualityPlantEveryStep;
//
//    static {
//        classesPlants = initializationClassesPlants();
//        qualityPlantEveryStep = convertPropertiesInhabitant("location/plantStep.properties");
//    }
//
//    private static List<Class<? extends AbstractPlant>> initializationClassesPlants(){
//        return List.of(Plant.class);
//    }
//
//    private static Map<Class<? extends AbstractPlant>, Integer> convertPropertiesInhabitant(String fileName){
//        Map<String, Float> map = Converter.convertPropertiesToMap(fileName);
//        Map<Class<? extends AbstractPlant>, Integer> result = new HashMap<>();
//        for (Class<? extends AbstractPlant> clazz : classesPlants) {
//            result.put(clazz, (int) (float) map.get(clazz.getSimpleName()));
//        };
//        return result;
//    }
}
