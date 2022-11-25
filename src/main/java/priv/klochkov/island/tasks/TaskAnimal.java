package priv.klochkov.island.tasks;

import priv.klochkov.island.controller.IslandController;
import priv.klochkov.island.model.island.Island;

public class TaskAnimal implements Runnable {
    IslandController islandController;

    public TaskAnimal(IslandController islandController) {
        this.islandController = islandController;
    }

    @Override
    public void run() {
            islandController.eat();
            islandController.mate();
            islandController.moveAllAnimals();
    }
}
