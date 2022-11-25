package priv.klochkov.island.tasks;

import priv.klochkov.island.controller.IslandController;

import java.lang.reflect.InvocationTargetException;

public class TaskPlant implements Runnable {
    IslandController islandController;

    public TaskPlant(IslandController islandController) {
        this.islandController = islandController;
    }

    @Override
    public void run() {
            try {
                islandController.growPlant();
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
    }
}
