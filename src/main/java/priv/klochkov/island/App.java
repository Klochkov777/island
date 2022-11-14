package priv.klochkov.island;

import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.view.ViewIsland;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Island island = new Island(2,1);
        ViewIsland viewIsland = new ViewIsland(island);
        viewIsland.lookIsland();
    }
}
