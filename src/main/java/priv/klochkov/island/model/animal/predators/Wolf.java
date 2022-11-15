package priv.klochkov.island.model.animal.predators;

import priv.klochkov.island.config.AnimalConfig;
import priv.klochkov.island.model.animal.Animal;
import java.util.Map;


public class Wolf extends Predator {

    public Wolf() {
        super(3, 30, 8);
    }

    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        System.out.println("Speed " + wolf.speedMovement);
        System.out.println("Weight " + wolf.weight);
        System.out.println("NeedFoodKg " + wolf.needFoodKg);
        System.out.println();
    }
}
