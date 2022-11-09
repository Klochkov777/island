package priv.klochkov.island.model.animal.predators;

import priv.klochkov.island.model.animal.Animal;

import java.util.Map;

public class Wolf extends Predator {

    public Wolf() {
        super("animals/probability/wolf.properties", 3, 30, 8);
    }





    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        System.out.println("Wolf");
        for (Map.Entry<Class<? extends Animal>, Integer> entry: wolf.getProbabilityEatData().entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println(wolf.getGender());

        System.out.println("\n\n\n");

        Snake snake = new Snake();
        System.out.println("Snake");
        for (Map.Entry<Class<? extends Animal>, Integer> entry: snake.getProbabilityEatData().entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println(snake.getGender());
    }
}
