package priv.klochkov.island.Factory;

import priv.klochkov.island.config.InhabitantConfig;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.plant.AbstractPlant;
import priv.klochkov.island.model.plant.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FactoryInhabitant {
    public static List<Inhabitant> createInhabitants ()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Inhabitant> inhabitants = new ArrayList<>();
        Map<Class<? extends Inhabitant>, Integer> maxQualityInhabitants = InhabitantConfig.maxQualityInhabitants;
        Random random = new Random();
        for (Map.Entry<Class<? extends Inhabitant>, Integer> entry : maxQualityInhabitants.entrySet()) {
            Class<? extends Inhabitant> clazz = entry.getKey();
            int maxQuality = entry.getValue();
            int quality = random.nextInt(maxQuality);
            for (int i = 0; i < quality; i++) {
                Inhabitant inhabitant = clazz.getConstructor().newInstance();
                inhabitants.add(inhabitant);
            }
        }
        return inhabitants;
    }

    public static List<AbstractPlant> createPlants(int qualityPlant, Class<? extends AbstractPlant> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<AbstractPlant> result = new ArrayList<>();
        Constructor<? extends AbstractPlant> constructorPlant = clazz.getConstructor();
        for (int i = 0; i < qualityPlant; i++) {
            AbstractPlant plant = constructorPlant.newInstance();
            result.add(plant);
        }
        return result;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Plant plant = new Plant();
        System.out.println(createPlants(5, new Plant().getClass()));
    }
}
