package priv.klochkov.island.view;

import priv.klochkov.island.config.InhabitantConfig;
import priv.klochkov.island.config.LocationConfig;
import priv.klochkov.island.controller.IslandController;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.herbivores.Buffalo;
import priv.klochkov.island.model.animal.herbivores.Caterpillar;
import priv.klochkov.island.model.animal.herbivores.Duck;
import priv.klochkov.island.model.animal.herbivores.Goat;
import priv.klochkov.island.model.animal.predators.Wolf;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.util.*;

public class ViewIsland {

    Island island;

    public ViewIsland(Island island) {
        this.island = island;
    }

    public void printIsland() {
        for (int i = 0; i < island.field.size(); i++) {
            List<Location> locations = island.field.get(i);
            for (Class<? extends Inhabitant> clazz : InhabitantConfig.classesInhabitant) {
                for (Location location : locations) {
                    Map<Class<? extends Inhabitant>, Long> map = location.getQualityInhabitant();
                    String str = dataOneClass(clazz.getSimpleName(), map.get(clazz));
                    System.out.print(str);
                }
                System.out.println();
            }
            System.out.println("\n\n");
        }
    }

    public void separate() {
        System.out.println("Finish\n\n\n\n\n");
    }


    private String dataOneClass(String simpleNameClass, Long qualityInhabitant) {
        return String.format("%-15s %-15s", simpleNameClass, qualityInhabitant);
    }

    public static void main(String[] args) {
//        Location location = new Location(2, 2);
//        location.inhabitants.add(new Wolf());
//        location.inhabitants.add(new Wolf());
//        location.inhabitants.add(new Wolf());
//        location.inhabitants.add(new Duck());
//        location.inhabitants.add(new Duck());
//        location.inhabitants.add(new Goat());
//        location.inhabitants.add(new Buffalo());
//        location.inhabitants.add(new Caterpillar());
//
//        for (Map.Entry<Class<? extends Inhabitant>, Long> entry : location.getQualityInhabitant().entrySet()) {
//            System.out.println(entry);
//        }

        Island island = new Island(3,3);
        IslandController islandController = new IslandController();
        islandController.setIsland(island);
        islandController.settleAnimalsToIsland();
        ViewIsland viewIsland = new ViewIsland(island);
        viewIsland.printIsland();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(island.field.get(2).get(2).getQualityInhabitant());
    }


}
