package priv.klochkov.island.model.animal.predators;

import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

public abstract class Predator extends Animal implements IEatableAnimal {
    public Predator(String fileNameProbabilityEat, int speedMovement, int weight, int needFoodKg) {
        super(fileNameProbabilityEat, speedMovement, weight, needFoodKg);
    }
}
