package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

public class Duck extends Herbivore implements IEatableAnimal {
    public Duck() {
        super(4, 1, 0.15);
    }
}
