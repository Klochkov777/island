package priv.klochkov.island.model.animal.predators;

import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

public abstract class Predator extends Animal implements IEatableAnimal {
    public Predator(int speedMovement, int weight, int maxSatiety, int x, int y) {
        super(speedMovement, weight, maxSatiety, x, y);
    }
}
