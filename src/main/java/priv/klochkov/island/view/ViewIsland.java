package priv.klochkov.island.view;

import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.util.*;

public class ViewIsland {

    Island island;

    public ViewIsland(Island island) {
        this.island = island;
    }

    public void lookIsland(){
        Location[][] locations = island.getIsland();
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                System.out.println(x + " " +y);
                Location location = locations[y][x];
                Map<String, Integer> animals = getDataLocation(location);
                for (Map.Entry<String, Integer> entry: animals.entrySet()){
                    System.out.println(entry);
                }
            }
        }
    }

    private Map<String, Integer> getDataLocation(Location location){
        Map<String, Integer> map = new HashMap<>();
        Set<Animal> set = new HashSet<>();
        Collections.addAll(set, location.)
        for (Animal animal: set) {
            int countAnimalEveryKind = getCountAnimalEveryKind(animal.getClass(), location.getAnimals());
            map.put(animal.toString(), countAnimalEveryKind);
        }
        return map;
    }

    private int getCountAnimalEveryKind(Class<? extends Animal> clazz, List<Animal> list){
        return (int) list.stream().filter(el -> el.getClass().equals(clazz)).count();
    }
}
