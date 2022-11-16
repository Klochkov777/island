package priv.klochkov.island.model.island;

import priv.klochkov.island.config.LocationConfig;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Location {

    private List<Inhabitant> inhabitants;
    public Location() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    }


    public List<Inhabitant> createAnimalsOnLocation() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Inhabitant> inhabitants = new ArrayList<>();
        for (Map.Entry<Class<? extends Inhabitant>, Float> entry : LocationConfig.maxQualityInhabitants.entrySet()) {
            Class<? extends Inhabitant> clazz = entry.getKey();
            addAnimalRandomQuality(inhabitants, clazz, (int) (float) entry.getValue());
        }
        return inhabitants;
    }

    private void addAnimalRandomQuality(List<Inhabitant> inhabitants, Class<? extends Inhabitant> clazz, int maxQuality)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Random random = new Random();
        int quality = random.nextInt(maxQuality);
        for (int i = 0; i < quality; i++) {
            Inhabitant inhabitant = clazz.getConstructor().newInstance();
            inhabitants.add(inhabitant);
        }
    }

    public List<Inhabitant> getInhabitants() {
        return inhabitants;
    }

    public static void main(String[] args) throws
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Location location = new Location();

        Map<Class<? extends Inhabitant>, Float> map = LocationConfig.maxQualityInhabitants;
        for (Map.Entry<Class<? extends Inhabitant>, Float> entry : map.entrySet()) {
            System.out.println(entry.getKey().getSimpleName() + " " + entry.getValue());
        }
        System.out.println();
        System.out.println(LocationConfig.listInhabitant);
        System.out.println();
        System.out.println(location.inhabitants);
    }
}