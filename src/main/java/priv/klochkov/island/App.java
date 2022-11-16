package priv.klochkov.island;

import priv.klochkov.island.controller.ControllerSettling;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.island.Island;
import priv.klochkov.island.model.island.Location;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Island island = new Island(3,3);
        ControllerSettling controllerSettling = new ControllerSettling(island);
        controllerSettling.settleAnimalsToIsland();


        List<Inhabitant> inhabitants = island.getLocations().get(2).get(2).getInhabitants();
        for (Inhabitant inhabitant : inhabitants) {
            System.out.println(inhabitant);
        }
    }
}
