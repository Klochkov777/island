package priv.klochkov.island.tasks;

import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.view.ViewIsland;

public class TaskView implements Runnable {
    ViewIsland viewIsland;

    public TaskView(Island island) {
        this.viewIsland = new ViewIsland(island);
    }

    @Override
    public void run() {
            viewIsland.printIsland();
            viewIsland.separate();
    }
}
