package priv.klochkov.island.models.animals.predators;

import priv.klochkov.island.models.animals.Animal;
import priv.klochkov.island.models.animals.interfaces.IEatableAnimal;

public abstract class Predator extends Animal implements IEatableAnimal {
    public Predator(String fileNameProbabilityEat) {
        super(fileNameProbabilityEat);
    }
}
