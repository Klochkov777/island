package priv.klochkov.island.model.animal;

import priv.klochkov.island.constants.Gender;
import priv.klochkov.island.utils.Converter;
import java.util.*;


public abstract class Animal {

    private final int speedMovement;
    private final int weight;
    private final int needFoodKg;
    private final Gender gender;
    private Map<Class<? extends Animal>, Integer> probabilityEatData;

    public Animal(String fileNameProbabilityEat, int speedMovement, int weight, int needFoodKg) {
        probabilityEatData = getResourceProbability(fileNameProbabilityEat);
        this.weight = weight;
        this.needFoodKg = needFoodKg;
        this.speedMovement = speedMovement;
        this.gender = Gender.randomGender();
    }

    private Map <Class<? extends Animal>, Integer> getResourceProbability (String fileNameProbabilityEat){
        Map<String, Integer> mapProperties = Converter.convertPropertiesToMap(fileNameProbabilityEat);
        List<Class<?>> childClasses = Converter.getChildClasses(Animal.class, "priv.klochkov.island.model.animal");
        Map <Class<?>, Integer> mapClasses = Converter.convertKeysMapStringToClass(mapProperties, childClasses);
        Map <Class<? extends Animal>, Integer> result = new HashMap<>();
        for (Map.Entry<Class<?>, Integer> entry: mapClasses.entrySet()) {
            result.put((Class<? extends Animal>) entry.getKey(), entry.getValue());
        }
        return result;
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

    public Gender getGender() {
        return gender;
    }
}
