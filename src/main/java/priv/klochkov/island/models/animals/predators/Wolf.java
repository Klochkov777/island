package priv.klochkov.island.models.animals.predators;

import priv.klochkov.island.models.animals.predators.Predator;

import java.util.Map;

public class Wolf extends Predator {

    public Wolf() {
        super("animals/probability/wolf.properties");
    }

    public static void main(String[] args) {
        for (Map.Entry<String, Integer> entry: Wolf.getProbabilityEatData().entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
