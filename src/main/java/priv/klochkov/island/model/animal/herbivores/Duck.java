package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

public class Duck extends Herbivore implements IEatableAnimal {
    public Duck() {
        super(3, 30, 8);
    }
}
