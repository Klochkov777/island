package priv.klochkov.island.view;

import priv.klochkov.island.config.InhabitantConfig;
import priv.klochkov.island.controller.IslandController;
import priv.klochkov.island.model.Inhabitant;
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
        System.out.println("=========================================================================================" +
                "\n\n\n\n\n");
    }


    private String dataOneClass(String simpleNameClass, Long qualityInhabitant) {
        return String.format("%-15s %-15s", simpleNameClass, qualityInhabitant);
    }
}
