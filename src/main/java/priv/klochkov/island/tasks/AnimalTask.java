package priv.klochkov.island.tasks;

import priv.klochkov.island.controller.IslandController;

public class AnimalTask implements Runnable {
    IslandController islandController;

    public AnimalTask(IslandController islandController) {
        this.islandController = islandController;
    }

    @Override
    public void run() {
        //islandController.eat();
        //islandController.mate();
        islandController.moveAllAnimals();
    }
}
