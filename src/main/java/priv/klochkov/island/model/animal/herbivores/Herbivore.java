package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.Animal;
import priv.klochkov.island.model.animal.interfaces.IEatablePlant;

public abstract class Herbivore extends Animal implements IEatablePlant {
    public Herbivore(int speedMovement, int weight, int maxSatiety, int x, int y) {
        super(speedMovement, weight, maxSatiety, x, y);
    }
}
