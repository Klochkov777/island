package priv.klochkov.island.utils;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.reader.PropertiesReader;

import java.util.*;

public class Converter {

    public static Map<String, Float> convertPropertiesToMap(String fileNameProbabilityEat) {
        Properties properties = new PropertiesReader(fileNameProbabilityEat).getProperties();
        HashMap<String, Float> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            result.put(String.valueOf(entry.getKey()), Float.valueOf(String.valueOf(entry.getValue())));
        }
        return result;
    }

    public static Map<Class<?>, Float> convertKeysMapStringToClass(Map<String, Float> map,
                                                                   List<Class<? extends Animal>> listClasses){
        Map<Class<?>, Float> result = new HashMap<>();
        for (Class<?> clazz : listClasses) {
            if (map.containsKey(clazz.getSimpleName())) {
                result.put(clazz, map.get(clazz.getSimpleName()));
            }
        }
        return result;
    }

    public static Map<Class<? extends Inhabitant>, Float> convertKeysMapStringToClass1(Map<String, Float> map,
                                                                   List<Class<? extends Inhabitant>> listClasses){
        Map<Class<? extends Inhabitant>, Float> result = new HashMap<>();
        for (Class<? extends Inhabitant> clazz : listClasses) {
            if (map.containsKey(clazz.getSimpleName())) {
                result.put(clazz, map.get(clazz.getSimpleName()));
            }
        }
        return result;
    }


}
