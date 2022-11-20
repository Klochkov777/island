package priv.klochkov.island.Factory;

import priv.klochkov.island.config.LocationConfig;
import priv.klochkov.island.model.Inhabitant;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FactoryInhabitant {
    public static List<Inhabitant> createInhabitants ()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Inhabitant> inhabitants = new ArrayList<>();
        Map<Class<? extends Inhabitant>, Integer> maxQualityInhabitants = LocationConfig.maxQualityInhabitants;
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
}
