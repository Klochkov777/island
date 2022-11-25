package priv.klochkov.island.controller;

import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.tasks.TaskAnimal;
import priv.klochkov.island.tasks.TaskPlant;
import priv.klochkov.island.tasks.TaskView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    Island island;
    IslandController islandController;
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public Controller(int lengthIsland, int widthIsland) {
        this.island = new Island(lengthIsland,widthIsland);
        islandController = new IslandController();
        islandController.setIsland(island);
        islandController.settleAnimalsToIsland();
    }

    public void start(){
        executorService.scheduleAtFixedRate(new TaskAnimal(islandController), 0, 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new TaskPlant(islandController), 0, 5, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new TaskView(island), 0, 6, TimeUnit.SECONDS);
    }




}
