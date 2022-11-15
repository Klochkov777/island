package priv.klochkov.island.utils;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
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

    public static List<Class<?>> getChildClasses (Class<?> superClass, String packageForFounding) {
        Reflections reflections = new Reflections(packageForFounding, new SubTypesScanner());
        List<Class<?>> subTypes = new ArrayList<>();
        for (String className : reflections.getStore().get(SubTypesScanner.class.getSimpleName()).values()) {
            try {
                Class<?> subType = Class.forName(className);
                if (superClass.isAssignableFrom(subType)) {
                    subTypes.add(subType);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Этого не может быть:)", e);
            }
        }
        return subTypes;
    }
}
