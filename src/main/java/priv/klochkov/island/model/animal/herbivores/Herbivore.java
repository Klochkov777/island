package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.interfaces.IEatablePlant;

public class Herbivore extends Animal implements IEatablePlant {
    public Herbivore(int speedMovement, int weight, int needFoodKg) {
        super(speedMovement, weight, needFoodKg);
    }
}
