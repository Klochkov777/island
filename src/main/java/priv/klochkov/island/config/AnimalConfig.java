package priv.klochkov.island.config;

import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.herbivores.*;
import priv.klochkov.island.model.animal.predators.*;
import priv.klochkov.island.utils.Converter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnimalConfig {

//    public static List<Class<?extends Animal>> classesAnimal;
//    public static Map<Class<? extends Animal>, Map<Class<? extends Inhabitant>, Float>> dataProbability;
//    public static Map<Class<? extends Inhabitant>, Integer> maxKids;
//    public static final List<Class<? extends Inhabitant>> classesInhabitant;
//
//    static {
//        classesAnimal = initializationClassesAnimal();
//        dataProbability = initializationDataProbability();
//        maxKids = convertPropertiesKids();
//    }
//
//    private static List<Class<? extends Animal>> initializationClassesAnimal(){
//        return List.of(Wolf.class,
//                Bear.class,
//                Boar.class,
//                Buffalo.class,
//                Caterpillar.class,
//                Deer.class,
//                Duck.class,
//                Eagle.class,
//                Fox.class,
//                Goat.class,
//                Hare.class,
//                Horse.class,
//                Mouse.class,
//                Sheep.class,
//                Snake.class);
//    }
//
//    private static Map<Class<? extends Animal>, Map<Class<? extends Inhabitant>, Float>> initializationDataProbability(){
//        Map<Class<? extends Animal>, Map<Class<? extends Inhabitant>, Float>> result = new HashMap<>();
//        List<String> fileNamesProbability = classesAnimal.stream()
//                .map(clazz -> "animals/probability/" + clazz.getSimpleName().toLowerCase() + ".properties").toList();
//        for (int i = 0; i < fileNamesProbability.size(); i++) {
//            result.put(classesAnimal.get(i), convertProperties(fileNamesProbability.get(i)));
//        }
//        return result;
//    }
//
//
//    private static Map<Class<? extends Inhabitant>, Float> convertProperties(String fileName){
//        Map<String, Float> map = Converter.convertPropertiesToMap(fileName);
//        Map<Class<? extends Inhabitant>, Float> map1 = Converter.convertKeysMapStringToClass1(map, LocationConfig.classesInhabitant);
////        Map<Class<? extends Inhabitant>, Float> result = new HashMap<>();
////        for (Map.Entry<Class<?>, Float> entry : map1.entrySet()) {
////            result.put((Class<? extends Inhabitant>) entry.getKey(), entry.getValue());
////        };
//        return map1;
//    }
//
//
//    private static Map<Class<? extends Inhabitant>, Integer> convertPropertiesKids(){
//        Map<Class<? extends Inhabitant>, Float> map = convertProperties("kids/count.properties");
//        Map<Class<? extends Inhabitant>, Integer> result = new HashMap<>();
//        for (Map.Entry<Class<? extends Inhabitant>, Float> entry : map.entrySet()) {
//            result.put(entry.getKey(), (int) (float) entry.getValue());
//        }
//        return result;
//    }
//
//
//    public static void main(String[] args) {
////        classesAnimal.stream().forEach(System.out::println);
////        System.out.println();
////        for (Map.Entry<Class<? extends Animal>, Map<Class<? extends Inhabitant>, Float>> entry1: dataProbability.entrySet() ) {
////            System.out.println();
////            System.out.println(entry1.getKey());
////            Map <Class<? extends Inhabitant>, Float> map = entry1.getValue();
////            for (Map.Entry<Class<? extends Inhabitant>, Float> entry2 : map.entrySet()) {
////                System.out.println(entry2);
////            }
////        }
//        for (Map.Entry<Class<? extends Inhabitant>, Integer> entry : maxKids.entrySet()) {
//            System.out.println(entry.getKey().getSimpleName() + " : " + entry.getValue());
//        }
//
//    }
}
