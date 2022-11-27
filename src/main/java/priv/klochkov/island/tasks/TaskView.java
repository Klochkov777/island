package priv.klochkov.island.tasks;

import priv.klochkov.island.controller.IslandController;

public class TaskView implements Runnable {
    //ViewIsland viewIsland;
    IslandController islandController;

//    public TaskView(Island island) {
//        this.viewIsland = new ViewIsland(island);
//    }


    public TaskView(IslandController islandController) {
        this.islandController = islandController;
    }

    @Override
    public void run() {
        islandController.printIsland();
    }
}
