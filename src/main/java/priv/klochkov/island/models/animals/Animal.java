package priv.klochkov.island.models.animals;

import priv.klochkov.island.reader.PropertiesReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class Animal {
    private static HashMap<String, Integer> probabilityEatData;


    public Animal(String fileNameProbabilityEat) {
        probabilityEatData = convertPropertiesToHashMap(fileNameProbabilityEat);
    }

    private HashMap<String, Integer> convertPropertiesToHashMap (String fileNameProbabilityEat) {
        Properties properties = new PropertiesReader(fileNameProbabilityEat).getProperties();
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            result.put(String.valueOf(entry.getKey()), Integer.valueOf(String.valueOf(entry.getValue())));
        }
        return result;
    }

    public static HashMap<String, Integer> getProbabilityEatData() {
        return probabilityEatData;
    }
}
