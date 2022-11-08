package priv.klochkov.island.model.animal;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import priv.klochkov.island.reader.PropertiesReader;
import java.util.*;

public abstract class Animal {
    private static List<Class<? extends Animal>> listChildClasses;
    private final int speedMovement;
    private final int weight;
    private final int needFoodKg;
    private Map<Class<? extends Animal>, Integer> probabilityEatData;

    static {
        listChildClasses = getChildClasses(Animal.class, "priv.klochkov.island.model.animal");
    }

    public Animal(String fileNameProbabilityEat, int speedMovement, int weight, int needFoodKg) {
        probabilityEatData = convertPropertiesToMap(fileNameProbabilityEat);
        this.weight = weight;
        this.needFoodKg = needFoodKg;
        this.speedMovement = speedMovement;
    }

    private Map<Class<? extends Animal>, Integer> convertPropertiesToMap(String fileNameProbabilityEat) {
        Properties properties = new PropertiesReader(fileNameProbabilityEat).getProperties();
        HashMap<String, Integer> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            result.put(String.valueOf(entry.getKey()), Integer.valueOf(String.valueOf(entry.getValue())));
        }
        return convertKeysMapStringToClass(result, listChildClasses);
    }

    private Map<Class<? extends Animal>, Integer> convertKeysMapStringToClass(Map<String, Integer> map,
                                                                              List<Class<? extends Animal>> listClasses){
        Map<Class<? extends Animal>, Integer> result = new HashMap<>();
        for (Class<? extends Animal> clazz : listClasses) {
            if (map.containsKey(clazz.getSimpleName())) {
                result.put(clazz, map.get(clazz.getSimpleName()));
            }
        }
        return result;
    }

    private static List<Class<? extends Animal>> getChildClasses (Class<?> superClass, String packageForFounding) {
        Reflections reflections = new Reflections(packageForFounding, new SubTypesScanner());
        List<Class<? extends Animal>> subTypes = new ArrayList<>();
        for (String className : reflections.getStore().get(SubTypesScanner.class.getSimpleName()).values()) {
            try {
                Class<? extends Animal> subType = (Class<? extends Animal>) Class.forName(className);
                if (superClass.isAssignableFrom(subType)) {
                    subTypes.add(subType);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Этого не может быть:)", e);
            }
        }
        return subTypes;
    }

    public Map <Class<? extends Animal>, Integer> getProbabilityEatData() {
        return probabilityEatData;
    }

    public int getSpeedMovement() {
        return speedMovement;
    }

    public int getWeight() {
        return weight;
    }

    public int getNeedFoodKg() {
        return needFoodKg;
    }

}
