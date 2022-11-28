package priv.klochkov.island.tasks;

import priv.klochkov.island.controller.IslandController;

public class TaskView implements Runnable {

    private final IslandController islandController;

    public TaskView(IslandController islandController) {
        this.islandController = islandController;
    }

    @Override
    public void run() {
        islandController.printIsland();
    }
}
