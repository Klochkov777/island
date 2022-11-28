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
    public List<Inhabitant> createInhabitants ()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Inhabitant> inhabitants = new ArrayList<>();
        Map<Class<? extends Inhabitant>, Integer> maxQualityInhabitants = InhabitantConfig.maxQualityInhabitants;
        Random random = new Random();
        for (Map.Entry<Class<? extends Inhabitant>, Integer> entry : maxQualityInhabitants.entrySet()) {
            Class<? extends Inhabitant> clazz = entry.getKey();
            int maxQuality = entry.getValue();
            int quality = random.nextInt(maxQuality);
            for (int i = 0; i < quality; i++) {
                Inhabitant inhabitant = createInhabitant(clazz);
                inhabitants.add(inhabitant);
            }
        }
        return inhabitants;
    }

    public Inhabitant createInhabitant(Class<? extends Inhabitant> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getConstructor().newInstance();
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

    public AbstractPlant Plant(Class<? extends AbstractPlant> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getConstructor().newInstance();
    }
}
