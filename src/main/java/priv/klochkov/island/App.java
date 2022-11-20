package priv.klochkov.island;

import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.island.Island;

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


        List<Inhabitant> inhabitants = island.getField().get(2).get(2).getInhabitants();
        for (Inhabitant inhabitant : inhabitants) {
            System.out.println(inhabitant);
        }
    }
}
