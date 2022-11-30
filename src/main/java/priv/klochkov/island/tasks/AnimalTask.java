package priv.klochkov.island.tasks;

import priv.klochkov.island.controller.IslandController;

public class AnimalTask implements Runnable {
    private final IslandController islandController;

    public AnimalTask(IslandController islandController) {
        this.islandController = islandController;
    }

    @Override
    public void run() {
        islandController.setSatietyZero();
        islandController.eat();
        islandController.deathHungryAnimals();
        islandController.mate();
        islandController.moveAllAnimals();
    }
}
