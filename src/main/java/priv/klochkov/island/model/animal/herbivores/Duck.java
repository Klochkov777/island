package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

public class Duck extends Herbivore implements IEatableAnimal {
    public Duck(int x, int y) {
        super(3, 30, 8, x, y);
    }
}
