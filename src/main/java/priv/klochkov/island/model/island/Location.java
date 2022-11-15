package priv.klochkov.island.model.island;

import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.utils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Location {
//    private static List<Class<? extends Animal>> kindsAnimal =
//            Converter.getChildClasses(Animal.class, "priv.klochkov.island.model.animal").
//                    stream().map(el -> (Class<? extends Animal>) el).collect(Collectors.toList());
//    private static Map<Class<? extends Animal>, Integer> maxQualityEveryKindAnimal =
//            getResourceProbability("location/numberAnimal.properties");
//    private List<Animal> animals = createAnimalsOnLocation();
//    public Location() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//    }

//    private static Map<Class<? extends Animal>, Integer> getResourceProbability(String fileNameProbabilityEat) {
//        Map<String, Integer> mapProperties = Converter.convertPropertiesToMap(fileNameProbabilityEat);
//        List<Class<?>> childClasses = Converter.getChildClasses(Animal.class, "priv.klochkov.island.model.animal");
//        Map<Class<?>, Integer> mapClasses = Converter.convertKeysMapStringToClass(mapProperties, childClasses);
//        Map<Class<? extends Animal>, Integer> result = new HashMap<>();
//        for (Map.Entry<Class<?>, Integer> entry : mapClasses.entrySet()) {
//            result.put((Class<? extends Animal>) entry.getKey(), entry.getValue());
//        }
//        return result;
//    }
//
//    private List<Animal> createAnimalsOnLocation() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        List<Animal> animals = new ArrayList<>();
//        Random random = new Random();
//        for (Map.Entry<Class<? extends Animal>, Integer> entry : maxQualityEveryKindAnimal.entrySet()) {
//            Class<? extends Animal> clazz = entry.getKey();
//            if (kindsAnimal.contains(clazz)) {
//                addAnimalRandomQuality(animals, clazz, entry.getValue());
//            }
//        }
//        return animals;
//    }
//
//    private void addAnimalRandomQuality(List<Animal> animals, Class<? extends Animal> clazz, int maxQuality)
//            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Random random = new Random();
//        int quality = random.nextInt(maxQuality);
//        for (int i = 0; i < quality; i++) {
//            Animal animal = clazz.getConstructor().newInstance();
//            animals.add(animal);
//        }
//    }
//
//    public static void main(String[] args) throws
//            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Location location = new Location();
//        Map<Class<? extends Animal>, Integer> map = location.maxQualityEveryKindAnimal;
//
//        for (Map.Entry<Class<? extends Animal>, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey().getSimpleName());
//        }
//        System.out.println();
//        System.out.println(location.kindsAnimal);
//        System.out.println();
//        System.out.println(location.animals);
//    }

//    public List<Animal> getAnimals() {
//        return animals;
//    }
}
