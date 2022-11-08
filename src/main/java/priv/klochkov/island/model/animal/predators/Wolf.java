package priv.klochkov.island.model.animal.predators;

import java.util.Map;

public class Wolf extends Predator {

    public Wolf() {
        super("animals/probability/wolf.properties", 3, 30, 8);
    }





    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        for (Map.Entry<String, Integer> entry: wolf.getProbabilityEatData().entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }


        System.out.println(wolf.getNeedFoodKg());
    }
}
