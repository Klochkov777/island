package priv.klochkov.island.model.animal;

import priv.klochkov.island.reader.PropertiesReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class Animal {
    private final int speedMovement;
    private final int weight;
    private final int needFoodKg;
    private Map<String, Integer> probabilityEatData;
    private static final String name;


    public Animal(String fileNameProbabilityEat, int speedMovement, int weight, int needFoodKg, String name) {
        probabilityEatData = convertPropertiesToHashMap(fileNameProbabilityEat);
        this.weight = weight;
        this.needFoodKg = needFoodKg;
        this.speedMovement = speedMovement;

    }

    private Map<String, Integer> convertPropertiesToHashMap (String fileNameProbabilityEat) {
        Properties properties = new PropertiesReader(fileNameProbabilityEat).getProperties();
        HashMap<String, Integer> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            result.put(String.valueOf(entry.getKey()), Integer.valueOf(String.valueOf(entry.getValue())));
        }
        return result;
    }

    public HashMap<String, Integer> getProbabilityEatData() {
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
